package cn.iocoder.yudao.module.ai.service.video;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoGenerateReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoPageReqVO;
import cn.iocoder.yudao.module.ai.dal.dataobject.video.AiVideoDO;

/**
 * AI 视频 Service 接口
 *
 * @author ruoyi
 */
public interface AiVideoService {

    /**
     * 生成视频
     *
     * @param userId 用户编号
     * @param reqVO 请求参数
     * @return 视频编号
     */
    Long generateVideo(Long userId, AiVideoGenerateReqVO reqVO);

    /**
     * 获取视频
     *
     * @param id 视频编号
     * @return 视频
     */
    AiVideoDO getVideo(Long id);

    /**
     * 获取用户的视频分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页参数
     * @return 视频分页
     */
    PageResult<AiVideoDO> getVideoPageMy(Long userId, AiVideoPageReqVO pageReqVO);

    /**
     * 获取视频分页（管理员）
     *
     * @param pageReqVO 分页参数
     * @return 视频分页
     */
    PageResult<AiVideoDO> getVideoPage(AiVideoPageReqVO pageReqVO);

    /**
     * 删除视频
     *
     * @param userId 用户编号
     * @param id 视频编号
     */
    void deleteVideo(Long userId, Long id);

    /**
     * 删除视频（管理员）
     *
     * @param id 视频编号
     */
    void deleteVideo(Long id);

    /**
     * 同步视频状态
     *
     * 定时任务调用，同步云雾API的视频生成状态
     *
     * @return 同步数量
     */
    Integer syncVideoStatus();

}
