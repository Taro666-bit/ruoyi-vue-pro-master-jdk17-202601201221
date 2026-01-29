<script setup lang="ts">
import { ref } from 'vue'
import { Upload, User } from '@element-plus/icons-vue'

const modelSourceType = ref('local')

const scenes = ref([
  { id: 1, name: '商务办公', thumbnail: 'https://picsum.photos/100/80?random=801' },
  { id: 2, name: '户外街景', thumbnail: 'https://picsum.photos/100/80?random=802' },
  { id: 3, name: '咖啡厅', thumbnail: 'https://picsum.photos/100/80?random=803' },
  { id: 4, name: '海边沙滩', thumbnail: 'https://picsum.photos/100/80?random=804' },
  { id: 5, name: '城市夜景', thumbnail: 'https://picsum.photos/100/80?random=805' },
  { id: 6, name: '森林草地', thumbnail: 'https://picsum.photos/100/80?random=806' }
])

const selectedScene = ref<number | null>(null)
</script>

<template>
  <div class="scene-change">
    <div class="page-banner">
      <div class="banner-content">
        <span class="banner-tag">AI场景变换</span>
        <div class="banner-info">
          <img src="https://picsum.photos/80/100?random=608" alt="" class="banner-img" />
          <div class="banner-text">
            <h3>人物场景智能变换</h3>
            <p>上传人像图，选择场景，AI自动换背景</p>
          </div>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">👤</span>
        <span class="section-title">上传人像图片</span>
      </div>

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
        <span class="section-icon">🏞️</span>
        <span class="section-title">选择场景</span>
      </div>

      <div class="scene-grid">
        <div
          v-for="scene in scenes"
          :key="scene.id"
          class="scene-item"
          :class="{ active: selectedScene === scene.id }"
          @click="selectedScene = scene.id"
        >
          <img :src="scene.thumbnail" :alt="scene.name" />
          <div class="scene-name">{{ scene.name }}</div>
          <div class="check-icon" v-if="selectedScene === scene.id">✓</div>
        </div>
      </div>
    </div>

    <button class="generate-btn">立即生成</button>
  </div>
</template>

<style lang="scss" scoped>
.scene-change {
  max-width: 450px;
}

.page-banner {
  background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
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
    margin-bottom: 16px;

    .section-icon { font-size: 16px; }
    .section-title { font-size: 14px; font-weight: 600; color: #1f2937; }
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

      .upload-icon { font-size: 32px; color: #9ca3af; margin-bottom: 12px; }
      .upload-title { font-size: 14px; color: #6b7280; margin: 0 0 4px; }
      .upload-hint { font-size: 12px; color: #9ca3af; margin: 0; }
    }
  }

  .scene-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 12px;

    .scene-item {
      position: relative;
      border-radius: 8px;
      overflow: hidden;
      cursor: pointer;
      border: 2px solid transparent;
      transition: all 0.2s;

      &:hover { border-color: #a855f7; }
      &.active { border-color: #a855f7; }

      img { width: 100%; height: 60px; object-fit: cover; display: block; }

      .scene-name {
        padding: 6px;
        font-size: 12px;
        text-align: center;
        color: #374151;
        background: #f9fafb;
      }

      .check-icon {
        position: absolute;
        top: 4px;
        right: 4px;
        width: 20px;
        height: 20px;
        background: #a855f7;
        color: #fff;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 12px;
      }
    }
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
