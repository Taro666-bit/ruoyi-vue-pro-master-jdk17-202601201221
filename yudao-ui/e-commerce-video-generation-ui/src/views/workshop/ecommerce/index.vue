<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ArrowLeft } from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()

// 当前选中的主Tab
const activeMainTab = ref('ai-studio')

// 主Tab配置
const mainTabs = [
  { key: 'ai-studio', label: 'AI 影棚', icon: '📹' },
  { key: 'ai-canvas', label: 'AI 画布', icon: '🎨' },
  { key: 'ai-script', label: 'AI 脚本编导', icon: '📝' },
  { key: 'ai-voice', label: 'AI 配音', icon: '🎙️' }
]

// AI影棚子菜单
const aiStudioMenus = [
  { key: 'viral-clone', label: '爆款复刻', icon: '✨', path: '/app/workshop/ecommerce/ai-studio/viral-clone' },
  { key: 'product-display', label: '产品展示', icon: '📦', path: '/app/workshop/ecommerce/ai-studio/product-display' },
  { key: 'share-video', label: '使用分享视频', icon: '📤', path: '/app/workshop/ecommerce/ai-studio/share-video' },
  { key: 'tvc-video', label: 'TVC模式视频', icon: '🎬', path: '/app/workshop/ecommerce/ai-studio/tvc-video' },
  { key: 'digital-human', label: '数字人口播', icon: '👤', path: '/app/workshop/ecommerce/ai-studio/digital-human' },
  { key: 'face-swap', label: '视频换脸', icon: '🔄', path: '/app/workshop/ecommerce/ai-studio/face-swap' },
  { key: 'multi-dialog', label: '多人对话', icon: '👥', path: '/app/workshop/ecommerce/ai-studio/multi-dialog' },
  { key: 'lip-sync', label: '对口型', icon: '💬', path: '/app/workshop/ecommerce/ai-studio/lip-sync' },
  { key: 'motion-clone', label: '动作模仿', icon: '🏃', path: '/app/workshop/ecommerce/ai-studio/motion-clone' }
]

// AI画布子菜单
const aiCanvasMenus = [
  { key: 'white-bg', label: '商品一键白底', icon: '⬜', path: '/app/workshop/ecommerce/ai-canvas/white-bg' },
  { key: 'change-bg', label: '产品换背景', icon: '🖼️', path: '/app/workshop/ecommerce/ai-canvas/change-bg' },
  { key: 'marketing-template', label: '商品营销模板', icon: '📋', path: '/app/workshop/ecommerce/ai-canvas/marketing-template' },
  { key: 'image-clone', label: '商品图片复刻', icon: '📷', path: '/app/workshop/ecommerce/ai-canvas/image-clone' },
  { key: 'series-image', label: '商品系列图(9图)', icon: '🖼️', path: '/app/workshop/ecommerce/ai-canvas/series-image' },
  { key: 'model-display', label: '模特商品展示', icon: '👗', path: '/app/workshop/ecommerce/ai-canvas/model-display' },
  { key: 'model-change', label: '模特换装', icon: '👚', path: '/app/workshop/ecommerce/ai-canvas/model-change' },
  { key: 'scene-change', label: '场景变换', icon: '🏞️', path: '/app/workshop/ecommerce/ai-canvas/scene-change' }
]

const currentSubMenus = computed(() => {
  if (activeMainTab.value === 'ai-studio') return aiStudioMenus
  if (activeMainTab.value === 'ai-canvas') return aiCanvasMenus
  return []
})

const activeSubMenu = computed(() => {
  const path = route.path
  const menus = [...aiStudioMenus, ...aiCanvasMenus]
  const menu = menus.find((m) => path.includes(m.key))
  return menu?.key || ''
})

// 监听路由变化，自动切换tab
watch(() => route.path, (path) => {
  if (path.includes('ai-studio')) {
    activeMainTab.value = 'ai-studio'
  } else if (path.includes('ai-canvas')) {
    activeMainTab.value = 'ai-canvas'
  } else if (path.includes('ai-script')) {
    activeMainTab.value = 'ai-script'
  } else if (path.includes('ai-voice')) {
    activeMainTab.value = 'ai-voice'
  }
}, { immediate: true })

const handleMainTabChange = (tab: string) => {
  activeMainTab.value = tab
  if (tab === 'ai-studio') {
    router.push('/app/workshop/ecommerce/ai-studio/viral-clone')
  } else if (tab === 'ai-canvas') {
    router.push('/app/workshop/ecommerce/ai-canvas/white-bg')
  } else if (tab === 'ai-script') {
    router.push('/app/workshop/ecommerce/ai-script')
  } else if (tab === 'ai-voice') {
    router.push('/app/workshop/ecommerce/ai-voice')
  }
}

const handleSubMenuClick = (path: string) => {
  router.push(path)
}

const handleBack = () => {
  router.push('/app/dashboard')
}

