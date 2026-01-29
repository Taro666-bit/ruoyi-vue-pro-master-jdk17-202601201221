<script setup lang="ts">
import { ref } from 'vue'
import { Plus, Search } from '@element-plus/icons-vue'

const searchKeyword = ref('')
const productList = ref([])
</script>

<template>
  <div class="products-page">
    <div class="page-header">
      <div class="header-left">
        <h1>产品管理</h1>
        <p>共 {{ productList.length }} 个产品</p>
      </div>
      <div class="header-right">
        <button class="add-btn">
          <el-icon><Plus /></el-icon>
          <span>添加产品</span>
        </button>
      </div>
    </div>

    <div class="page-search">
      <div class="search-box">
        <el-icon class="search-icon"><Search /></el-icon>
        <input
          v-model="searchKeyword"
          type="text"
          placeholder="搜索产品名称..."
          class="search-input"
        />
        <button class="search-btn">
          <el-icon><Search /></el-icon>
          <span>搜索</span>
        </button>
      </div>
    </div>

    <div class="page-content">
      <div class="empty-state" v-if="productList.length === 0">
        <div class="empty-icon">📦</div>
        <p class="empty-title">暂无产品</p>
        <p class="empty-desc">添加您的第一个产品开始使用</p>
        <button class="empty-btn">
          <el-icon><Plus /></el-icon>
          <span>添加产品</span>
        </button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.products-page {
  padding: 24px;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
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

  .add-btn {
    display: flex;
    align-items: center;
    gap: 6px;
    padding: 10px 20px;
    background: #1f2937;
    color: #fff;
    border: none;
    border-radius: 8px;
    font-size: 14px;
    cursor: pointer;

    &:hover { background: #374151; }
  }
}

.page-search {
  margin-bottom: 24px;

  .search-box {
    display: flex;
    align-items: center;
    background: #fff;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    overflow: hidden;
    max-width: 500px;

    .search-icon {
      padding: 0 12px;
      color: #9ca3af;
    }

    .search-input {
      flex: 1;
      padding: 12px 0;
      border: none;
      font-size: 14px;
      outline: none;

      &::placeholder { color: #9ca3af; }
    }

    .search-btn {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 12px 20px;
      background: #1f2937;
      color: #fff;
      border: none;
      font-size: 14px;
      cursor: pointer;

      &:hover { background: #374151; }
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
      margin: 0 0 24px;
    }

    .empty-btn {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 10px 20px;
      background: #1f2937;
      color: #fff;
      border: none;
      border-radius: 8px;
      font-size: 14px;
      cursor: pointer;

      &:hover { background: #374151; }
    }
  }
}
</style>
