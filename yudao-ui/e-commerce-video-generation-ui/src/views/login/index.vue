<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

// 登录方式: qrcode | password
const loginType = ref<'qrcode' | 'password'>('qrcode')

// 表单数据
const formData = reactive({
  phone: '',
  password: '',
  agreed: false
})

// 二维码状态
const qrStatus = ref<'waiting' | 'scanned' | 'expired'>('waiting')

// 登录
const handleLogin = () => {
  if (!formData.agreed) {
    ElMessage.warning('请先同意用户协议和隐私政策')
    return
  }

  if (!formData.phone || !formData.password) {
    ElMessage.warning('请输入手机号和密码')
    return
  }

  // 模拟登录成功
  userStore.setToken('mock-token-12345')
  userStore.setUserInfo({
    id: '27993cad-9a5b-4084-8589-8e8636ed22dc',
    nickname: '用户',
    avatar: '',
    phone: formData.phone,
    email: '',
    points: 270,
    memberLevel: 'free'
  })

  ElMessage.success('登录成功')
  router.push('/app/dashboard')
}

// 微信登录（模拟扫码）
const handleWechatScan = () => {
  // 模拟扫码登录
  userStore.setToken('mock-token-wechat')
  userStore.setUserInfo({
    id: '27993cad-9a5b-4084-8589-8e8636ed22dc',
    nickname: '微信用户',
    avatar: '',
    phone: '186****2693',
    email: '',
    points: 270,
    memberLevel: 'free'
  })

  ElMessage.success('登录成功')
  router.push('/app/dashboard')
}

// 刷新二维码
const refreshQrCode = () => {
  qrStatus.value = 'waiting'
}
</script>

<template>
  <div class="login-page">
    <!-- 左侧品牌区域 -->
    <div class="login-left">
      <div class="brand-content">
        <!-- Logo -->
        <div class="logo">
          <div class="logo-circle">
            <span>C</span>
          </div>
        </div>

        <!-- 标题 -->
        <h1 class="brand-title">
          AI 驱动的<br />
          视频创作平台
        </h1>

        <!-- 描述 -->
        <p class="brand-desc">输入 TikTok 链接，30秒生成营销视频</p>

        <!-- 特性列表 -->
        <div class="feature-list">
          <div class="feature-item">
            <span class="feature-icon">⚡</span>
            <div class="feature-text">
              <h4>快速生成</h4>
              <p>AI 分析产品链接，自动生成脚本和数字人视频</p>
            </div>
          </div>
          <div class="feature-item">
            <span class="feature-icon">🧠</span>
            <div class="feature-text">
              <h4>AI 智能</h4>
              <p>智能脚本生成，自然语音合成，数字人克隆</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧登录表单 -->
    <div class="login-right">
      <div class="login-card">
        <!-- 登录方式切换 -->
        <div class="login-tabs">
          <button
            class="tab-btn"
            :class="{ active: loginType === 'qrcode' }"
            @click="loginType = 'qrcode'"
          >
            <span class="tab-icon">📱</span>
            <span>二维码登录</span>
          </button>
          <button
            class="tab-btn"
            :class="{ active: loginType === 'password' }"
            @click="loginType = 'password'"
          >
            <span class="tab-icon">🔑</span>
            <span>密码登录</span>
          </button>
        </div>

        <!-- 二维码登录 -->
        <div v-if="loginType === 'qrcode'" class="qrcode-login">
          <h2 class="login-title">微信二维码登录</h2>
          <p class="login-subtitle">使用微信扫描二维码快速登录</p>

          <div class="qrcode-wrapper">
            <div class="qrcode-box" @click="handleWechatScan">
              <!-- 模拟二维码 -->
              <div class="qrcode-placeholder">
                <svg viewBox="0 0 100 100" class="qrcode-svg">
                  <rect x="10" y="10" width="20" height="20" fill="#000" />
                  <rect x="35" y="10" width="10" height="10" fill="#000" />
                  <rect x="50" y="10" width="10" height="10" fill="#000" />
                  <rect x="70" y="10" width="20" height="20" fill="#000" />

                  <rect x="10" y="35" width="10" height="10" fill="#000" />
                  <rect x="30" y="35" width="10" height="10" fill="#000" />
                  <rect x="50" y="35" width="20" height="10" fill="#000" />

                  <rect x="10" y="50" width="20" height="10" fill="#000" />
                  <rect x="40" y="50" width="10" height="10" fill="#000" />
                  <rect x="60" y="50" width="10" height="20" fill="#000" />
                  <rect x="80" y="50" width="10" height="10" fill="#000" />

                  <rect x="10" y="70" width="20" height="20" fill="#000" />
                  <rect x="35" y="70" width="15" height="10" fill="#000" />
                  <rect x="55" y="75" width="10" height="15" fill="#000" />
                  <rect x="70" y="70" width="20" height="20" fill="#000" />

                  <!-- 中间logo -->
                  <rect x="40" y="40" width="20" height="20" rx="4" fill="#07c160" />
                  <text x="50" y="55" text-anchor="middle" fill="#fff" font-size="12" font-weight="bold">微</text>
                </svg>
              </div>
              <div v-if="qrStatus === 'expired'" class="qrcode-expired" @click.stop="refreshQrCode">
                <p>二维码已过期</p>
                <button>点击刷新</button>
              </div>
            </div>
            <p class="qrcode-tip">
              <span class="status-dot"></span>
              等待扫码中...
            </p>
            <p class="qrcode-hint">扫码后根据提示完成登录或注册</p>
          </div>
        </div>

        <!-- 密码登录 -->
        <div v-else class="password-login">
          <h2 class="login-title">手机密码登录</h2>
          <p class="login-subtitle">使用手机号和密码快速登录</p>

          <div class="form-content">
            <div class="form-item">
              <label>手机号</label>
              <div class="input-wrapper">
                <span class="input-icon">📱</span>
                <input
                  v-model="formData.phone"
                  type="tel"
                  placeholder="请输入手机号"
                  maxlength="11"
                />
              </div>
            </div>

            <div class="form-item">
              <label>密码</label>
              <div class="input-wrapper">
                <span class="input-icon">🔒</span>
                <input
                  v-model="formData.password"
                  type="password"
                  placeholder="请输入密码"
                />
              </div>
            </div>

            <button class="login-btn" @click="handleLogin">
              立即登录
              <span class="btn-arrow">→</span>
            </button>

            <p class="register-link">
              还没有账号？<a href="#">使用验证码注册</a>
            </p>
          </div>
        </div>

        <!-- 版权信息 -->
        <div class="copyright">
          <p>© 2025 Castar AI. All rights reserved.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-page {
  display: flex;
  min-height: 100vh;
}

