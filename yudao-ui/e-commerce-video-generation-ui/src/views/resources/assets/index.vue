<script setup lang="ts">
import { ref } from 'vue'

const activeTab = ref('viral-clone')

const tabs = [
  { key: 'viral-clone', label: '爆款复刻' },
  { key: 'product-display', label: '产品展示' },
  { key: 'digital-human', label: '数字人口播' },
  { key: 'face-swap', label: '视频换脸' },
  { key: 'motion-clone', label: '动作模仿' },
  { key: 'lip-sync', label: '对口型' },
  { key: 'share-video', label: '使用分享视频' },
  { key: 'tvc-video', label: 'TVC模式视频' },
  { key: 'model-display', label: '模特商品展示' },
  { key: 'model-change', label: '模特换装' },
  { key: 'scene-change', label: '场景变换' },
  { key: 'white-bg', label: '商品一键白底' }
]

const assetList = ref([])
const showCollapsed = ref(false)

const visibleTabs = computed(() => showCollapsed.value ? tabs : tabs.slice(0, 10))
</script>

<template>
  <div class="assets-page">
    <div class="page-header">
      <div class="header-left">
        <h1>我的资产</h1>
        <p>查看和管理您在工作台生成的所有内容资产</p>
      </div>
    </div>

    <div class="page-filters">
      <div class="filter-tabs">
        <button
          v-for="tab in visibleTabs"
          :key="tab.key"
          class="filter-tab"
          :class="{ active: activeTab === tab.key }"
          @click="activeTab = tab.key"
        >
          {{ tab.label }}
        </button>
        <button class="collapse-btn" @click="showCollapsed = !showCollapsed">
          {{ showCollapsed ? '∧ 收起' : '∨ 更多' }}
        </button>
      </div>
    </div>

    <div class="page-content">
      <div class="empty-state" v-if="assetList.length === 0">
        <div class="empty-icon">⚡</div>
        <p class="empty-title">暂无智能成片视频</p>
        <p class="empty-desc">去创建您的第一个智能成片吧</p>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.assets-page {
  padding: 24px;
}

.page-header {
  margin-bottom: 24px;

  .header-left {
    h1 {
      font-size: 24px;
      font-weight: 600;
      color: #1f2937;
      margin: 0 0 4px;
    }

    p {
      font-size: 14px;
      color: #6b7280;
      margin: 0;
    }
  }
}

.page-filters {
  margin-bottom: 24px;
  padding: 16px 20px;
  background: #fff;
  border-radius: 12px;

  .filter-tabs {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;

    .filter-tab {
      padding: 8px 16px;
      border: 1px solid #e5e7eb;
      border-radius: 20px;
      font-size: 13px;
      color: #6b7280;
      background: #fff;
      cursor: pointer;
      transition: all 0.2s;

      &:hover { border-color: #a855f7; color: #a855f7; }
      &.active { background: #a855f7; color: #fff; border-color: #a855f7; }
    }

    .collapse-btn {
      padding: 8px 16px;
      border: none;
      border-radius: 20px;
      font-size: 13px;
      color: #6b7280;
      background: #f3f4f6;
      cursor: pointer;

      &:hover { background: #e5e7eb; }
    }
  }
}

.page-content {
  .empty-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 80px 20px;
    background: #fff;
    border-radius: 12px;

    .empty-icon {
      font-size: 64px;
      margin-bottom: 16px;
      opacity: 0.5;
    }

    .empty-title {
      font-size: 18px;
      color: #6b7280;
      margin: 0 0 8px;
    }

    .empty-desc {
      font-size: 14px;
      color: #9ca3af;
      margin: 0;
    }
  }
}
</style>
