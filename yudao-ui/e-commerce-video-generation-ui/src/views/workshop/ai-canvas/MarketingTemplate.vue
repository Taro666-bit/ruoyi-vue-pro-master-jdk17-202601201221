<script setup lang="ts">
import { ref } from 'vue'

const templates = ref([
  { id: 1, name: '双十一促销', thumbnail: 'https://picsum.photos/150/200?random=701' },
  { id: 2, name: '新品上市', thumbnail: 'https://picsum.photos/150/200?random=702' },
  { id: 3, name: '限时特惠', thumbnail: 'https://picsum.photos/150/200?random=703' },
  { id: 4, name: '爆款推荐', thumbnail: 'https://picsum.photos/150/200?random=704' },
  { id: 5, name: '清仓甩卖', thumbnail: 'https://picsum.photos/150/200?random=705' },
  { id: 6, name: '会员专享', thumbnail: 'https://picsum.photos/150/200?random=706' }
])

const selectedTemplate = ref<number | null>(null)
</script>

<template>
  <div class="marketing-template">
    <div class="page-banner">
      <div class="banner-content">
        <span class="banner-tag">AI商品营销模板</span>
        <div class="banner-info">
          <img src="https://picsum.photos/80/100?random=603" alt="" class="banner-img" />
          <div class="banner-text">
            <h3>商品营销模板生成</h3>
            <p>选择模板，上传商品图，AI自动生成营销图</p>
          </div>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">📋</span>
        <span class="section-title">选择模板</span>
      </div>

      <div class="template-grid">
        <div
          v-for="tpl in templates"
          :key="tpl.id"
          class="template-item"
          :class="{ active: selectedTemplate === tpl.id }"
          @click="selectedTemplate = tpl.id"
        >
          <img :src="tpl.thumbnail" :alt="tpl.name" />
          <div class="template-name">{{ tpl.name }}</div>
          <div class="check-icon" v-if="selectedTemplate === tpl.id">✓</div>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">📷</span>
        <span class="section-title">上传产品图片</span>
      </div>

      <div class="upload-placeholder">
        <p class="placeholder-text">暂无产品，请先添加产品</p>
      </div>
    </div>

    <button class="generate-btn">立即生成</button>
  </div>
</template>

<style lang="scss" scoped>
.marketing-template {
  max-width: 500px;
}

.page-banner {
  background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
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

  .template-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 12px;

    .template-item {
      position: relative;
      border-radius: 8px;
      overflow: hidden;
      cursor: pointer;
      border: 2px solid transparent;
      transition: all 0.2s;

      &:hover { border-color: #a855f7; }
      &.active { border-color: #a855f7; }

      img { width: 100%; display: block; }

      .template-name {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        padding: 8px;
        background: linear-gradient(transparent, rgba(0,0,0,0.7));
        color: #fff;
        font-size: 12px;
        text-align: center;
      }

      .check-icon {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 24px;
        height: 24px;
        background: #a855f7;
        color: #fff;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
      }
    }
  }

  .upload-placeholder {
    text-align: center;
    padding: 40px 20px;
    border: 2px dashed #e5e7eb;
    border-radius: 12px;

    .placeholder-text { font-size: 14px; color: #9ca3af; margin: 0; }
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