// 左侧品牌区域
.login-left {
  flex: 1;
  background: #0a0a0a;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 60px;
  position: relative;

  // 背景装饰
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background:
      radial-gradient(circle at 20% 30%, rgba(168, 85, 247, 0.1) 0%, transparent 50%),
      radial-gradient(circle at 80% 70%, rgba(99, 102, 241, 0.1) 0%, transparent 50%);
  }

  .brand-content {
    position: relative;
    z-index: 1;
    max-width: 480px;
  }

  .logo {
    margin-bottom: 48px;

    .logo-circle {
      width: 80px;
      height: 80px;
      background: #1a1a1a;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      border: 1px solid #333;

      span {
        color: #fff;
        font-size: 36px;
        font-weight: 700;
      }
    }
  }

  .brand-title {
    font-size: 42px;
    font-weight: 700;
    color: #fff;
    line-height: 1.3;
    margin-bottom: 16px;
  }

  .brand-desc {
    font-size: 16px;
    color: #6b7280;
    margin-bottom: 48px;
  }

  .feature-list {
    display: flex;
    flex-direction: column;
    gap: 24px;

    .feature-item {
      display: flex;
      align-items: flex-start;
      gap: 16px;

      .feature-icon {
        width: 40px;
        height: 40px;
        background: #1a1a1a;
        border-radius: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 20px;
        flex-shrink: 0;
      }

      .feature-text {
        h4 {
          color: #fff;
          font-size: 16px;
          font-weight: 600;
          margin-bottom: 4px;
        }

        p {
          color: #6b7280;
          font-size: 14px;
          line-height: 1.5;
        }
      }
    }
  }
}

// 右侧登录区域
.login-right {
  width: 520px;
  background: #fafafa;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
}

