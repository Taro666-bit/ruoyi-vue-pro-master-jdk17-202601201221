<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 功能标签
const featureTags = [
  { id: 1, label: '爆款复刻', path: '/app/workshop/ecommerce/ai-studio/viral-clone' },
  { id: 2, label: '素材制作', path: '/app/workshop/material' },
  { id: 3, label: '脚本编导', path: '/app/workshop/ecommerce/ai-script' },
  { id: 4, label: '浏览器插件', path: '#', badge: '↓' },
  { id: 5, label: 'AI配音', path: '/app/workshop/ecommerce/ai-voice' },
  { id: 6, label: 'Sora2 Pro', path: '#', badge: '↓', hot: true },
  { id: 7, label: 'Nano Banana Pro', path: '#', badge: '→', hot: true },
  { id: 8, label: '对口型', path: '/app/workshop/ecommerce/ai-studio/lip-sync' },
  { id: 9, label: '动作模仿', path: '/app/workshop/ecommerce/ai-studio/motion-clone' },
  { id: 10, label: '多人对话', path: '/app/workshop/ecommerce/ai-studio/multi-dialog' },
  { id: 11, label: '模特商品展示', path: '/app/workshop/ecommerce/ai-canvas/model-display' },
  { id: 12, label: '模特换装', path: '/app/workshop/ecommerce/ai-canvas/model-change' },
  { id: 13, label: '场景变换', path: '/app/workshop/ecommerce/ai-canvas/scene-change' },
  { id: 14, label: '数字人口播', path: '/app/workshop/ecommerce/ai-studio/digital-human' },
  { id: 15, label: '视频换脸', path: '/app/workshop/ecommerce/ai-studio/face-swap' },
  { id: 16, label: '产品展示', path: '/app/workshop/ecommerce/ai-studio/product-display' },
  { id: 17, label: '商品一键白底', path: '/app/workshop/ecommerce/ai-canvas/white-bg' },
  { id: 18, label: '商品图片复刻', path: '/app/workshop/ecommerce/ai-canvas/image-clone' },
  { id: 19, label: '商品系列图(9图)', path: '/app/workshop/ecommerce/ai-canvas/series-image' }
]

