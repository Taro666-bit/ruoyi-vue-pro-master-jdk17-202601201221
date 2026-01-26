package cn.iocoder.yudao.module.ai.service.video;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoGenerateReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoPageReqVO;
import cn.iocoder.yudao.module.ai.dal.dataobject.video.AiVideoDO;
import cn.iocoder.yudao.module.ai.dal.mysql.video.AiVideoMapper;
import cn.iocoder.yudao.module.ai.enums.video.AiVideoStatusEnum;
import cn.iocoder.yudao.module.ai.framework.ai.core.model.yunwu.api.YunWuVideoApi;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ai.enums.ErrorCodeConstants.VIDEO_NOT_EXISTS;

/**
 * AI 视频 Service 实现类
 *
 * @author ruoyi
 */
@Service
@Slf4j
public class AiVideoServiceImpl implements AiVideoService {

    /**
     * 云雾API地址
     */
    private static final String YUNWU_BASE_URL = "https://yunwu.ai";

    /**
     * 平台标识
     */
    private static final String PLATFORM = "YunWu";

    @Resource
    private AiVideoMapper videoMapper;

    @Resource
    private FileApi fileApi;

    /**
     * 云雾API Key - 从配置文件读取
     */
    @Value("${yudao.ai.yunwu.api-key:}")
    private String yunwuApiKey;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long generateVideo(Long userId, AiVideoGenerateReqVO reqVO) {
        // 1. 创建视频记录
        AiVideoDO video = new AiVideoDO()
                .setUserId(userId)
                .setModel(reqVO.getModel())
                .setPlatform(PLATFORM)
                .setPrompt(reqVO.getPrompt())
                .setDuration(reqVO.getDuration() != null ? reqVO.getDuration() : 15)
                .setSize("large") // 1080p
                .setOrientation(StrUtil.isNotEmpty(reqVO.getOrientation()) ? reqVO.getOrientation() : "landscape")
                .setImageUrl(reqVO.getImageUrl())
                .setWatermark(reqVO.getWatermark() != null ? reqVO.getWatermark() : false)
                .setIsPrivate(false)
                .setStatus(AiVideoStatusEnum.IN_PROGRESS.getStatus());
        videoMapper.insert(video);

        // 2. 异步调用云雾API生成视频
        getSelf().executeGenerateVideo(video);

        return video.getId();
    }

    /**
     * 异步执行视频生成
     *
     * @param video 视频记录
     */
    @Async
    public void executeGenerateVideo(AiVideoDO video) {
        try {
            // 1. 创建云雾API客户端
            YunWuVideoApi videoApi = new YunWuVideoApi(YUNWU_BASE_URL, yunwuApiKey);

            // 2. 构建请求
            YunWuVideoApi.VideoCreateRequest request = YunWuVideoApi.VideoCreateRequest.builder()
                    .model(video.getModel())
                    .prompt(video.getPrompt())
                    .duration(video.getDuration())
                    .size(video.getSize())
                    .orientation(video.getOrientation())
                    .images(StrUtil.isNotEmpty(video.getImageUrl()) ? List.of(video.getImageUrl()) : null)
                    .watermark(video.getWatermark())
                    .isPrivate(video.getIsPrivate())
                    .build();

            // 3. 调用API
            YunWuVideoApi.VideoCreateResponse response = videoApi.createVideo(request);

            // 4. 检查响应
            if (response == null) {
                throw new IllegalStateException("云雾API响应为空");
            }

            // 5. 更新任务ID
            videoMapper.updateById(new AiVideoDO()
                    .setId(video.getId())
                    .setTaskId(response.getTaskId()));

            log.info("[executeGenerateVideo][视频任务提交成功] videoId={}, taskId={}",
                    video.getId(), response.getTaskId());

        } catch (Exception ex) {
            log.error("[executeGenerateVideo][视频任务提交失败] videoId={}", video.getId(), ex);
            videoMapper.updateById(new AiVideoDO()
                    .setId(video.getId())
                    .setStatus(AiVideoStatusEnum.FAIL.getStatus())
                    .setErrorMessage(ex.getMessage())
                    .setFinishTime(LocalDateTime.now()));
        }
    }

    @Override
    public AiVideoDO getVideo(Long id) {
        return videoMapper.selectById(id);
    }

    @Override
    public PageResult<AiVideoDO> getVideoPageMy(Long userId, AiVideoPageReqVO pageReqVO) {
        return videoMapper.selectPageMy(userId, pageReqVO);
    }