.login-card {
  width: 100%;
  max-width: 400px;
  background: #fff;
  border-radius: 20px;
  padding: 32px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);

  .login-tabs {
    display: flex;
    background: #f5f5f5;
    border-radius: 12px;
    padding: 4px;
    margin-bottom: 32px;

    .tab-btn {
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 6px;
      padding: 12px;
      border: none;
      border-radius: 10px;
      background: transparent;
      color: #6b7280;
      font-size: 14px;
      cursor: pointer;
      transition: all 0.2s;

      &.active {
        background: #fff;
        color: #1f2937;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
      }

      .tab-icon {
        font-size: 16px;
      }
    }
  }

  .login-title {
    font-size: 20px;
    font-weight: 600;
    color: #1f2937;
    margin-bottom: 8px;
    text-align: center;
  }

  .login-subtitle {
    font-size: 14px;
    color: #9ca3af;
    margin-bottom: 32px;
    text-align: center;
  }
}

// 二维码登录
.qrcode-login {
  .qrcode-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .qrcode-box {
    width: 200px;
    height: 200px;
    background: #fff;
    border: 1px solid #e5e7eb;
    border-radius: 12px;
    padding: 16px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 16px;
    cursor: pointer;
    position: relative;
    transition: all 0.2s;

    &:hover {
      border-color: #a855f7;
      box-shadow: 0 4px 12px rgba(168, 85, 247, 0.15);
    }

    .qrcode-placeholder {
      width: 100%;
      height: 100%;
    }

    .qrcode-svg {
      width: 100%;
      height: 100%;
    }

    .qrcode-expired {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background: rgba(255, 255, 255, 0.95);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      border-radius: 12px;

      p {
        color: #6b7280;
        margin-bottom: 12px;
      }

      button {
        padding: 8px 20px;
        background: #a855f7;
        color: #fff;
        border: none;
        border-radius: 6px;
        cursor: pointer;
      }
    }
  }

  .qrcode-tip {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 14px;
    color: #1f2937;
    margin-bottom: 8px;

    .status-dot {
      width: 8px;
      height: 8px;
      background: #10b981;
      border-radius: 50%;
      animation: pulse 2s infinite;
    }

    @keyframes pulse {
      0%, 100% {
        opacity: 1;
      }
      50% {
        opacity: 0.5;
      }
    }
  }

  .qrcode-hint {
    font-size: 12px;
    color: #9ca3af;
  }
}

// 密码登录
.password-login {
  .form-content {
    .form-item {
      margin-bottom: 20px;

      label {
        display: block;
        font-size: 14px;
        color: #374151;
        margin-bottom: 8px;
        font-weight: 500;
      }

      .input-wrapper {
        display: flex;
        align-items: center;
        background: #f9fafb;
        border: 1px solid #e5e7eb;
        border-radius: 10px;
        padding: 0 16px;
        transition: all 0.2s;

        &:focus-within {
          border-color: #a855f7;
          background: #fff;
        }

        .input-icon {
          font-size: 18px;
          margin-right: 12px;
        }

        input {
          flex: 1;
          padding: 14px 0;
          border: none;
          background: transparent;
          font-size: 14px;
          outline: none;

          &::placeholder {
            color: #9ca3af;
          }
        }
      }
    }

    .login-btn {
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 8px;
      padding: 14px;
      background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
      color: #fff;
      border: none;
      border-radius: 10px;
      font-size: 16px;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.2s;
      margin-bottom: 16px;

      &:hover {
        opacity: 0.9;
        transform: translateY(-1px);
      }

      .btn-arrow {
        font-size: 18px;
      }
    }

    .register-link {
      text-align: center;
      font-size: 14px;
      color: #6b7280;

      a {
        color: #a855f7;
        text-decoration: none;

        &:hover {
          text-decoration: underline;
        }
      }
    }
  }
}

.copyright {
  margin-top: 32px;
  padding-top: 24px;
  border-top: 1px solid #f0f0f0;
  text-align: center;

  p {
    font-size: 12px;
    color: #9ca3af;
  }
}

// 响应式
@media (max-width: 1024px) {
  .login-left {
    display: none;
  }

  .login-right {
    width: 100%;
  }
}

@media (max-width: 480px) {
  .login-right {
    padding: 20px;
  }

  .login-card {
    padding: 24px;
  }
}
</style>
