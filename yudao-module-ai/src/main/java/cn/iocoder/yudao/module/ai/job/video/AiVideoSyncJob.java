package cn.iocoder.yudao.module.ai.job.video;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import cn.iocoder.yudao.module.ai.service.video.AiVideoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * AI 视频状态同步 Job
 *
 * 定期同步云雾API的视频生成状态
 * 建议执行频率: 每 30 秒执行一次
 *
 * @author ruoyi
 */
@Component
@Slf4j
public class AiVideoSyncJob implements JobHandler {

    @Resource
    private AiVideoService videoService;

    @Override
    public String execute(String param) {
        Integer count = videoService.syncVideoStatus();
        log.info("[execute][同步 AI 视频状态 ({}) 个]", count);
        return String.format("同步视频 %s 个", count);
    }

}
