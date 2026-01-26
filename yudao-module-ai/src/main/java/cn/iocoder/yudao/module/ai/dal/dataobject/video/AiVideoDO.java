package cn.iocoder.yudao.module.ai.dal.dataobject.video;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.module.ai.dal.dataobject.model.AiModelDO;
import cn.iocoder.yudao.module.ai.enums.video.AiVideoStatusEnum;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * AI 视频生成 DO
 *
 * @author ruoyi
 */
@TableName(value = "ai_video", autoResultMap = true)
@KeySequence("ai_video_seq")
@Data
public class AiVideoDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 用户编号
     *
     * 关联 {@link AdminUserRespDTO#getId()}
     */
    private Long userId;

    // ==================== 模型信息 ====================

    /**
     * 模型编号
     *
     * 关联 {@link AiModelDO#getId()}
     */
    private Long modelId;

    /**
     * 模型标识
     *
     * 冗余 {@link AiModelDO#getModel()}
     * 如: sora-2, sora-2-pro, sora-all
     */
    private String model;

    /**
     * 平台
     *
     * 如: YunWu
     */
    private String platform;

    // ==================== 任务信息 ====================

    /**
     * 云雾API任务ID
     */
    private String taskId;

    // ==================== 生成参数 ====================

    /**
     * 提示词
     */
    private String prompt;

    /**
     * 视频时长(秒)
     *
     * 支持: 15, 25
     */
    private Integer duration;

    /**
     * 分辨率
     *
     * 如: large = 1080p
     */
    private String size;

    /**
     * 视频方向
     *
     * portrait(竖屏) / landscape(横屏)
     */
    private String orientation;

    // ==================== 输入素材 ====================

    /**
     * 参考图片URL(图生视频)
     */
    private String imageUrl;

    // ==================== 生成结果 ====================

    /**
     * 生成状态
     *
     * 枚举 {@link AiVideoStatusEnum}
     */
    private Integer status;

    /**
     * 生成的视频URL
     */
    private String videoUrl;

    /**
     * 视频封面URL
     */
    private String coverUrl;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    // ==================== 其他选项 ====================

    /**
     * 是否有水印
     */
    private Boolean watermark;

    /**
     * 是否私有
     */
    private Boolean isPrivate;

}
