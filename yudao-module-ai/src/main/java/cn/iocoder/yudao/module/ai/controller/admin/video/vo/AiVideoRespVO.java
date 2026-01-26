package cn.iocoder.yudao.module.ai.controller.admin.video.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - AI 视频 Response VO")
@Data
public class AiVideoRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "平台", requiredMode = Schema.RequiredMode.REQUIRED, example = "YunWu")
    private String platform;

    @Schema(description = "模型标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "sora-2")
    private String model;

    @Schema(description = "提示词", requiredMode = Schema.RequiredMode.REQUIRED, example = "一个穿着红色汉服的女孩")
    private String prompt;

    @Schema(description = "视频时长(秒)", example = "15")
    private Integer duration;

    @Schema(description = "视频方向", example = "landscape")
    private String orientation;

    @Schema(description = "参考图片URL", example = "https://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "生成状态: 10进行中 20成功 30失败", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    private Integer status;

    @Schema(description = "生成的视频URL", example = "https://example.com/video.mp4")
    private String videoUrl;

    @Schema(description = "视频封面URL", example = "https://example.com/cover.jpg")
    private String coverUrl;

    @Schema(description = "错误信息", example = "生成失败")
    private String errorMessage;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