// 右侧展示数据
const activeShowcaseTab = ref('recent')
const showcaseItems = ref([
  { id: 1, src: 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=200&h=280&fit=crop', tag: 'TVC模式视频' },
  { id: 2, src: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=200&h=250&fit=crop', tag: 'TVC模式视频' },
  { id: 3, src: 'https://images.unsplash.com/photo-1560472354-b33ff0c44a43?w=200&h=300&fit=crop', tag: 'TVC模式视频' },
  { id: 4, src: 'https://images.unsplash.com/photo-1585386959984-a4155224a1ad?w=200&h=260&fit=crop', tag: 'TVC模式视频' },
  { id: 5, src: 'https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=200&h=280&fit=crop', tag: 'TVC模式视频' },
  { id: 6, src: 'https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=200&h=240&fit=crop', tag: 'TVC模式视频' }
])
</script>

<template>
  <div class="ecommerce-workshop">
    <!-- 顶部导航 -->
    <header class="workshop-header">
      <!-- 第一行：返回 + 标题 + 右侧按钮 -->
      <div class="header-row-1">
        <div class="header-left">
          <button class="back-btn" @click="handleBack">
            <el-icon><ArrowLeft /></el-icon>
            <span>返回</span>
          </button>
          <div class="header-title">
            <h1>电商工坊</h1>
            <p>AI 智能生成电商营销素材</p>
          </div>
        </div>
        <div class="header-right">
          <span class="help-text">AI 影棚生成电商视频</span>
          <button class="grid-btn">
            <span>⊞</span>
          </button>
        </div>
      </div>
      <!-- 第二行：Tab切换 -->
      <div class="header-row-2">
        <div class="header-tabs">
          <button
            v-for="tab in mainTabs"
            :key="tab.key"
            class="main-tab"
            :class="{ active: activeMainTab === tab.key }"
            @click="handleMainTabChange(tab.key)"
          >
            <span class="tab-icon">{{ tab.icon }}</span>
            <span class="tab-label">{{ tab.label }}</span>
          </button>
        </div>
      </div>
    </header>

    <!-- 主内容区 -->
    <div class="workshop-main">
      <!-- 左侧边栏 -->
      <aside class="workshop-sidebar">
        <!-- Banner卡片 -->
        <div class="sidebar-banner">
          <div class="banner-badge">AI影棚视频中心</div>
          <div class="banner-images">
            <img src="https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=100&h=120&fit=crop" alt="" />
            <img src="https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=100&h=120&fit=crop" alt="" />
          </div>
        </div>

        <!-- 子菜单 -->
        <nav class="sub-menu" v-if="currentSubMenus.length > 0">
          <div
            v-for="menu in currentSubMenus"
            :key="menu.key"
            class="sub-menu-item"
            :class="{ active: activeSubMenu === menu.key }"
            @click="handleSubMenuClick(menu.path)"
          >
            <span class="menu-icon">{{ menu.icon }}</span>
            <span class="menu-label">{{ menu.label }}</span>
          </div>
        </nav>
      </aside>

      <!-- 中间内容区 -->
      <main class="workshop-content">
        <router-view />
      </main>

      <!-- 右侧展示区 -->
      <aside class="workshop-showcase">
        <div class="showcase-header">
          <button
            class="showcase-tab"
            :class="{ active: activeShowcaseTab === 'recent' }"
            @click="activeShowcaseTab = 'recent'"
          >
            <span class="tab-icon">📹</span>
            <span>最近生成</span>
          </button>
          <button
            class="showcase-tab"
            :class="{ active: activeShowcaseTab === 'examples' }"
            @click="activeShowcaseTab = 'examples'"
          >
            <span class="tab-icon">✨</span>
            <span>精彩案例</span>
            <span class="hot-dot">🔥</span>
          </button>
        </div>

        <div class="showcase-content">
          <div v-if="activeShowcaseTab === 'recent'" class="empty-state">
            <div class="empty-icon">📹</div>
            <p class="empty-title">暂无生成记录</p>
            <p class="empty-desc">点击生成按钮开始创作</p>
          </div>

          <div v-else class="showcase-grid">
            <div
              v-for="item in showcaseItems"
              :key="item.id"
              class="showcase-item"
            >
              <img :src="item.src" :alt="item.tag" />
              <div class="item-tag">{{ item.tag }}</div>
              <div class="play-overlay">
                <span class="play-icon">▶</span>
              </div>
            </div>
          </div>
        </div>
      </aside>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.ecommerce-workshop {
  min-height: 100vh;
  background: #f5f5f5;
  display: flex;
  flex-direction: column;
  position: relative;
}

.workshop-header {
  background: #fff;
  border-bottom: 1px solid #e5e7eb;

  .header-row-1 {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 12px 24px 8px;
  }

  .header-row-2 {
    padding: 0 24px 12px;
  }

  .header-left {
    display: flex;
    align-items: center;
    gap: 20px;

    .back-btn {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 8px 12px;
      color: #6b7280;
      font-size: 14px;
      background: transparent;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        background: #f3f4f6;
        color: #1f2937;
      }
    }

    .header-title {
      h1 {
        font-size: 18px;
        font-weight: 600;
        color: #1f2937;
        margin: 0;
      }

      p {
        font-size: 12px;
        color: #9ca3af;
        margin: 2px 0 0;
      }
    }
  }

  .header-tabs {
    display: inline-flex;
    gap: 8px;

    .main-tab {
      display: flex;
      align-items: center;
      gap: 8px;
      padding: 10px 20px;
      border-radius: 10px;
      font-size: 14px;
      color: #6b7280;
      background: transparent;
      border: none;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        color: #1f2937;
      }

      &.active {
        color: #a855f7;
        border-bottom: 2px solid #a855f7;
        border-radius: 0;
        padding-bottom: 8px;
      }

      .tab-icon {
        font-size: 16px;
      }

      .tab-label {
        font-weight: 500;
      }
    }
  }

  .header-right {
    display: flex;
    align-items: center;
    gap: 16px;

    .help-text {
      font-size: 13px;
      color: #6b7280;
    }

    .grid-btn {
      width: 36px;
      height: 36px;
      display: flex;
      align-items: center;
      justify-content: center;
      background: #f5f5f5;
      border: none;
      border-radius: 8px;
      font-size: 16px;
      color: #6b7280;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        background: #e5e7eb;
      }
    }
  }
}

