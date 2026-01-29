<script setup lang="ts">
import { ref } from 'vue'
import { Upload, Link } from '@element-plus/icons-vue'

const productSelectType = ref('local')
const productList = ref([])
</script>

<template>
  <div class="white-bg">
    <div class="page-banner">
      <div class="banner-content">
        <span class="banner-tag">AI商品一键白底</span>
        <div class="banner-info">
          <img src="https://picsum.photos/80/100?random=601" alt="" class="banner-img" />
          <div class="banner-text">
            <h3>商品一键换白底</h3>
            <p>上传商品图，AI自动去除背景换成白色</p>
          </div>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">📷</span>
        <span class="section-title">上传产品图片</span>
      </div>

      <div class="product-tabs">
        <button class="tab-btn" :class="{ active: productSelectType === 'local' }" @click="productSelectType = 'local'">
          <el-icon><Upload /></el-icon>
          <span>本地上传</span>
        </button>
        <button class="tab-btn" :class="{ active: productSelectType === 'library' }" @click="productSelectType = 'library'">
          <span>产品库</span>
        </button>
        <button class="tab-btn" :class="{ active: productSelectType === 'link' }" @click="productSelectType = 'link'">
          <el-icon><Link /></el-icon>
          <span>链接解析</span>
        </button>
      </div>

      <p class="upload-hint">从您的产品库中选择</p>

      <div class="upload-placeholder" v-if="productList.length === 0">
        <p class="placeholder-text">暂无产品，请先添加产品</p>
      </div>
    </div>

    <button class="generate-btn">立即生成</button>
  </div>
</template>

<style lang="scss" scoped>
.white-bg {
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
    margin-bottom: 16px;

    .section-icon { font-size: 16px; }
    .section-title { font-size: 14px; font-weight: 600; color: #1f2937; }
  }

  .product-tabs {
    display: flex;
    gap: 8px;
    margin-bottom: 12px;

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

  .upload-hint {
    font-size: 12px;
    color: #9ca3af;
    margin: 0 0 16px;
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
