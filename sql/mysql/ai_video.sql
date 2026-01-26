-- ----------------------------
-- AI 视频生成表
-- ----------------------------
DROP TABLE IF EXISTS `ai_video`;
CREATE TABLE `ai_video` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',

  -- 模型信息
  `model_id` bigint DEFAULT NULL COMMENT '模型编号(关联ai_model)',
  `model` varchar(64) NOT NULL DEFAULT 'sora-2' COMMENT '模型标识(sora-2/sora-2-pro/sora-all)',
  `platform` varchar(32) NOT NULL DEFAULT 'YunWu' COMMENT '平台',

  -- 任务信息
  `task_id` varchar(128) DEFAULT NULL COMMENT '云雾API任务ID',

  -- 生成参数
  `prompt` varchar(2000) NOT NULL COMMENT '提示词',
  `duration` int NOT NULL DEFAULT 15 COMMENT '视频时长(秒): 15或25',
  `size` varchar(32) DEFAULT 'large' COMMENT '分辨率: large=1080p',
  `orientation` varchar(16) DEFAULT 'landscape' COMMENT '方向: portrait(竖屏)/landscape(横屏)',

  -- 输入素材(图生视频)
  `image_url` varchar(1024) DEFAULT NULL COMMENT '参考图片URL',

  -- 生成结果
  `status` tinyint NOT NULL DEFAULT 10 COMMENT '状态: 10进行中 20成功 30失败',
  `video_url` varchar(1024) DEFAULT NULL COMMENT '生成的视频URL',
  `cover_url` varchar(1024) DEFAULT NULL COMMENT '视频封面URL',
  `error_message` varchar(512) DEFAULT NULL COMMENT '错误信息',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',

  -- 其他选项
  `watermark` bit(1) DEFAULT b'1' COMMENT '是否有水印',
  `is_private` bit(1) DEFAULT b'0' COMMENT '是否私有',

  -- 通用字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_task_id` (`task_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 视频生成表';

-- ----------------------------
-- 初始化云雾API配置（需要手动执行或通过后台添加）
-- 注意：请将 your-api-key 替换为实际的 API Key
-- ----------------------------
-- INSERT INTO `ai_api_key` (`name`, `api_key`, `platform`, `url`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
-- VALUES ('云雾API-Sora', 'sk-lLqS0EeXrwzieSmF6KqOBwyHAalENKG9FFnMKn0NG7NtlOkW', 'YunWu', 'https://yunwu.ai', 0, 'admin', NOW(), 'admin', NOW(), b'0', 1);

-- 添加 Sora2 模型配置
-- INSERT INTO `ai_model` (`key_id`, `name`, `model`, `platform`, `type`, `sort`, `status`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
-- VALUES
-- ((SELECT id FROM ai_api_key WHERE name = '云雾API-Sora' LIMIT 1), 'Sora2 标准版', 'sora-2', 'YunWu', 4, 1, 0, 'admin', NOW(), 'admin', NOW(), b'0', 1),
-- ((SELECT id FROM ai_api_key WHERE name = '云雾API-Sora' LIMIT 1), 'Sora2 Pro高清版', 'sora-2-pro', 'YunWu', 4, 2, 0, 'admin', NOW(), 'admin', NOW(), b'0', 1),
-- ((SELECT id FROM ai_api_key WHERE name = '云雾API-Sora' LIMIT 1), 'Sora All', 'sora-all', 'YunWu', 4, 3, 0, 'admin', NOW(), 'admin', NOW(), b'0', 1);
