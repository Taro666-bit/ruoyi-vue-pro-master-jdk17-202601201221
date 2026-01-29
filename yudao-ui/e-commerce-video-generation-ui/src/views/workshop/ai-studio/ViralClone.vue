<script setup lang="ts">
import { ref } from 'vue'
import { Upload, Link } from '@element-plus/icons-vue'

// 产品选择方式
const productSelectType = ref('library') // local | library | link

// 视频素材方式
const videoSourceType = ref('link') // link | local

// 生成语言
const generateLang = ref('en')

// 视频链接
const videoLink = ref('')

// 产品列表（空状态）
const productList = ref([])
</script>

<template>
  <div class="viral-clone">
    <!-- 页面标题Banner -->
    <div class="page-banner">
      <div class="banner-left">
        <div class="banner-badge">AI爆款复刻</div>
        <div class="banner-text">
          <p>复入"视频+产品后，一键整刻爆款短视频</p>
        </div>
      </div>
      <div class="banner-images">
        <img src="https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=120&h=160&fit=crop" alt="" />
        <img src="https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=120&h=160&fit=crop" alt="" />
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
          class="tab-btn active-dark"
          :class="{ 'active-dark': productSelectType === 'library' }"
          @click="productSelectType = 'library'"
        >
          <span class="icon">⊞</span>
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
          <div class="placeholder-icon">
            <svg width="48" height="48" viewBox="0 0 48 48" fill="none">
              <rect x="8" y="8" width="32" height="32" rx="4" stroke="#d1d5db" stroke-width="2" stroke-dasharray="4 4"/>
              <path d="M24 16v16M16 24h16" stroke="#d1d5db" stroke-width="2" stroke-linecap="round"/>
            </svg>
          </div>
          <p class="placeholder-title">暂无产品</p>
          <p class="placeholder-desc">请先添加产品到产品库</p>
        </div>
      </div>
    </div>

    <!-- 视频素材 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">🎬</span>
        <span class="section-title">视频素材</span>
      </div>

      <div class="video-tabs">
        <button
          class="tab-btn active-dark"
          :class="{ 'active-dark': videoSourceType === 'link' }"
          @click="videoSourceType = 'link'"
        >
          <el-icon><Link /></el-icon>
          <span>链接解析</span>
        </button>
        <button
          class="tab-btn"
          :class="{ active: videoSourceType === 'local' }"
          @click="videoSourceType = 'local'"
        >
          <el-icon><Upload /></el-icon>
          <span>本地上传</span>
        </button>
      </div>

      <!-- 链接输入 -->
      <div class="video-link-input" v-if="videoSourceType === 'link'">
        <p class="input-hint">支持TikTok视频链接</p>
        <div class="input-wrapper">
          <input
            v-model="videoLink"
            type="text"
            placeholder="粘贴视频链接..."
          />
          <button class="parse-btn">解析</button>
        </div>
      </div>
    </div>

    <!-- 生成语言 -->
    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">🌐</span>
        <span class="section-title">生成语言</span>
      </div>

      <el-select v-model="generateLang" class="lang-select" placeholder="选择语言">
        <el-option label="英语 (English)" value="en" />
        <el-option label="中文 (Chinese)" value="zh" />
        <el-option label="日语 (Japanese)" value="ja" />
        <el-option label="韩语 (Korean)" value="ko" />
        <el-option label="西班牙语 (Spanish)" value="es" />
        <el-option label="法语 (French)" value="fr" />
      </el-select>
    </div>

    <!-- 生成按钮 -->
    <button class="generate-btn">
      <span class="btn-icon">✨</span>
      <span>立即生成</span>
    </button>
  </div>
</template>

<style lang="scss" scoped>
.viral-clone {
  max-width: 420px;
}

.page-banner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
  border-radius: 16px;
  padding: 20px;
  margin-bottom: 24px;

  .banner-left {
    .banner-badge {
      display: inline-block;
      background: rgba(168, 85, 247, 0.3);
      color: #c084fc;
      font-size: 12px;
      padding: 4px 12px;
      border-radius: 6px;
      margin-bottom: 8px;
      font-weight: 500;
    }

    .banner-text {
      p {
        color: rgba(255, 255, 255, 0.8);
        font-size: 13px;
        margin: 0;
        line-height: 1.5;
      }
    }
  }

  .banner-images {
    display: flex;
    gap: 8px;

    img {
      width: 70px;
      height: 90px;
      object-fit: cover;
      border-radius: 8px;
    }
  }
}

.form-section {
  background: #fff;
  border: 1px solid #f0f0f0;
  border-radius: 16px;
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

  .product-tabs,
  .video-tabs {
    display: flex;
    gap: 8px;
    margin-bottom: 16px;

    .tab-btn {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 10px 18px;
      border: 1px solid #e5e7eb;
      border-radius: 10px;
      font-size: 13px;
      color: #6b7280;
      background: #fff;
      cursor: pointer;
      transition: all 0.2s;

      .icon {
        font-size: 14px;
      }

      &:hover {
        border-color: #d1d5db;
        background: #f9fafb;
      }

      &.active {
        border-color: #a855f7;
        color: #a855f7;
        background: #faf5ff;
      }

      &.active-dark {
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
      background: #fafafa;

      .placeholder-icon {
        margin-bottom: 12px;
        opacity: 0.6;
      }

      .placeholder-title {
        font-size: 14px;
        color: #6b7280;
        margin: 0 0 4px;
        font-weight: 500;
      }

      .placeholder-desc {
        font-size: 12px;
        color: #9ca3af;
        margin: 0;
      }
    }
  }

  .video-link-input {
    .input-hint {
      font-size: 12px;
      color: #9ca3af;
      margin: 0 0 10px;
    }

    .input-wrapper {
      display: flex;
      gap: 10px;

      input {
        flex: 1;
        padding: 12px 16px;
        border: 1px solid #e5e7eb;
        border-radius: 10px;
        font-size: 14px;
        outline: none;
        background: #f9fafb;
        transition: all 0.2s;

        &:focus {
          border-color: #a855f7;
          background: #fff;
        }

        &::placeholder {
          color: #9ca3af;
        }
      }

      .parse-btn {
        padding: 12px 24px;
        background: #1f2937;
        color: #fff;
        border: none;
        border-radius: 10px;
        font-size: 14px;
        font-weight: 500;
        cursor: pointer;
        transition: all 0.2s;

        &:hover {
          background: #374151;
        }
      }
    }
  }

  .lang-select {
    width: 100%;

    :deep(.el-input__wrapper) {
      border-radius: 10px;
      box-shadow: none;
      border: 1px solid #e5e7eb;
      padding: 4px 12px;

      &:hover {
        border-color: #d1d5db;
      }

      &.is-focus {
        border-color: #a855f7;
      }
    }
  }
}

.generate-btn {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding: 16px;
  background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
  color: #fff;
  border: none;
  border-radius: 12px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  box-shadow: 0 4px 15px rgba(168, 85, 247, 0.3);

  .btn-icon {
    font-size: 18px;
  }

  &:hover {
    opacity: 0.95;
    transform: translateY(-1px);
    box-shadow: 0 6px 20px rgba(168, 85, 247, 0.4);
  }
}
</style>
