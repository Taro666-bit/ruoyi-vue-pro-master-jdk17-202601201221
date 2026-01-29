<script setup lang="ts">
import { ref } from 'vue'
import { CopyDocument, Refresh, ArrowRight } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const userInfo = ref({
  id: '27993cad-9a5b-4084-8589-8e8636ed22dc',
  phone: '18614052693',
  apiKey: ''
})

const inviteStats = ref({
  invited: 0,
  earned: 0
})

const inviteLink = 'https://castar.ai/invite/xxx'

const copyText = (text: string) => {
  navigator.clipboard.writeText(text)
  ElMessage.success('复制成功')
}

const generateApiKey = () => {
  userInfo.value.apiKey = 'sk-' + Math.random().toString(36).substring(2)
  ElMessage.success('API Key 已生成')
}
</script>

<template>
  <div class="profile-page">
    <div class="page-header">
      <h1>个人资料</h1>
      <p>管理您的账户信息和安全设置</p>
      <button class="logout-btn">退出登录</button>
    </div>

    <div class="page-content">
      <div class="content-left">
        <!-- 基本信息 -->
        <div class="info-section">
          <h2>基本信息</h2>

          <div class="info-item">
            <div class="item-icon">👤</div>
            <div class="item-content">
              <span class="item-label">用户ID</span>
              <span class="item-value">{{ userInfo.id }}</span>
            </div>
            <button class="copy-btn" @click="copyText(userInfo.id)">复制</button>
          </div>
        </div>

        <!-- 安全设置 -->
        <div class="info-section">
          <h2>安全设置</h2>

          <div class="info-item">
            <div class="item-icon">📱</div>
            <div class="item-content">
              <span class="item-label">手机号</span>
              <span class="item-value">{{ userInfo.phone }}</span>
            </div>
          </div>

          <div class="info-item">
            <div class="item-icon">🔑</div>
            <div class="item-content">
              <span class="item-label">API Key</span>
              <span class="item-value" v-if="userInfo.apiKey">{{ userInfo.apiKey }}</span>
              <span class="item-value empty" v-else>未生成 API Key</span>
            </div>
            <button class="generate-btn" @click="generateApiKey">
              <el-icon><Refresh /></el-icon>
              <span>生成</span>
            </button>
          </div>
        </div>
      </div>

      <div class="content-right">
        <!-- 邀请好友 -->
        <div class="invite-card">
          <h3>📣 邀请好友</h3>
          <p>分享您的专属邀请链接，每邀请一位好友注册即可获得积分奖励！</p>

          <div class="invite-stats">
            <div class="stat-item">
              <span class="stat-value">{{ inviteStats.invited }}</span>
              <span class="stat-label">已邀请</span>
            </div>
            <div class="stat-item">
              <span class="stat-value">{{ inviteStats.earned }}</span>
              <span class="stat-label">获得积分</span>
            </div>
          </div>

          <div class="invite-link">
            <input type="text" :value="inviteLink" readonly />
            <button class="copy-link-btn" @click="copyText(inviteLink)">
              <el-icon><CopyDocument /></el-icon>
              <span>复制</span>
            </button>
          </div>

          <button class="share-btn">
            查看邀请规则
            <el-icon><ArrowRight /></el-icon>
          </button>
        </div>

        <!-- 安全提示 -->
        <div class="tips-card">
          <h3>🔒 安全提示</h3>
          <ul>
            <li>• 请定期修改密码，确保账户安全</li>
            <li>• 请勿泄露API Key等敏感信息</li>
            <li>• 建议绑定手机号以保护账户安全</li>
            <li>• 请勿与他人分享您的账户凭证</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.profile-page {
  padding: 24px;
  max-width: 1200px;
}

.page-header {
  position: relative;
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

  .logout-btn {
    position: absolute;
    top: 0;
    right: 0;
    padding: 8px 16px;
    background: #fee2e2;
    color: #dc2626;
    border: none;
    border-radius: 6px;
    font-size: 13px;
    cursor: pointer;

    &:hover { background: #fecaca; }
  }
}

.page-content {
  display: flex;
  gap: 24px;
}

.content-left {
  flex: 1;
}

.content-right {
  width: 360px;
}

.info-section {
  background: #fff;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 24px;

  h2 {
    font-size: 16px;
    font-weight: 600;
    color: #1f2937;
    margin: 0 0 20px;
  }

  .info-item {
    display: flex;
    align-items: center;
    gap: 16px;
    padding: 16px 0;
    border-bottom: 1px solid #f3f4f6;

    &:last-child { border-bottom: none; }

    .item-icon {
      width: 40px;
      height: 40px;
      background: #f3f4f6;
      border-radius: 10px;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 18px;
    }

    .item-content {
      flex: 1;

      .item-label {
        display: block;
        font-size: 12px;
        color: #9ca3af;
        margin-bottom: 4px;
      }

      .item-value {
        display: block;
        font-size: 14px;
        color: #1f2937;

        &.empty { color: #9ca3af; }
      }
    }

    .copy-btn,
    .generate-btn {
      display: flex;
      align-items: center;
      gap: 4px;
      padding: 6px 12px;
      border: 1px solid #e5e7eb;
      border-radius: 6px;
      font-size: 12px;
      color: #6b7280;
      background: #fff;
      cursor: pointer;

      &:hover { background: #f9fafb; }
    }
  }
}

.invite-card {
  background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
  border-radius: 12px;
  padding: 24px;
  color: #fff;
  margin-bottom: 24px;

  h3 {
    font-size: 16px;
    font-weight: 600;
    margin: 0 0 8px;
  }

  > p {
    font-size: 13px;
    opacity: 0.9;
    margin: 0 0 20px;
  }

  .invite-stats {
    display: flex;
    gap: 24px;
    margin-bottom: 20px;

    .stat-item {
      text-align: center;

      .stat-value {
        display: block;
        font-size: 28px;
        font-weight: 700;
      }

      .stat-label {
        display: block;
        font-size: 12px;
        opacity: 0.8;
      }
    }
  }

  .invite-link {
    display: flex;
    gap: 8px;
    margin-bottom: 16px;

    input {
      flex: 1;
      padding: 10px 14px;
      border: none;
      border-radius: 8px;
      font-size: 13px;
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      outline: none;

      &::placeholder { color: rgba(255, 255, 255, 0.7); }
    }

    .copy-link-btn {
      display: flex;
      align-items: center;
      gap: 4px;
      padding: 10px 16px;
      background: #fff;
      color: #a855f7;
      border: none;
      border-radius: 8px;
      font-size: 13px;
      cursor: pointer;

      &:hover { background: #f9fafb; }
    }
  }

  .share-btn {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 4px;
    width: 100%;
    padding: 10px;
    background: rgba(255, 255, 255, 0.2);
    color: #fff;
    border: none;
    border-radius: 8px;
    font-size: 13px;
    cursor: pointer;

    &:hover { background: rgba(255, 255, 255, 0.3); }
  }
}

.tips-card {
  background: #fffbeb;
  border: 1px solid #fef3c7;
  border-radius: 12px;
  padding: 24px;

  h3 {
    font-size: 16px;
    font-weight: 600;
    color: #92400e;
    margin: 0 0 16px;
  }

  ul {
    margin: 0;
    padding: 0;
    list-style: none;

    li {
      font-size: 13px;
      color: #92400e;
      line-height: 1.8;
    }
  }
}
</style>
