<script setup lang="ts">
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'

const filterStatus = ref('all')
const showSora2Only = ref(false)
const modelList = ref([])
</script>

<template>
  <div class="models-page">
    <div class="page-header">
      <div class="header-left">
        <h1>我的模特</h1>
        <p>管理您的AI数字人模特库</p>
      </div>
      <div class="header-right">
        <button class="add-btn">
          <el-icon><Plus /></el-icon>
          <span>新建模特</span>
        </button>
      </div>
    </div>

    <div class="page-filters">
      <div class="filter-left">
        <span class="filter-label">状态：</span>
        <el-select v-model="filterStatus" class="status-select">
          <el-option label="全部状态" value="all" />
          <el-option label="训练中" value="training" />
          <el-option label="已完成" value="completed" />
          <el-option label="已失败" value="failed" />
        </el-select>
        <label class="sora-filter">
          <input type="checkbox" v-model="showSora2Only" />
          <span>仅显示Sora2模特</span>
        </label>
      </div>
      <div class="filter-right">
        <span class="count-text">共 {{ modelList.length }} 个模特</span>
      </div>
    </div>

    <div class="page-content">
      <div class="empty-state" v-if="modelList.length === 0">
        <div class="empty-icon">👤</div>
        <p class="empty-title">暂无模特</p>
        <p class="empty-desc">创建您的第一个AI数字人模特</p>
        <button class="empty-btn">
          <el-icon><Plus /></el-icon>
          <span>新建模特</span>
        </button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.models-page {
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

.page-filters {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  padding: 16px 20px;
  background: #fff;
  border-radius: 12px;

  .filter-left {
    display: flex;
    align-items: center;
    gap: 16px;

    .filter-label {
      font-size: 14px;
      color: #6b7280;
    }

    .status-select {
      width: 140px;
    }

    .sora-filter {
      display: flex;
      align-items: center;
      gap: 8px;
      font-size: 14px;
      color: #6b7280;
      cursor: pointer;

      input { cursor: pointer; }
    }
  }

  .filter-right {
    .count-text {
      font-size: 14px;
      color: #9ca3af;
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
