<script setup lang="ts">
import { ref } from 'vue'
import { Upload, Link } from '@element-plus/icons-vue'

// 产品选择方式
const productSelectType = ref('local')

// 生成语言
const generateLang = ref('en')

// 视频输出尺寸
const videoRatio = ref('9:16')

// 视频模式
const videoMode = ref('portrait')

// 批量生成数量
const batchCount = ref(1)

// 产品列表
const productList = ref([])
</script>

<template>
  <div class="product-display">
    <!-- 页面标题Banner -->
    <div class="page-banner">
      <div class="banner-content">
        <span class="banner-tag">AI产品展示</span>
        <div class="banner-info">
          <img src="https://picsum.photos/80/100?random=502" alt="" class="banner-img" />
          <div class="banner-text">
            <h3>产品展示视频生成</h3>
            <p>上传产品图，AI自动生成产品展示视频</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 产品选择 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">📦</span>
        <span class="section-title">产品选择</span>
      </div>

      <div class="product-tabs">
        <button
          class="tab-btn"
          :class="{ active: productSelectType === 'local' }"
          @click="productSelectType = 'local'"
        >
          <el-icon><Upload /></el-icon>
          <span>本地上传</span>
        </button>
        <button
          class="tab-btn"
          :class="{ active: productSelectType === 'library' }"
          @click="productSelectType = 'library'"
        >
          <el-icon><Folder /></el-icon>
          <span>产品库</span>
        </button>
        <button
          class="tab-btn"
          :class="{ active: productSelectType === 'link' }"
          @click="productSelectType = 'link'"
        >
          <el-icon><Link /></el-icon>
          <span>链接解析</span>
        </button>
      </div>

      <!-- 产品上传区域 -->
      <div class="product-upload-area">
        <div class="upload-placeholder" v-if="productList.length === 0">
          <div class="placeholder-icon">📦</div>
          <p class="placeholder-title">暂无产品</p>
          <p class="placeholder-desc">请先添加产品到产品库</p>
        </div>
      </div>
    </div>

    <!-- 生成语言 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">🌐</span>
        <span class="section-title">生成语言</span>
      </div>
      <el-select v-model="generateLang" class="full-select">
        <el-option label="英语 (English)" value="en" />
        <el-option label="中文 (Chinese)" value="zh" />
        <el-option label="日语 (Japanese)" value="ja" />
      </el-select>
    </div>

    <!-- 视频输出尺寸 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">📐</span>
        <span class="section-title">视频输出尺寸</span>
      </div>
      <el-select v-model="videoRatio" class="full-select">
        <el-option label="9:16 (竖屏)" value="9:16" />
        <el-option label="16:9 (横屏)" value="16:9" />
        <el-option label="1:1 (方形)" value="1:1" />
      </el-select>
    </div>

    <!-- 视频模式 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">🎬</span>
        <span class="section-title">竖屏 (Portrait)</span>
      </div>
      <el-select v-model="videoMode" class="full-select">
        <el-option label="竖屏 (Portrait)" value="portrait" />
        <el-option label="横屏 (Landscape)" value="landscape" />
      </el-select>
    </div>

    <!-- 批量生成数量 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">📊</span>
        <span class="section-title">批量生成数量</span>
      </div>
      <div class="batch-control">
        <button class="batch-btn" @click="batchCount = Math.max(1, batchCount - 1)">-</button>
        <span class="batch-count">{{ batchCount }}</span>
        <button class="batch-btn" @click="batchCount = Math.min(20, batchCount + 1)">+</button>
        <span class="batch-hint">最多 20个</span>
      </div>
    </div>

    <!-- 生成按钮 -->
    <button class="generate-btn">
      立即生成
    </button>
  </div>
</template>

<style lang="scss" scoped>
.product-display {
  max-width: 400px;
}

.page-banner {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
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

      .banner-img {
        width: 60px;
        height: 80px;
        object-fit: cover;
        border-radius: 8px;
      }

      .banner-text {
        color: #fff;

        h3 {
          font-size: 16px;
          font-weight: 600;
          margin: 0 0 4px;
        }

        p {
          font-size: 12px;
          opacity: 0.9;
          margin: 0;
        }
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

    .section-icon {
      font-size: 16px;
    }

    .section-title {
      font-size: 14px;
      font-weight: 600;
      color: #1f2937;
    }
  }

  .product-tabs {
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
      transition: all 0.2s;

      &:hover {
        border-color: #a855f7;
        color: #a855f7;
      }

      &.active {
        background: #1f2937;
        color: #fff;
        border-color: #1f2937;
      }
    }
  }

  .product-upload-area {
    .upload-placeholder {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      padding: 40px 20px;
      border: 2px dashed #e5e7eb;
      border-radius: 12px;
      text-align: center;

      .placeholder-icon {
        font-size: 40px;
        margin-bottom: 12px;
        opacity: 0.5;
      }

      .placeholder-title {
        font-size: 14px;
        color: #6b7280;
        margin: 0 0 4px;
      }

      .placeholder-desc {
        font-size: 12px;
        color: #9ca3af;
        margin: 0;
      }
    }
  }

  .full-select {
    width: 100%;
  }

  .batch-control {
    display: flex;
    align-items: center;
    gap: 12px;

    .batch-btn {
      width: 32px;
      height: 32px;
      border: 1px solid #e5e7eb;
      border-radius: 6px;
      background: #fff;
      font-size: 18px;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        border-color: #a855f7;
        color: #a855f7;
      }
    }

    .batch-count {
      font-size: 16px;
      font-weight: 500;
      min-width: 30px;
      text-align: center;
    }

    .batch-hint {
      font-size: 12px;
      color: #9ca3af;
      margin-left: auto;
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
  transition: all 0.2s;

  &:hover {
    opacity: 0.9;
    transform: translateY(-1px);
  }
}
</style>
