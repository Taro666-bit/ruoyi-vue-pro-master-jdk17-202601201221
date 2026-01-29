package cn.iocoder.yudao.module.ai.controller.admin.video;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoGenerateReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoPageReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoRespVO;
import cn.iocoder.yudao.module.ai.dal.dataobject.model.AiModelDO;
import cn.iocoder.yudao.module.ai.dal.dataobject.video.AiVideoDO;
import cn.iocoder.yudao.module.ai.enums.model.AiModelTypeEnum;
import cn.iocoder.yudao.module.ai.enums.model.AiPlatformEnum;
import cn.iocoder.yudao.module.ai.service.model.AiModelService;
import cn.iocoder.yudao.module.ai.service.video.AiVideoService;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - AI 视频")
@RestController
@RequestMapping("/ai/video")
@Validated
public class AiVideoController {

    @Resource
    private AiVideoService videoService;

    @Resource
    private AiModelService modelService;

    // ==================== 用户端接口 ====================

    @PostMapping("/generate")
    @Operation(summary = "生成视频")
    public CommonResult<Long> generateVideo(@Valid @RequestBody AiVideoGenerateReqVO reqVO) {
        Long userId = getLoginUserId();
        return success(videoService.generateVideo(userId, reqVO));
    }

    @GetMapping("/get-my")
    @Operation(summary = "获取我的视频")
    @Parameter(name = "id", required = true, description = "视频编号", example = "1024")
    public CommonResult<AiVideoRespVO> getMyVideo(@RequestParam("id") Long id) {
        AiVideoDO video = videoService.getVideo(id);
        // 校验是否是自己的视频
        if (video != null && !video.getUserId().equals(getLoginUserId())) {
            return success(null);
        }
        return success(BeanUtils.toBean(video, AiVideoRespVO.class));
    }

    @GetMapping("/page-my")
    @Operation(summary = "获取我的视频分页")
    public CommonResult<PageResult<AiVideoRespVO>> getMyVideoPage(@Valid AiVideoPageReqVO pageReqVO) {
        Long userId = getLoginUserId();
        PageResult<AiVideoDO> pageResult = videoService.getVideoPageMy(userId, pageReqVO);
        return success(BeanUtils.toBean(pageResult, AiVideoRespVO.class));
    }

    @DeleteMapping("/delete-my")
    @Operation(summary = "删除我的视频")
    @Parameter(name = "id", required = true, description = "视频编号", example = "1024")
    public CommonResult<Boolean> deleteMyVideo(@RequestParam("id") Long id) {
        Long userId = getLoginUserId();
        videoService.deleteVideo(userId, id);
        return success(true);
    }

    // ==================== 管理端接口 ====================

    @GetMapping("/get")
    @Operation(summary = "获取视频")
    @Parameter(name = "id", required = true, description = "视频编号", example = "1024")
    @PreAuthorize("@ss.hasPermission('ai:video:query')")
    public CommonResult<AiVideoRespVO> getVideo(@RequestParam("id") Long id) {
        AiVideoDO video = videoService.getVideo(id);
        return success(BeanUtils.toBean(video, AiVideoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获取视频分页")
    @PreAuthorize("@ss.hasPermission('ai:video:query')")
    public CommonResult<PageResult<AiVideoRespVO>> getVideoPage(@Valid AiVideoPageReqVO pageReqVO) {
        PageResult<AiVideoDO> pageResult = videoService.getVideoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AiVideoRespVO.class));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除视频")
    @Parameter(name = "id", required = true, description = "视频编号", example = "1024")
    @PreAuthorize("@ss.hasPermission('ai:video:delete')")
    public CommonResult<Boolean> deleteVideo(@RequestParam("id") Long id) {
        videoService.deleteVideo(id);
        return success(true);
    }

    // ==================== 支持的模型列表 ====================

    @GetMapping("/models")
    @Operation(summary = "获取支持的视频模型列表")
    public CommonResult<List<AiVideoModelRespVO>> getVideoModels() {
        // 从数据库查询已启用的视频模型（type=4表示视频类型）
        List<AiModelDO> models = modelService.getModelListByStatusAndType(
                CommonStatusEnum.ENABLE.getStatus(),
                AiModelTypeEnum.VIDEO.getType(),
                AiPlatformEnum.YUN_WU.getPlatform()
        );
        // 转换为响应VO
        List<AiVideoModelRespVO> result = models.stream()
                .map(model -> new AiVideoModelRespVO(model.getId(), model.getModel(), model.getName()))
                .toList();
        return success(result);
    }

    /**
     * 视频模型响应VO
     */
    public record AiVideoModelRespVO(Long id, String model, String name) {
    }

}
