package cn.iocoder.yudao.module.ai.controller.admin.video.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AI 视频分页 Request VO")
@Data
public class AiVideoPageReqVO extends PageParam {

    @Schema(description = "用户编号", example = "28987")
    private Long userId;

    @Schema(description = "平台", example = "YunWu")
    private String platform;

    @Schema(description = "提示词", example = "汉服女孩")
    private String prompt;

    @Schema(description = "生成状态: 10进行中 20成功 30失败", example = "20")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