    @Override
    public PageResult<AiVideoDO> getVideoPage(AiVideoPageReqVO pageReqVO) {
        return videoMapper.selectPage(pageReqVO);
    }

    @Override
    public void deleteVideo(Long userId, Long id) {
        // 校验视频存在且属于当前用户
        AiVideoDO video = videoMapper.selectById(id);
        if (video == null) {
            throw exception(VIDEO_NOT_EXISTS);
        }
        if (!video.getUserId().equals(userId)) {
            throw exception(VIDEO_NOT_EXISTS);
        }
        videoMapper.deleteById(id);
    }

    @Override
    public void deleteVideo(Long id) {
        // 校验视频存在
        AiVideoDO video = videoMapper.selectById(id);
        if (video == null) {
            throw exception(VIDEO_NOT_EXISTS);
        }
        videoMapper.deleteById(id);
    }

    @Override
    public Integer syncVideoStatus() {
        // 1. 查询所有进行中的视频任务
        List<AiVideoDO> videos = videoMapper.selectListByStatus(
                AiVideoStatusEnum.IN_PROGRESS.getStatus());

        if (CollUtil.isEmpty(videos)) {
            return 0;
        }

        // 2. 创建云雾API客户端
        YunWuVideoApi videoApi = new YunWuVideoApi(YUNWU_BASE_URL, yunwuApiKey);

        // 3. 逐个查询状态并更新
        int count = 0;
        for (AiVideoDO video : videos) {
            // 跳过没有taskId的记录（可能还在提交中）
            if (StrUtil.isEmpty(video.getTaskId())) {
                continue;
            }

            try {
                // 查询状态
                YunWuVideoApi.VideoStatusResponse status = videoApi.getVideoStatus(video.getTaskId());
                if (status == null) {
                    continue;
                }

                // 更新状态
                updateVideoStatus(video, status);
                count++;
            } catch (Exception ex) {
                log.error("[syncVideoStatus][同步失败] videoId={}, taskId={}",
                        video.getId(), video.getTaskId(), ex);
            }
        }

        return count;
    }

    /**
     * 更新视频状态
     *
     * @param video 视频记录
     * @param status 云雾API状态响应
     */
    private void updateVideoStatus(AiVideoDO video, YunWuVideoApi.VideoStatusResponse status) {
        YunWuVideoApi.TaskStatusEnum taskStatus = YunWuVideoApi.TaskStatusEnum.of(status.getStatus());

        AiVideoDO updateDO = new AiVideoDO().setId(video.getId());

        switch (taskStatus) {
            case COMPLETED:
                // 下载视频并上传到自己的存储
                String videoUrl = uploadToStorage(status.getVideoUrl(), "video");
                String coverUrl = StrUtil.isNotEmpty(status.getCoverUrl()) ?
                        uploadToStorage(status.getCoverUrl(), "cover") : null;

                updateDO.setStatus(AiVideoStatusEnum.SUCCESS.getStatus())
                        .setVideoUrl(videoUrl)
                        .setCoverUrl(coverUrl)
                        .setFinishTime(LocalDateTime.now());

                log.info("[updateVideoStatus][视频生成成功] videoId={}, videoUrl={}",
                        video.getId(), videoUrl);
                break;

            case FAILED:
                updateDO.setStatus(AiVideoStatusEnum.FAIL.getStatus())
                        .setErrorMessage(status.getErrorMessage())
                        .setFinishTime(LocalDateTime.now());

                log.warn("[updateVideoStatus][视频生成失败] videoId={}, error={}",
                        video.getId(), status.getErrorMessage());
                break;

            default:
                // PENDING / PROCESSING - 不更新状态，等下次同步
                log.debug("[updateVideoStatus][视频生成中] videoId={}, status={}, progress={}",
                        video.getId(), status.getStatus(), status.getProgress());
                return;
        }

        videoMapper.updateById(updateDO);
    }

    /**
     * 上传到自己的存储服务
     *
     * @param url 文件URL
     * @param type 文件类型（用于日志）
     * @return 存储后的URL
     */
    private String uploadToStorage(String url, String type) {
        if (StrUtil.isEmpty(url)) {
            return null;
        }
        try {
            byte[] content = HttpUtil.downloadBytes(url);
            return fileApi.createFile(content);
        } catch (Exception ex) {
            log.error("[uploadToStorage][上传{}失败] url={}", type, url, ex);
            return url; // 失败则返回原URL
        }
    }

    /**
     * 获取自身代理对象（用于调用@Async方法）
     */
    private AiVideoServiceImpl getSelf() {
        return SpringUtil.getBean(getClass());
    }

}
