<script setup lang="ts">
import { ref } from 'vue'
</script>

<template>
  <div class="multi-dialog">
    <div class="page-banner">
      <div class="banner-content">
        <span class="banner-tag">AI多人对话</span>
        <div class="banner-info">
          <img src="https://picsum.photos/80/100?random=507" alt="" class="banner-img" />
          <div class="banner-text">
            <h3>多人对话视频生成</h3>
            <p>上传多个人像，AI生成多人对话视频</p>
          </div>
        </div>
      </div>
    </div>

    <div class="form-section">
      <div class="section-header">
        <span class="section-icon">👥</span>
        <span class="section-title">多人对话配置</span>
      </div>

      <div class="coming-soon">
        <span class="icon">🚧</span>
        <p>功能开发中，敬请期待...</p>
      </div>
    </div>

    <button class="generate-btn" disabled>立即生成</button>
  </div>
</template>

<style lang="scss" scoped>
.multi-dialog {
  max-width: 400px;
}

.page-banner {
  background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 24px;

  .banner-content {
    .banner-tag {
      display: inline-block;
      background: rgba(255, 255, 255, 0.2);
      color: #fff;
      font-size: 12px;
      padding: 4px 10px;
      border-radius: 4px;
      margin-bottom: 12px;
    }

    .banner-info {
      display: flex;
      gap: 12px;

      .banner-img { width: 60px; height: 80px; object-fit: cover; border-radius: 8px; }
      .banner-text {
        color: #fff;
        h3 { font-size: 16px; font-weight: 600; margin: 0 0 4px; }
        p { font-size: 12px; opacity: 0.9; margin: 0; }
      }
    }
  }
}

.form-section {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 16px;

  .section-header {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 16px;

    .section-icon { font-size: 16px; }
    .section-title { font-size: 14px; font-weight: 600; color: #1f2937; }
  }

  .coming-soon {
    text-align: center;
    padding: 40px 20px;
    color: #9ca3af;

    .icon { font-size: 48px; display: block; margin-bottom: 12px; }
    p { margin: 0; font-size: 14px; }
  }
}

.generate-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
  color: #fff;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;

  &:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
}
</style>