// 精彩案例 - 瀑布流数据
const showcaseItems = ref([
  { id: 1, src: 'https://images.unsplash.com/photo-1522335789203-aabd1fc54bc9?w=300&h=400&fit=crop', tag: '视频素材', type: 'video' },
  { id: 2, src: 'https://images.unsplash.com/photo-1596462502278-27bfdc403348?w=300&h=350&fit=crop', tag: '产品展示', type: 'image' },
  { id: 3, src: 'https://images.unsplash.com/photo-1586495777744-4413f21062fa?w=300&h=450&fit=crop', tag: '使用分享视频', type: 'video' },
  { id: 4, src: 'https://images.unsplash.com/photo-1556228720-195a672e8a03?w=300&h=380&fit=crop', tag: '视频素材', type: 'video' },
  { id: 5, src: 'https://images.unsplash.com/photo-1571781926291-c477ebfd024b?w=300&h=320&fit=crop', tag: '视频素材', type: 'video' },
  { id: 6, src: 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=300&h=300&fit=crop', tag: '产品展示', type: 'image' },
  { id: 7, src: 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=300&h=360&fit=crop', tag: '视频素材', type: 'video' },
  { id: 8, src: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=300&h=300&fit=crop', tag: '产品展示', type: 'image' },
  { id: 9, src: 'https://images.unsplash.com/photo-1560472354-b33ff0c44a43?w=300&h=420&fit=crop', tag: '视频素材', type: 'video' },
  { id: 10, src: 'https://images.unsplash.com/photo-1583394838336-acd977736f90?w=300&h=340&fit=crop', tag: '视频素材', type: 'video' },
  { id: 11, src: 'https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=300&h=380&fit=crop', tag: '产品展示', type: 'image' },
  { id: 12, src: 'https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=300&h=300&fit=crop', tag: '动作模仿', type: 'video' },
  { id: 13, src: 'https://images.unsplash.com/photo-1585386959984-a4155224a1ad?w=300&h=450&fit=crop', tag: '视频素材', type: 'video' },
  { id: 14, src: 'https://images.unsplash.com/photo-1598532163257-ae3c6b2524b6?w=300&h=360&fit=crop', tag: '产品展示', type: 'image' },
  { id: 15, src: 'https://images.unsplash.com/photo-1546868871-7041f2a55e12?w=300&h=320&fit=crop', tag: '视频素材', type: 'video' }
])

const handleTagClick = (path: string) => {
  if (path !== '#') {
    router.push(path)
  }
}
</script>

<template>
  <div class="dashboard">
    <!-- 顶部标题区 -->
    <div class="dashboard-header">
      <div class="header-badge">
        <span class="badge-icon">✨</span>
        <span>AI驱动的视频创作平台</span>
      </div>
    </div>

    <!-- Hero区域 -->
    <section class="hero-section">
      <h1 class="hero-title">
        <span class="gradient-text">AI 驱动视频创作平台</span>
      </h1>
      <p class="hero-subtitle">专业的 AI 工具集，助力视频创作全流程</p>

      <!-- 功能标签网格 -->
      <div class="feature-tags">
        <button
          v-for="tag in featureTags"
          :key="tag.id"
          class="tag-btn"
          :class="{ hot: tag.hot }"
          @click="handleTagClick(tag.path)"
        >
          {{ tag.label }}
          <span v-if="tag.badge" class="tag-badge">{{ tag.badge }}</span>
        </button>
      </div>
    </section>

    <!-- 精彩案例 -->
    <section class="showcase-section">
      <div class="section-header">
        <h2 class="section-title">精彩案例</h2>
        <p class="section-subtitle">探索由 AI 驱动创作的精彩作品</p>
      </div>

      <!-- 瀑布流网格 -->
      <div class="masonry-grid">
        <div
          v-for="item in showcaseItems"
          :key="item.id"
          class="masonry-item"
        >
          <div class="item-wrapper">
            <img :src="item.src" :alt="item.tag" loading="lazy" />
            <div class="item-tag">{{ item.tag }}</div>
            <div v-if="item.type === 'video'" class="play-overlay">
              <span class="play-icon">▶</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style lang="scss" scoped>
.dashboard {
  padding: 24px 32px;
  background: #fff;
  min-height: 100%;
}

.dashboard-header {
  margin-bottom: 32px;

  .header-badge {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 8px 16px;
    background: #faf5ff;
    border: 1px solid #e9d5ff;
    border-radius: 100px;
    font-size: 14px;
    color: #7c3aed;

    .badge-icon {
      font-size: 16px;
    }
  }
}

.hero-section {
  text-align: center;
  margin-bottom: 60px;

  .hero-title {
    font-size: 48px;
    font-weight: 700;
    margin-bottom: 16px;

    .gradient-text {
      background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      background-clip: text;
    }
  }

  .hero-subtitle {
    font-size: 18px;
    color: #6b7280;
    margin-bottom: 40px;
  }

  .feature-tags {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 12px;
    max-width: 900px;
    margin: 0 auto;

    .tag-btn {
      display: inline-flex;
      align-items: center;
      gap: 6px;
      padding: 10px 20px;
      background: #fff;
      border: 1px solid #e5e7eb;
      border-radius: 100px;
      font-size: 14px;
      color: #374151;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        border-color: #a855f7;
        color: #a855f7;
        background: #faf5ff;
      }

      &.hot {
        border-color: #fbbf24;
        background: #fffbeb;

        &:hover {
          border-color: #f59e0b;
        }
      }

      .tag-badge {
        font-size: 12px;
        color: #f59e0b;
      }
    }
  }
}

.showcase-section {
  .section-header {
    text-align: center;
    margin-bottom: 40px;

    .section-title {
      font-size: 28px;
      font-weight: 700;
      color: #1f2937;
      margin-bottom: 8px;
    }

    .section-subtitle {
      font-size: 16px;
      color: #6b7280;
    }
  }
}

.masonry-grid {
  column-count: 5;
  column-gap: 16px;

  @media (max-width: 1400px) {
    column-count: 4;
  }

  @media (max-width: 1100px) {
    column-count: 3;
  }

  @media (max-width: 800px) {
    column-count: 2;
  }
}

.masonry-item {
  break-inside: avoid;
  margin-bottom: 16px;

  .item-wrapper {
    position: relative;
    border-radius: 12px;
    overflow: hidden;
    background: #f3f4f6;
    cursor: pointer;
    transition: transform 0.2s;

    &:hover {
      transform: translateY(-4px);

      .play-overlay {
        opacity: 1;
      }
    }

    img {
      width: 100%;
      display: block;
    }

    .item-tag {
      position: absolute;
      top: 12px;
      right: 12px;
      background: linear-gradient(135deg, #ec4899 0%, #a855f7 100%);
      color: #fff;
      font-size: 11px;
      padding: 4px 10px;
      border-radius: 4px;
      font-weight: 500;
    }

    .play-overlay {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(0, 0, 0, 0.3);
      display: flex;
      align-items: center;
      justify-content: center;
      opacity: 0;
      transition: opacity 0.2s;

      .play-icon {
        width: 48px;
        height: 48px;
        background: rgba(255, 255, 255, 0.95);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 18px;
        color: #1f2937;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
      }
    }
  }
}
</style>
