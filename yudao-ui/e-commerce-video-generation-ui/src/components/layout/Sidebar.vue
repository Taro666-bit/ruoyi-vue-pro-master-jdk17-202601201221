<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {
  House,
  VideoCamera,
  Picture,
  Tickets,
  Headset,
  User,
  Folder,
  Box,
  Setting,
  CreditCard,
  ChatLineSquare,
  Document,
  ArrowLeft
} from '@element-plus/icons-vue'

interface MenuItem {
  path: string
  title: string
  icon: any
  children?: MenuItem[]
}

const route = useRoute()
const router = useRouter()

const menuList: MenuItem[] = [
  { path: '/app/dashboard', title: '主页', icon: House },
  {
    path: '/app/workshop',
    title: '工作台',
    icon: VideoCamera,
    children: [
      { path: '/app/workshop/ecommerce', title: '电商工坊', icon: VideoCamera },
      { path: '/app/workshop/material', title: '素材制作', icon: Picture },
      { path: '/app/workshop/image-edit', title: '图片编辑', icon: Picture }
    ]
  },
  {
    path: '/app/resources',
    title: '资源中心',
    icon: Folder,
    children: [
      { path: '/app/resources/models', title: '我的模特', icon: User },
      { path: '/app/resources/assets', title: '我的资产', icon: Box },
      { path: '/app/resources/products', title: '我的产品', icon: Folder }
    ]
  },
  {
    path: '/app/account',
    title: '账户中心',
    icon: Setting,
    children: [
      { path: '/app/account/profile', title: '个人资料', icon: User },
      { path: '/app/account/membership', title: '账单 / 升级会员', icon: CreditCard }
    ]
  }
]

const currentPath = computed(() => route.path)

const handleMenuClick = (path: string) => {
  router.push(path)
}

const isActiveMenu = (item: MenuItem): boolean => {
  if (currentPath.value === item.path) return true
  if (item.children) {
    return item.children.some((child) => currentPath.value.startsWith(child.path))
  }
  return currentPath.value.startsWith(item.path)
}

const isActiveChild = (path: string): boolean => {
  return currentPath.value === path || currentPath.value.startsWith(path + '/')
}
</script>

<template>
  <div class="sidebar">
    <!-- Logo -->
    <div class="sidebar-logo">
      <div class="logo-icon">
        <span class="logo-text">C</span>
      </div>
      <div class="logo-info">
        <span class="logo-name">创影</span>
        <span class="logo-desc">视频创作平台</span>
      </div>
    </div>

    <!-- Menu -->
    <nav class="sidebar-menu">
      <template v-for="item in menuList" :key="item.path">
        <!-- 有子菜单 -->
        <div v-if="item.children" class="menu-group">
          <div class="menu-group-title" :class="{ active: isActiveMenu(item) }">
            <el-icon><component :is="item.icon" /></el-icon>
            <span>{{ item.title }}</span>
          </div>
          <div class="menu-children">
            <div
              v-for="child in item.children"
              :key="child.path"
              class="menu-item"
              :class="{ active: isActiveChild(child.path) }"
              @click="handleMenuClick(child.path)"
            >
              <el-icon><component :is="child.icon" /></el-icon>
              <span>{{ child.title }}</span>
            </div>
          </div>
        </div>
        <!-- 无子菜单 -->
        <div
          v-else
          class="menu-item"
          :class="{ active: isActiveMenu(item) }"
          @click="handleMenuClick(item.path)"
        >
          <el-icon><component :is="item.icon" /></el-icon>
          <span>{{ item.title }}</span>
        </div>
      </template>
    </nav>

    <!-- Bottom Actions -->
    <div class="sidebar-bottom">
      <button class="action-btn guide-btn">
        <el-icon><Document /></el-icon>
        <span>使用手册</span>
      </button>
      <button class="action-btn task-btn">
        <el-icon><Tickets /></el-icon>
        <span>任务中心</span>
      </button>

      <!-- 套餐信息 -->
      <div class="package-info">
        <div class="package-header">
          <span class="package-badge">当前套餐</span>
        </div>
        <div class="package-content">
          <span class="package-name">免费版</span>
          <span class="package-upgrade">升级 ▸</span>
        </div>
        <div class="package-points">
          <span>积分 270</span>
        </div>
      </div>

      <!-- 联系客服 -->
      <button class="contact-btn">
        <el-icon><ChatLineSquare /></el-icon>
        <span>联系客服 ▸</span>
        <span class="expand-icon">⌄</span>
      </button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.sidebar {
  width: 240px;
  height: 100vh;
  background: #fff;
  border-right: 1px solid #e5e7eb;
  display: flex;
  flex-direction: column;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 100;
}

