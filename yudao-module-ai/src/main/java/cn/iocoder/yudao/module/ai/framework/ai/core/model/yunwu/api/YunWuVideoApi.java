package cn.iocoder.yudao.module.ai.framework.ai.core.model.yunwu.api;

import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 云雾API - Sora2 视频生成客户端
 *
 * 文档地址: https://yunwu.apifox.cn/
 * 支持模型: sora-2, sora-2-pro, sora-all
 *
 * @author ruoyi
 */
@Slf4j
public class YunWuVideoApi {

    private static final String DEFAULT_BASE_URL = "https://yunwu.ai";

    private final Predicate<HttpStatusCode> STATUS_PREDICATE = status -> !status.is2xxSuccessful();

    private final Function<Object, Function<ClientResponse, Mono<? extends Throwable>>> EXCEPTION_FUNCTION =
            reqParam -> response -> response.bodyToMono(String.class).handle((responseBody, sink) -> {
                HttpRequest request = response.request();
                log.error("[yunwu-video-api] 调用失败！请求方式:[{}]，请求地址:[{}]，请求参数:[{}]，响应数据: [{}]",
                        request.getMethod(), request.getURI(), reqParam, responseBody);
                sink.error(new IllegalStateException("[yunwu-video-api] 调用失败！响应: " + responseBody));
            });

    private final WebClient webClient;

    public YunWuVideoApi(String apiKey) {
        this(DEFAULT_BASE_URL, apiKey);
    }

    public YunWuVideoApi(String baseUrl, String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.setBearerAuth(apiKey);
                })
                .build();
    }

    /**
     * 创建视频生成任务
     * POST /v1/video/create
     *
     * @param request 请求参数
     * @return 任务信息
     */
    public VideoCreateResponse createVideo(VideoCreateRequest request) {
        String response = post("/v1/video/create", request);
        return JsonUtils.parseObject(response, VideoCreateResponse.class);
    }

    /**
     * 查询视频生成状态
     * GET /v1/video/status?task_id=xxx
     *
     * @param taskId 任务ID
     * @return 状态信息
     */
    public VideoStatusResponse getVideoStatus(String taskId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/video/status")
                        .queryParam("task_id", taskId)
                        .build())
                .retrieve()
                .onStatus(STATUS_PREDICATE, EXCEPTION_FUNCTION.apply(taskId))
                .bodyToMono(String.class)
                .map(response -> JsonUtils.parseObject(response, VideoStatusResponse.class))
                .block();
    }

    /**
     * 通过GET参数获取视频结果 (备用接口)
     * GET /v1/video/get?ids=xxx
     *
     * @param taskIds 任务ID列表
     * @return 视频信息列表
     */
    public List<VideoStatusResponse> getVideoList(List<String> taskIds) {
        String ids = String.join(",", taskIds);
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/video/get")
                        .queryParam("ids", ids)
                        .build())
                .retrieve()
                .onStatus(STATUS_PREDICATE, EXCEPTION_FUNCTION.apply(taskIds))
                .bodyToMono(String.class)
                .block();
        return JsonUtils.parseArray(response, VideoStatusResponse.class);
    }

    private String post(String uri, Object body) {
        return webClient.post()
                .uri(uri)
                .body(Mono.just(JsonUtils.toJsonString(body)), String.class)
                .retrieve()
                .onStatus(STATUS_PREDICATE, EXCEPTION_FUNCTION.apply(body))
                .bodyToMono(String.class)
                .block();
    }

    // ==================== 请求/响应对象 ====================

    /**
     * 视频创建请求
     */
    @Data
    @Builder
    public static class VideoCreateRequest {

        /**
         * 模型名称: sora-2, sora-2-pro, sora-all
         */
        private String model;

        /**
         * 提示词
         */
        private String prompt;

        /**
         * 视频时长(秒): 15 或 25
         */
        private Integer duration;

        /**
         * 分辨率: large = 1080p
         */
        private String size;

        /**
         * 方向: portrait(竖屏) / landscape(横屏)
         */
        private String orientation;

        /**
         * 参考图片链接数组(图生视频)
         */
        private List<String> images;

        /**
         * 是否添加水印
         */
        private Boolean watermark;

        /**
         * 是否私有
         */
        @JsonProperty("private")
        private Boolean isPrivate;

    }

    /**
     * 视频创建响应
     */
    @Data
    public static class VideoCreateResponse {

        /**
         * 任务ID
         */
        @JsonProperty("task_id")
        private String taskId;

        /**
         * 状态
         */
        private String status;

        /**
         * 创建时间戳
         */
        @JsonProperty("create_time")
        private Long createTime;

        /**
         * 消耗的token
         */
        @JsonProperty("tokens_used")
        private Integer tokensUsed;

        /**
         * 错误码
         */
        private String code;

        /**
         * 错误信息
         */
        private String message;

    }

    /**
     * 视频状态响应
     */
    @Data
    public static class VideoStatusResponse {

        /**
         * 任务ID
         */
        @JsonProperty("task_id")
        private String taskId;

        /**
         * 状态: pending/processing/completed/failed
         */
        private String status;

        /**
         * 视频URL
         */
        @JsonProperty("video_url")
        private String videoUrl;

        /**
         * 封面URL
         */
        @JsonProperty("cover_url")
        private String coverUrl;

        /**
         * 进度百分比 0-100
         */
        private Integer progress;

        /**
         * 错误信息
         */
        @JsonProperty("error_message")
        private String errorMessage;

        /**
         * 创建时间
         */
        @JsonProperty("create_time")
        private Long createTime;

        /**
         * 完成时间
         */
        @JsonProperty("finish_time")
        private Long finishTime;

    }

    // ==================== 枚举 ====================

    /**
     * 模型枚举
     */
    @Getter
    @AllArgsConstructor
    public enum ModelEnum {

        SORA_2("sora-2", "Sora2 标准版"),
        SORA_2_PRO("sora-2-pro", "Sora2 Pro高清版"),
        SORA_ALL("sora-all", "Sora All");

        private final String model;
        private final String name;

        public static ModelEnum of(String model) {
            for (ModelEnum value : values()) {
                if (value.getModel().equals(model)) {
                    return value;
                }
            }
            return SORA_2; // 默认
        }

    }

    /**
     * 视频任务状态枚举
     */
    @Getter
    @AllArgsConstructor
    public enum TaskStatusEnum {

        PENDING("pending", "等待中"),
        PROCESSING("processing", "处理中"),
        COMPLETED("completed", "已完成"),
        FAILED("failed", "失败");

        private final String status;
        private final String name;

        public static TaskStatusEnum of(String status) {
            for (TaskStatusEnum value : values()) {
                if (value.getStatus().equals(status)) {
                    return value;
                }
            }
            return PENDING;
        }

    }

    /**
     * 视频方向枚举
     */
    @Getter
    @AllArgsConstructor
    public enum OrientationEnum {

        LANDSCAPE("landscape", "横屏 16:9"),
        PORTRAIT("portrait", "竖屏 9:16");

        private final String value;
        private final String name;

    }

    /**
     * 视频时长枚举
     */
    @Getter
    @AllArgsConstructor
    public enum DurationEnum {

        SHORT(15, "15秒"),
        LONG(25, "25秒");

        private final Integer value;
        private final String name;

    }

}
