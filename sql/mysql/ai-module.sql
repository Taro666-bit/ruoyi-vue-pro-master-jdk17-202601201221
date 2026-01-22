-- =============================================
-- AI 模块数据库表结构 SQL
-- 根据 DO 文件自动生成
-- =============================================

-- ----------------------------
-- 1. AI API密钥表
-- ----------------------------
DROP TABLE IF EXISTS `ai_api_key`;
CREATE TABLE `ai_api_key` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `api_key` varchar(512) NOT NULL COMMENT '密钥',
  `platform` varchar(32) NOT NULL COMMENT '平台',
  `url` varchar(512) DEFAULT NULL COMMENT 'API地址',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI API密钥表';

-- ----------------------------
-- 2. AI 模型表
-- ----------------------------
DROP TABLE IF EXISTS `ai_model`;
CREATE TABLE `ai_model` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `key_id` bigint NOT NULL COMMENT 'API密钥编号',
  `name` varchar(128) NOT NULL COMMENT '模型名称',
  `model` varchar(64) NOT NULL COMMENT '模型标志',
  `platform` varchar(32) NOT NULL COMMENT '平台',
  `type` int NOT NULL COMMENT '类型',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序值',
  `status` int NOT NULL COMMENT '状态',
  `temperature` double DEFAULT NULL COMMENT '温度参数',
  `max_tokens` int DEFAULT NULL COMMENT '单条回复的最大Token数量',
  `max_contexts` int DEFAULT NULL COMMENT '上下文的最大Message数量',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_key_id` (`key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 模型表';

-- ----------------------------
-- 3. AI 聊天角色表
-- ----------------------------
DROP TABLE IF EXISTS `ai_chat_role`;
CREATE TABLE `ai_chat_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(128) NOT NULL COMMENT '角色名称',
  `avatar` varchar(512) DEFAULT NULL COMMENT '角色头像',
  `category` varchar(64) DEFAULT NULL COMMENT '角色分类',
  `description` varchar(512) DEFAULT NULL COMMENT '角色描述',
  `system_message` text DEFAULT NULL COMMENT '角色设定',
  `user_id` bigint DEFAULT NULL COMMENT '用户编号',
  `model_id` bigint DEFAULT NULL COMMENT '模型编号',
  `knowledge_ids` varchar(512) DEFAULT NULL COMMENT '引用的知识库编号列表',
  `tool_ids` varchar(512) DEFAULT NULL COMMENT '引用的工具编号列表',
  `mcp_client_names` varchar(512) DEFAULT NULL COMMENT '引用的MCP Client名字列表',
  `public_status` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否公开',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序值',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 聊天角色表';

-- ----------------------------
-- 4. AI 工具表
-- ----------------------------
DROP TABLE IF EXISTS `ai_tool`;
CREATE TABLE `ai_tool` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工具编号',
  `name` varchar(128) NOT NULL COMMENT '工具名称',
  `description` varchar(512) DEFAULT NULL COMMENT '工具描述',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 工具表';

-- ----------------------------
-- 5. AI Chat 对话表
-- ----------------------------
DROP TABLE IF EXISTS `ai_chat_conversation`;
CREATE TABLE `ai_chat_conversation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `title` varchar(256) NOT NULL DEFAULT '新对话' COMMENT '对话标题',
  `pinned` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶',
  `pinned_time` datetime DEFAULT NULL COMMENT '置顶时间',
  `role_id` bigint DEFAULT NULL COMMENT '角色编号',
  `model_id` bigint NOT NULL COMMENT '模型编号',
  `model` varchar(64) NOT NULL COMMENT '模型标志',
  `system_message` varchar(4096) DEFAULT NULL COMMENT '角色设定',
  `temperature` double DEFAULT NULL COMMENT '温度参数',
  `max_tokens` int DEFAULT NULL COMMENT '单条回复的最大Token数量',
  `max_contexts` int DEFAULT NULL COMMENT '上下文的最大Message数量',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI Chat 对话表';

