package cn.iocoder.yudao.module.ai.controller.admin.video.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "管理后台 - AI 视频生成 Request VO")
@Data
public class AiVideoGenerateReqVO {

    @Schema(description = "模型标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "sora-2")
    @NotEmpty(message = "模型标识不能为空")
    private String model;

    @Schema(description = "提示词", requiredMode = Schema.RequiredMode.REQUIRED, example = "一个穿着红色汉服的女孩在樱花树下漫步")
    @NotEmpty(message = "提示词不能为空")
    @Size(max = 2000, message = "提示词最大 2000 字符")
    private String prompt;

    @Schema(description = "视频时长(秒)", example = "15")
    @NotNull(message = "视频时长不能为空")
    private Integer duration;

    @Schema(description = "视频方向: landscape(横屏)/portrait(竖屏)", example = "landscape")
    private String orientation;

    @Schema(description = "参考图片URL(图生视频)", example = "https://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "是否添加水印", example = "false")
    private Boolean watermark;

}