.workshop-main {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.workshop-sidebar {
  width: 200px;
  background: #fff;
  border-right: 1px solid #e5e7eb;
  display: flex;
  flex-direction: column;
  overflow-y: auto;

  .sidebar-banner {
    margin: 16px;
    padding: 16px;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-radius: 16px;

    .banner-badge {
      display: inline-block;
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      font-size: 11px;
      padding: 4px 10px;
      border-radius: 6px;
      margin-bottom: 12px;
      font-weight: 500;
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

  .sub-menu {
    padding: 8px;
    flex: 1;

    .sub-menu-item {
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 12px 16px;
      border-radius: 10px;
      font-size: 13px;
      color: #6b7280;
      cursor: pointer;
      transition: all 0.2s;
      margin-bottom: 4px;

      &:hover {
        background: #f9fafb;
        color: #1f2937;
      }

      &.active {
        background: linear-gradient(135deg, #faf5ff 0%, #f5f3ff 100%);
        color: #a855f7;

        .menu-icon {
          background: #a855f7;
          color: #fff;
        }
      }

      .menu-icon {
        width: 28px;
        height: 28px;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #f3f4f6;
        border-radius: 8px;
        font-size: 14px;
      }

      .menu-label {
        font-weight: 500;
      }
    }
  }
}

.workshop-content {
  flex: 1;
  padding: 16px;
  overflow-y: auto;
  background: #f5f5f5;
}

.workshop-showcase {
  width: 320px;
  background: #fff;
  border-left: 1px solid #e5e7eb;
  display: flex;
  flex-direction: column;

  .showcase-header {
    display: flex;
    gap: 8px;
    padding: 16px;
    border-bottom: 1px solid #f0f0f0;

    .showcase-tab {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 10px 16px;
      background: transparent;
      border: none;
      border-radius: 10px;
      font-size: 14px;
      color: #6b7280;
      cursor: pointer;
      transition: all 0.2s;

      &.active {
        background: #f5f5f5;
        color: #1f2937;
        font-weight: 500;
      }

      .tab-icon {
        font-size: 14px;
      }

      .hot-dot {
        font-size: 12px;
      }
    }
  }

  .showcase-content {
    flex: 1;
    padding: 16px;
    overflow-y: auto;

    .empty-state {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      padding: 60px 20px;
      text-align: center;

      .empty-icon {
        font-size: 48px;
        margin-bottom: 16px;
        opacity: 0.5;
      }

      .empty-title {
        font-size: 14px;
        color: #6b7280;
        margin-bottom: 4px;
      }

      .empty-desc {
        font-size: 12px;
        color: #9ca3af;
      }
    }

    .showcase-grid {
      display: grid;
      grid-template-columns: repeat(2, 1fr);
      gap: 12px;

      .showcase-item {
        position: relative;
        border-radius: 12px;
        overflow: hidden;
        cursor: pointer;

        img {
          width: 100%;
          display: block;
        }

        .item-tag {
          position: absolute;
          bottom: 8px;
          left: 8px;
          background: rgba(0, 0, 0, 0.7);
          color: #fff;
          font-size: 10px;
          padding: 4px 8px;
          border-radius: 4px;
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
            width: 36px;
            height: 36px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 14px;
          }
        }

        &:hover .play-overlay {
          opacity: 1;
        }
      }
    }
  }
}
</style>
