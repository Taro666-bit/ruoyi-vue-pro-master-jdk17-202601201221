<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ArrowLeft } from '@element-plus/icons-vue'

interface Tab {
  key: string
  label: string
  icon?: string
}

interface Props {
  title: string
  subtitle?: string
  tabs?: Tab[]
  activeTab?: string
  showBack?: boolean
  rightText?: string
}

const props = withDefaults(defineProps<Props>(), {
  subtitle: '',
  tabs: () => [],
  activeTab: '',
  showBack: true,
  rightText: ''
})

const emit = defineEmits<{
  (e: 'tab-change', tab: string): void
  (e: 'back'): void
}>()

const router = useRouter()

const handleBack = () => {
  emit('back')
  router.back()
}

const handleTabChange = (tab: string) => {
  emit('tab-change', tab)
}
</script>

<template>
  <div class="workshop-layout">
    <!-- Header -->
    <div class="workshop-header">
      <div class="header-left">
        <button v-if="showBack" class="back-btn" @click="handleBack">
          <el-icon><ArrowLeft /></el-icon>
          <span>返回</span>
        </button>
        <div class="header-title">
          <h1>{{ title }}</h1>
          <p v-if="subtitle">{{ subtitle }}</p>
        </div>
      </div>
      <div v-if="tabs.length > 0" class="header-tabs">
        <button
          v-for="tab in tabs"
          :key="tab.key"
          class="tab-btn"
          :class="{ active: activeTab === tab.key }"
          @click="handleTabChange(tab.key)"
        >
          <span v-if="tab.icon" class="tab-icon">{{ tab.icon }}</span>
          {{ tab.label }}
        </button>
      </div>
      <div v-if="rightText" class="header-right">
        <span class="right-text">{{ rightText }}</span>
        <el-icon class="right-icon"><Setting /></el-icon>
      </div>
    </div>

    <!-- Content -->
    <div class="workshop-content">
      <slot />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.workshop-layout {
  min-height: 100vh;
  background: #f9fafb;
}

.workshop-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 24px;
  background: #fff;
  border-bottom: 1px solid #e5e7eb;

  .header-left {
    display: flex;
    align-items: center;
    gap: 16px;

    .back-btn {
      display: flex;
      align-items: center;
      gap: 4px;
      color: #6b7280;
      font-size: 14px;
      cursor: pointer;
      padding: 8px 12px;
      border-radius: 6px;
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
        margin: 4px 0 0;
      }
    }
  }

  .header-tabs {
    display: flex;
    gap: 8px;

    .tab-btn {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 8px 16px;
      border-radius: 8px;
      font-size: 14px;
      color: #6b7280;
      background: transparent;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        background: #f3f4f6;
        color: #1f2937;
      }

      &.active {
        color: #a855f7;
        background: #faf5ff;
      }

      .tab-icon {
        font-size: 16px;
      }
    }
  }

  .header-right {
    display: flex;
    align-items: center;
    gap: 8px;
    color: #6b7280;
    font-size: 14px;

    .right-icon {
      cursor: pointer;

      &:hover {
        color: #1f2937;
      }
    }
  }
}

.workshop-content {
  padding: 24px;
}
</style>