.sidebar-logo {
  display: flex;
  align-items: center;
  padding: 20px 16px;
  gap: 12px;

  .logo-icon {
    width: 40px;
    height: 40px;
    background: #000;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;

    .logo-text {
      color: #fff;
      font-size: 20px;
      font-weight: 700;
    }
  }

  .logo-info {
    display: flex;
    flex-direction: column;

    .logo-name {
      font-size: 16px;
      font-weight: 600;
      color: #1f2937;
    }

    .logo-desc {
      font-size: 12px;
      color: #9ca3af;
    }
  }
}

.sidebar-menu {
  flex: 1;
  overflow-y: auto;
  padding: 8px 12px;

  .menu-group {
    margin-bottom: 8px;

    .menu-group-title {
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 10px 12px;
      color: #6b7280;
      font-size: 14px;
      cursor: default;

      &.active {
        color: #1f2937;
      }

      .el-icon {
        font-size: 18px;
      }
    }

    .menu-children {
      padding-left: 12px;
    }
  }

  .menu-item {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 10px 12px;
    border-radius: 8px;
    color: #6b7280;
    font-size: 14px;
    cursor: pointer;
    transition: all 0.2s;
    margin-bottom: 4px;

    &:hover {
      background: #f3f4f6;
      color: #1f2937;
    }

    &.active {
      background: #000;
      color: #fff;
    }

    .el-icon {
      font-size: 18px;
    }
  }
}

.sidebar-bottom {
  padding: 12px;
  border-top: 1px solid #e5e7eb;

  .action-btn {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    padding: 10px;
    border-radius: 8px;
    font-size: 14px;
    margin-bottom: 8px;
    cursor: pointer;
    transition: all 0.2s;

    &.guide-btn {
      border: 1px solid #e5e7eb;
      color: #1f2937;
      background: #fff;

      &:hover {
        background: #f3f4f6;
      }
    }

    &.task-btn {
      border: 1px solid #a855f7;
      color: #a855f7;
      background: #fff;

      &:hover {
        background: #faf5ff;
      }
    }
  }

  .package-info {
    background: #1f2937;
    border-radius: 12px;
    padding: 12px;
    margin-bottom: 12px;

    .package-header {
      margin-bottom: 8px;

      .package-badge {
        background: #fbbf24;
        color: #1f2937;
        font-size: 12px;
        padding: 2px 8px;
        border-radius: 4px;
        font-weight: 500;
      }
    }

    .package-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 4px;

      .package-name {
        color: #fff;
        font-size: 14px;
        font-weight: 500;
      }

      .package-upgrade {
        color: #a855f7;
        font-size: 12px;
        cursor: pointer;
      }
    }

    .package-points {
      color: #9ca3af;
      font-size: 12px;
    }
  }

  .contact-btn {
    width: 100%;
    display: flex;
    align-items: center;
    gap: 8px;
    padding: 12px;
    background: linear-gradient(135deg, #a855f7 0%, #6366f1 100%);
    color: #fff;
    border-radius: 8px;
    font-size: 14px;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
      opacity: 0.9;
    }

    .expand-icon {
      margin-left: auto;
    }
  }
}
</style>
