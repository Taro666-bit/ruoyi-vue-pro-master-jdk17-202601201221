<script setup lang="ts">
import { ref } from 'vue'
import { Upload, User } from '@element-plus/icons-vue'

const modelSourceType = ref('local')
const clothesDesc = ref('')
</script>

<template>
  <div class="model-change">
    <div class="page-banner">
      <div class="banner-content">
        <span class="banner-tag">AI模特换装</span>
        <div class="banner-info">
          <img src="https://picsum.photos/80/100?random=607" alt="" class="banner-img" />
          <div class="banner-text">
            <h3>模特智能换装</h3>
            <p>上传模特图和服装图，AI自动换装</p>
          </div>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">👤</span>
        <span class="section-title">上传人像图片</span>
      </div>
      <p class="section-desc">人像图片</p>

      <div class="source-tabs">
        <button class="tab-btn" :class="{ active: modelSourceType === 'local' }" @click="modelSourceType = 'local'">
          <el-icon><Upload /></el-icon>
          <span>本地上传</span>
        </button>
        <button class="tab-btn" :class="{ active: modelSourceType === 'model' }" @click="modelSourceType = 'model'">
          <el-icon><User /></el-icon>
          <span>模特库</span>
        </button>
      </div>

      <div class="upload-area">
        <div class="upload-placeholder">
          <el-icon class="upload-icon"><Upload /></el-icon>
          <p class="upload-title">上传人像图片</p>
          <p class="upload-hint">支持 JPG, PNG 格式</p>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">👗</span>
        <span class="section-title">服装描述</span>
      </div>

      <div class="desc-input">
        <textarea
          v-model="clothesDesc"
          placeholder="例如：黑色西装配白色衬衫..."
          rows="3"
        ></textarea>
      </div>

      <div class="divider">或</div>

      <div class="section-sub-header">服装图片</div>
      <div class="upload-area">
        <div class="upload-placeholder small">
          <el-icon class="upload-icon"><Upload /></el-icon>
          <p class="upload-title">上传服装图片</p>
          <p class="upload-hint">JPG, PNG 格式</p>
        </div>
      </div>
    </div>

    <button class="generate-btn">立即生成</button>
  </div>
</template>

<style lang="scss" scoped>
.model-change {
  max-width: 400px;
}

.page-banner {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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

    .banner-info {
      display: flex;
      gap: 12px;

      .banner-img { width: 60px; height: 80px; object-fit: cover; border-radius: 8px; }
      .banner-text {
        color: #fff;
        h3 { font-size: 16px; font-weight: 600; margin: 0 0 4px; }
        p { font-size: 12px; opacity: 0.9; margin: 0; }
      }
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
    margin-bottom: 8px;

    .section-icon { font-size: 16px; }
    .section-title { font-size: 14px; font-weight: 600; color: #1f2937; }
  }

  .section-desc {
    font-size: 13px;
    color: #6b7280;
    margin: 0 0 12px;
  }

  .section-sub-header {
    font-size: 13px;
    font-weight: 500;
    color: #374151;
    margin-bottom: 12px;
  }

  .source-tabs {
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

      &:hover { border-color: #a855f7; color: #a855f7; }
      &.active { background: #1f2937; color: #fff; border-color: #1f2937; }
    }
  }

  .upload-area {
    .upload-placeholder {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 40px 20px;
      border: 2px dashed #e5e7eb;
      border-radius: 12px;
      text-align: center;
      cursor: pointer;

      &:hover { border-color: #a855f7; }
      &.small { padding: 20px; }

      .upload-icon { font-size: 32px; color: #9ca3af; margin-bottom: 12px; }
      .upload-title { font-size: 14px; color: #6b7280; margin: 0 0 4px; }
      .upload-hint { font-size: 12px; color: #9ca3af; margin: 0; }
    }
  }

  .desc-input {
    textarea {
      width: 100%;
      padding: 12px;
      border: 1px solid #e5e7eb;
      border-radius: 8px;
      font-size: 14px;
      resize: none;
      outline: none;
      font-family: inherit;

      &:focus { border-color: #a855f7; }
      &::placeholder { color: #9ca3af; }
    }
  }

  .divider {
    text-align: center;
    color: #9ca3af;
    font-size: 13px;
    margin: 16px 0;
    position: relative;

    &::before,
    &::after {
      content: '';
      position: absolute;
      top: 50%;
      width: 40%;
      height: 1px;
      background: #e5e7eb;
    }

    &::before { left: 0; }
    &::after { right: 0; }
  }
}

.generate-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
  color: #fff;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;

  &:hover { opacity: 0.9; }
}
</style>
