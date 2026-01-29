<script setup lang="ts">
import { ref } from 'vue'
import { Upload, Link, VideoCamera, Document } from '@element-plus/icons-vue'

// 输入方式
const inputType = ref('video') // video | tiktok | text

// 脚本名称
const scriptName = ref('')
</script>

<template>
  <div class="ai-script-page">
    <div class="page-content">
      <!-- 左侧表单 -->
      <div class="form-area">
        <!-- 页面标题Banner -->
        <div class="page-banner">
          <div class="banner-content">
            <span class="banner-tag">AI脚本拆解：卖点智能提取</span>
            <div class="banner-icons">
              <span class="icon-item">📹</span>
              <span class="icon-item">📝</span>
              <span class="icon-item">🤖</span>
              <span class="icon-item">✨</span>
            </div>
            <p class="banner-desc">上传视频/输入文本，AI智能分析脚本</p>
          </div>
        </div>

        <!-- 脚本名称 -->
        <div class="form-section">
          <div class="section-header">
            <span class="section-icon">📝</span>
            <span class="section-title">脚本名称 *</span>
          </div>
          <input
            v-model="scriptName"
            type="text"
            class="name-input"
            placeholder="请输入脚本名称..."
          />
        </div>

        <!-- 输入方式 -->
        <div class="form-section">
          <div class="section-header">
            <span class="section-icon">📥</span>
            <span class="section-title">输入方式</span>
          </div>

          <div class="input-tabs">
            <button
              class="tab-btn"
              :class="{ active: inputType === 'video' }"
              @click="inputType = 'video'"
            >
              <el-icon><Upload /></el-icon>
              <span>视频上传</span>
            </button>
            <button
              class="tab-btn"
              :class="{ active: inputType === 'tiktok' }"
              @click="inputType = 'tiktok'"
            >
              <el-icon><Link /></el-icon>
              <span>TikTok链接</span>
            </button>
            <button
              class="tab-btn"
              :class="{ active: inputType === 'text' }"
              @click="inputType = 'text'"
            >
              <el-icon><Document /></el-icon>
              <span>文本输入</span>
            </button>
          </div>

          <!-- 视频上传 -->
          <div v-if="inputType === 'video'" class="upload-area">
            <p class="upload-label">上传视频</p>
            <div class="upload-placeholder">
              <el-icon class="upload-icon"><Upload /></el-icon>
              <p class="upload-title">点击上传视频文件</p>
              <p class="upload-hint">支持 MP4 格式</p>
            </div>
          </div>

          <!-- TikTok链接 -->
          <div v-if="inputType === 'tiktok'" class="link-input-area">
            <p class="input-label">TikTok视频链接</p>
            <input type="text" class="link-input" placeholder="粘贴TikTok视频链接..." />
          </div>

          <!-- 文本输入 -->
          <div v-if="inputType === 'text'" class="text-input-area">
            <p class="input-label">脚本文本</p>
            <textarea class="text-input" placeholder="输入脚本内容..." rows="6"></textarea>
          </div>
        </div>

        <!-- 提交按钮 -->
        <button class="submit-btn">
          开始分析
        </button>
      </div>

      <!-- 右侧结果区 -->
      <div class="result-area">
        <div class="result-header">
          <div class="result-tabs">
            <button class="result-tab active">
              <el-icon><Document /></el-icon>
              <span>脚本详情</span>
            </button>
            <button class="result-tab">
              <el-icon><Document /></el-icon>
              <span>脚本列表</span>
            </button>
          </div>
        </div>

        <div class="result-content">
          <div class="empty-state">
            <el-icon class="empty-icon"><Document /></el-icon>
            <p class="empty-title">暂无拆解结果</p>
            <p class="empty-desc">上传视频或输入文本开始分析</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.ai-script-page {
  min-height: calc(100vh - 65px);
}

.page-content {
  display: flex;
  gap: 24px;
  height: 100%;
}

.form-area {
  width: 380px;
  flex-shrink: 0;
}

.page-banner {
  background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 24px;

  .banner-content {
    .banner-tag {
      display: inline-block;
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      font-size: 12px;
      padding: 4px 10px;
      border-radius: 4px;
      margin-bottom: 12px;
    }

    .banner-icons {
      display: flex;
      gap: 12px;
      margin-bottom: 12px;

      .icon-item {
        width: 36px;
        height: 36px;
        background: rgba(255, 255, 255, 0.2);
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 18px;
      }
    }

    .banner-desc {
      color: #fff;
      font-size: 12px;
      opacity: 0.9;
      margin: 0;
    }
  }
}

.form-section {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 16px;

  .section-header {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 16px;

    .section-icon { font-size: 16px; }
    .section-title { font-size: 14px; font-weight: 600; color: #1f2937; }
  }

  .name-input {
    width: 100%;
    padding: 10px 14px;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    font-size: 14px;
    outline: none;

    &:focus { border-color: #f97316; }
    &::placeholder { color: #9ca3af; }
  }

  .input-tabs {
    display: flex;
    gap: 8px;
    margin-bottom: 16px;

    .tab-btn {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 8px 16px;
      border: 1px solid #e5e7eb;
      border-radius: 8px;
      font-size: 13px;
      color: #6b7280;
      background: #fff;
      cursor: pointer;

      &:hover { border-color: #f97316; color: #f97316; }
      &.active { background: #1f2937; color: #fff; border-color: #1f2937; }
    }
  }

  .upload-area,
  .link-input-area,
  .text-input-area {
    .upload-label,
    .input-label {
      font-size: 13px;
      color: #6b7280;
      margin: 0 0 12px;
    }
  }

  .upload-placeholder {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 40px 20px;
    border: 2px dashed #e5e7eb;
    border-radius: 12px;
    text-align: center;
    cursor: pointer;

    &:hover { border-color: #f97316; }

    .upload-icon { font-size: 32px; color: #9ca3af; margin-bottom: 12px; }
    .upload-title { font-size: 14px; color: #6b7280; margin: 0 0 4px; }
    .upload-hint { font-size: 12px; color: #9ca3af; margin: 0; }
  }

  .link-input {
    width: 100%;
    padding: 10px 14px;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    font-size: 14px;
    outline: none;

    &:focus { border-color: #f97316; }
    &::placeholder { color: #9ca3af; }
  }

  .text-input {
    width: 100%;
    padding: 12px;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    font-size: 14px;
    resize: none;
    outline: none;
    font-family: inherit;

    &:focus { border-color: #f97316; }
    &::placeholder { color: #9ca3af; }
  }
}

.submit-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
  color: #fff;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;

  &:hover { opacity: 0.9; }
}

.result-area {
  flex: 1;
  background: #fff;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .result-header {
    padding: 16px 20px;
    border-bottom: 1px solid #e5e7eb;

    .result-tabs {
      display: flex;
      gap: 16px;

      .result-tab {
        display: flex;
        align-items: center;
        gap: 6px;
        padding: 8px 0;
        font-size: 14px;
        color: #6b7280;
        background: none;
        cursor: pointer;

        &.active {
          color: #f97316;
          font-weight: 500;
        }
      }
    }
  }

  .result-content {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;

    .empty-state {
      text-align: center;
      color: #9ca3af;

      .empty-icon { font-size: 48px; margin-bottom: 16px; }
      .empty-title { font-size: 16px; margin: 0 0 8px; color: #6b7280; }
      .empty-desc { font-size: 14px; margin: 0; }
    }
  }
}
</style>
