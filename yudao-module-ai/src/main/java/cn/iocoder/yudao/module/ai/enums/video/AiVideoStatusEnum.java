package cn.iocoder.yudao.module.ai.enums.video;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI 视频生成状态的枚举
 *
 * @author ruoyi
 */
@AllArgsConstructor
@Getter
public enum AiVideoStatusEnum {

    IN_PROGRESS(10, "进行中"),
    SUCCESS(20, "已完成"),
    FAIL(30, "已失败");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    public static AiVideoStatusEnum valueOfStatus(Integer status) {
        for (AiVideoStatusEnum statusEnum : AiVideoStatusEnum.values()) {
            if (statusEnum.getStatus().equals(status)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("未知视频状态： " + status);
    }

}
