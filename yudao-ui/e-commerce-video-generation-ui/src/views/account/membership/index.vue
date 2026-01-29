<script setup lang="ts">
import { ref } from 'vue'

const currentPlan = ref({
  name: '暂无套餐',
  type: 'free',
  points: 270,
  validDays: 0
})

const plans = ref([
  {
    id: 'trial',
    name: '尝鲜版',
    price: 47.2,
    originalPrice: 59,
    unit: '/月',
    points: 500,
    features: ['500积分/月', '基础功能', '标准客服支持']
  },
  {
    id: 'pro',
    name: '精英版',
    price: 1295.2,
    originalPrice: 1619,
    unit: '/年',
    points: 5000,
    features: ['5000积分/年', '全部功能', '优先客服支持', 'API接口'],
    popular: true
  }
])

const selectedPlan = ref('pro')

const usageRecords = ref([
  { name: 'Basic', type: '基础版', desc: 'Model Base Pro' },
  { name: '爆款复刻', type: 'Boost Starter', desc: '爆款复刻功能' },
  { name: '数字人口播', type: 'Boost Booster', desc: '数字人口播功能' },
  { name: '视频换脸', type: 'Boost Booster Pro', desc: '视频换脸功能' },
  { name: 'Meta Bisique Pro', type: '高级版', desc: 'Meta Bisique Pro功能' }
])
</script>