-- ----------------------------
-- 6. AI Chat 消息表
-- ----------------------------
DROP TABLE IF EXISTS `ai_chat_message`;
CREATE TABLE `ai_chat_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `conversation_id` bigint NOT NULL COMMENT '对话编号',
  `reply_id` bigint DEFAULT NULL COMMENT '回复消息编号',
  `type` varchar(32) NOT NULL COMMENT '消息类型',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `role_id` bigint DEFAULT NULL COMMENT '角色编号',
  `model` varchar(64) DEFAULT NULL COMMENT '模型标志',
  `model_id` bigint DEFAULT NULL COMMENT '模型编号',
  `content` text NOT NULL COMMENT '聊天内容',
  `reasoning_content` text DEFAULT NULL COMMENT '推理内容',
  `use_context` bit(1) DEFAULT b'1' COMMENT '是否携带上下文',
  `segment_ids` varchar(1024) DEFAULT NULL COMMENT '知识库段落编号数组',
  `web_search_pages` text DEFAULT NULL COMMENT '联网搜索的网页内容数组',
  `attachment_urls` varchar(2048) DEFAULT NULL COMMENT '附件URL数组',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_conversation_id` (`conversation_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI Chat 消息表';

-- ----------------------------
-- 7. AI 绘画表
-- ----------------------------
DROP TABLE IF EXISTS `ai_image`;
CREATE TABLE `ai_image` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `prompt` text NOT NULL COMMENT '提示词',
  `platform` varchar(32) NOT NULL COMMENT '平台',
  `model_id` bigint DEFAULT NULL COMMENT '模型编号',
  `model` varchar(64) DEFAULT NULL COMMENT '模型标识',
  `width` int DEFAULT NULL COMMENT '图片宽度',
  `height` int DEFAULT NULL COMMENT '图片高度',
  `status` int NOT NULL COMMENT '生成状态',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `error_message` varchar(1024) DEFAULT NULL COMMENT '绘画错误信息',
  `pic_url` varchar(1024) DEFAULT NULL COMMENT '图片地址',
  `public_status` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否公开',
  `options` text DEFAULT NULL COMMENT '绘制参数JSON',
  `buttons` text DEFAULT NULL COMMENT 'MJ buttons按钮JSON',
  `task_id` varchar(128) DEFAULT NULL COMMENT '任务编号',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 绘画表';

-- ----------------------------
-- 8. AI 知识库表
-- ----------------------------
DROP TABLE IF EXISTS `ai_knowledge`;
CREATE TABLE `ai_knowledge` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(128) NOT NULL COMMENT '知识库名称',
  `description` varchar(512) DEFAULT NULL COMMENT '知识库描述',
  `embedding_model_id` bigint NOT NULL COMMENT '向量模型编号',
  `embedding_model` varchar(64) NOT NULL COMMENT '模型标识',
  `top_k` int NOT NULL DEFAULT 3 COMMENT 'topK',
  `similarity_threshold` double NOT NULL DEFAULT 0.5 COMMENT '相似度阈值',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 知识库表';

-- ----------------------------
-- 9. AI 知识库文档表
-- ----------------------------
DROP TABLE IF EXISTS `ai_knowledge_document`;
CREATE TABLE `ai_knowledge_document` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `knowledge_id` bigint NOT NULL COMMENT '知识库编号',
  `name` varchar(256) NOT NULL COMMENT '文档名称',
  `url` varchar(1024) DEFAULT NULL COMMENT '文件URL',
  `content` longtext DEFAULT NULL COMMENT '内容',
  `content_length` int DEFAULT NULL COMMENT '文档长度',
  `tokens` int DEFAULT NULL COMMENT '文档token数量',
  `segment_max_tokens` int DEFAULT NULL COMMENT '分片最大Token数',
  `retrieval_count` int NOT NULL DEFAULT 0 COMMENT '召回次数',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_knowledge_id` (`knowledge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 知识库文档表';

-- ----------------------------
-- 10. AI 知识库文档分段表
-- ----------------------------
DROP TABLE IF EXISTS `ai_knowledge_segment`;
CREATE TABLE `ai_knowledge_segment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `knowledge_id` bigint NOT NULL COMMENT '知识库编号',
  `document_id` bigint NOT NULL COMMENT '文档编号',
  `content` text NOT NULL COMMENT '切片内容',
  `content_length` int DEFAULT NULL COMMENT '切片内容长度',
  `vector_id` varchar(128) DEFAULT '' COMMENT '向量库的编号',
  `tokens` int DEFAULT NULL COMMENT 'token数量',
  `retrieval_count` int NOT NULL DEFAULT 0 COMMENT '召回次数',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_knowledge_id` (`knowledge_id`),
  KEY `idx_document_id` (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 知识库文档分段表';

-- ----------------------------
-- 11. AI 思维导图表
-- ----------------------------
DROP TABLE IF EXISTS `ai_mind_map`;
CREATE TABLE `ai_mind_map` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `platform` varchar(32) NOT NULL COMMENT '平台',
  `model_id` bigint DEFAULT NULL COMMENT '模型编号',
  `model` varchar(64) DEFAULT NULL COMMENT '模型',
  `prompt` text NOT NULL COMMENT '生成内容提示',
  `generated_content` text DEFAULT NULL COMMENT '生成的内容',
  `error_message` varchar(1024) DEFAULT NULL COMMENT '错误信息',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 思维导图表';

-- ----------------------------
-- 12. AI 音乐表
-- ----------------------------
DROP TABLE IF EXISTS `ai_music`;
CREATE TABLE `ai_music` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `title` varchar(256) DEFAULT NULL COMMENT '音乐名称',
  `lyric` text DEFAULT NULL COMMENT '歌词',
  `image_url` varchar(1024) DEFAULT NULL COMMENT '图片地址',
  `audio_url` varchar(1024) DEFAULT NULL COMMENT '音频地址',
  `video_url` varchar(1024) DEFAULT NULL COMMENT '视频地址',
  `status` int NOT NULL COMMENT '音乐状态',
  `generate_mode` int DEFAULT NULL COMMENT '生成模式',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述词',
  `platform` varchar(32) NOT NULL COMMENT '平台',
  `model` varchar(64) DEFAULT NULL COMMENT '模型',
  `tags` varchar(512) DEFAULT NULL COMMENT '音乐风格标签JSON',
  `duration` double DEFAULT NULL COMMENT '音乐时长',
  `public_status` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否公开',
  `task_id` varchar(128) DEFAULT NULL COMMENT '任务编号',
  `error_message` varchar(1024) DEFAULT NULL COMMENT '错误信息',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 音乐表';

-- ----------------------------
-- 13. AI 工作流表
-- ----------------------------
DROP TABLE IF EXISTS `ai_workflow`;
CREATE TABLE `ai_workflow` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(128) NOT NULL COMMENT '工作流名称',
  `code` varchar(64) NOT NULL COMMENT '工作流标识',
  `graph` text DEFAULT NULL COMMENT '工作流模型JSON数据',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` int NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 工作流表';

-- ----------------------------
-- 14. AI 写作表
-- ----------------------------
DROP TABLE IF EXISTS `ai_write`;
CREATE TABLE `ai_write` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `type` int NOT NULL COMMENT '写作类型',
  `platform` varchar(32) NOT NULL COMMENT '平台',
  `model_id` bigint DEFAULT NULL COMMENT '模型编号',
  `model` varchar(64) DEFAULT NULL COMMENT '模型',
  `prompt` text NOT NULL COMMENT '生成内容提示',
  `generated_content` text DEFAULT NULL COMMENT '生成的内容',
  `original_content` text DEFAULT NULL COMMENT '原文',
  `length` int DEFAULT NULL COMMENT '长度提示词',
  `format` int DEFAULT NULL COMMENT '格式提示词',
  `tone` int DEFAULT NULL COMMENT '语气提示词',
  `language` int DEFAULT NULL COMMENT '语言提示词',
  `error_message` varchar(1024) DEFAULT NULL COMMENT '错误信息',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI 写作表';