<template>
  <div class="membership-page">
    <div class="page-header">
      <h1>账单 / 升级会员</h1>
      <p>管理您的订阅和账单信息</p>
    </div>

    <div class="page-content">
      <!-- 当前套餐 -->
      <div class="current-plan">
        <div class="plan-info">
          <div class="plan-badge">
            <span class="badge-icon">🎫</span>
            <span class="badge-text">{{ currentPlan.name }}</span>
          </div>
          <p class="plan-status">账户当前状态</p>
        </div>
        <div class="plan-stats">
          <div class="stat-item">
            <span class="stat-value">{{ currentPlan.points }}</span>
            <span class="stat-label">剩余积分</span>
          </div>
          <div class="stat-item">
            <span class="stat-value">{{ currentPlan.validDays }}</span>
            <span class="stat-label">有效天数</span>
          </div>
        </div>
        <div class="plan-actions">
          <button class="action-btn">零付购</button>
          <button class="action-btn">专付购</button>
        </div>
      </div>

      <!-- 选择套餐 -->
      <div class="select-plan">
        <h2>选择套餐</h2>
        <div class="plan-cards">
          <div
            v-for="plan in plans"
            :key="plan.id"
            class="plan-card"
            :class="{ active: selectedPlan === plan.id, popular: plan.popular }"
            @click="selectedPlan = plan.id"
          >
            <div class="popular-tag" v-if="plan.popular">推荐</div>
            <h3>{{ plan.name }}</h3>
            <div class="plan-price">
              <span class="currency">¥</span>
              <span class="amount">{{ plan.price }}</span>
              <span class="unit">{{ plan.unit }}</span>
            </div>
            <p class="original-price" v-if="plan.originalPrice">
              原价 ¥{{ plan.originalPrice }}
            </p>
            <ul class="plan-features">
              <li v-for="(feature, idx) in plan.features" :key="idx">
                <span class="check">✓</span>
                {{ feature }}
              </li>
            </ul>
            <button class="subscribe-btn">立即订阅</button>
          </div>
        </div>
      </div>

      <!-- 消费明细 -->
      <div class="usage-section">
        <h2>消费明细(积分扣费)</h2>
        <p class="usage-hint">会根据您的使用情况自动扣除积分</p>

        <table class="usage-table">
          <thead>
            <tr>
              <th>功能名称</th>
              <th>类型</th>
              <th>说明</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="record in usageRecords" :key="record.name">
              <td>{{ record.name }}</td>
              <td>{{ record.type }}</td>
              <td>{{ record.desc }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.membership-page {
  padding: 24px;
  max-width: 1000px;
}

.page-header {
  margin-bottom: 32px;

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

.current-plan {
  display: flex;
  align-items: center;
  gap: 40px;
  background: #1f2937;
  border-radius: 16px;
  padding: 24px 32px;
  margin-bottom: 32px;
  color: #fff;

  .plan-info {
    .plan-badge {
      display: flex;
      align-items: center;
      gap: 8px;
      margin-bottom: 8px;

      .badge-icon { font-size: 24px; }
      .badge-text { font-size: 20px; font-weight: 600; }
    }

    .plan-status {
      font-size: 13px;
      opacity: 0.7;
      margin: 0;
    }
  }

  .plan-stats {
    display: flex;
    gap: 32px;
    margin-left: auto;

    .stat-item {
      text-align: center;

      .stat-value {
        display: block;
        font-size: 28px;
        font-weight: 700;
        color: #fbbf24;
      }

      .stat-label {
        display: block;
        font-size: 12px;
        opacity: 0.7;
      }
    }
  }

  .plan-actions {
    display: flex;
    gap: 12px;

    .action-btn {
      padding: 10px 20px;
      background: rgba(255, 255, 255, 0.1);
      color: #fff;
      border: 1px solid rgba(255, 255, 255, 0.2);
      border-radius: 8px;
      font-size: 14px;
      cursor: pointer;

      &:hover { background: rgba(255, 255, 255, 0.2); }
    }
  }
}

.select-plan {
  margin-bottom: 32px;

  h2 {
    font-size: 18px;
    font-weight: 600;
    color: #1f2937;
    margin: 0 0 20px;
  }

  .plan-cards {
    display: flex;
    gap: 20px;

    .plan-card {
      flex: 1;
      background: #fff;
      border: 2px solid #e5e7eb;
      border-radius: 16px;
      padding: 24px;
      cursor: pointer;
      transition: all 0.2s;
      position: relative;

      &:hover { border-color: #a855f7; }
      &.active { border-color: #a855f7; background: #faf5ff; }

      &.popular {
        .popular-tag {
          position: absolute;
          top: -12px;
          right: 20px;
          background: #a855f7;
          color: #fff;
          font-size: 12px;
          padding: 4px 12px;
          border-radius: 12px;
        }
      }

      h3 {
        font-size: 18px;
        font-weight: 600;
        color: #1f2937;
        margin: 0 0 16px;
      }

      .plan-price {
        margin-bottom: 4px;

        .currency { font-size: 16px; color: #1f2937; }
        .amount { font-size: 36px; font-weight: 700; color: #1f2937; }
        .unit { font-size: 14px; color: #6b7280; }
      }

      .original-price {
        font-size: 13px;
        color: #9ca3af;
        text-decoration: line-through;
        margin: 0 0 20px;
      }

      .plan-features {
        margin: 0 0 20px;
        padding: 0;
        list-style: none;

        li {
          display: flex;
          align-items: center;
          gap: 8px;
          font-size: 14px;
          color: #4b5563;
          margin-bottom: 8px;

          .check { color: #10b981; }
        }
      }

      .subscribe-btn {
        width: 100%;
        padding: 12px;
        background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
        color: #fff;
        border: none;
        border-radius: 8px;
        font-size: 14px;
        font-weight: 500;
        cursor: pointer;

        &:hover { opacity: 0.9; }
      }
    }
  }
}

.usage-section {
  h2 {
    font-size: 18px;
    font-weight: 600;
    color: #1f2937;
    margin: 0 0 8px;
  }

  .usage-hint {
    font-size: 13px;
    color: #9ca3af;
    margin: 0 0 20px;
  }

  .usage-table {
    width: 100%;
    background: #fff;
    border-radius: 12px;
    overflow: hidden;
    border-collapse: collapse;

    th, td {
      padding: 14px 20px;
      text-align: left;
      border-bottom: 1px solid #f3f4f6;
    }

    th {
      background: #f9fafb;
      font-size: 13px;
      font-weight: 500;
      color: #6b7280;
    }

    td {
      font-size: 14px;
      color: #1f2937;
    }

    tbody tr:last-child td { border-bottom: none; }
  }
}
</style>
