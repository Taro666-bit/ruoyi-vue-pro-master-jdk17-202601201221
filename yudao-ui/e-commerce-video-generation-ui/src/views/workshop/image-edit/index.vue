<script setup lang="ts">
</script>

<template>
  <div class="image-edit-page">
    <div class="page-header">
      <h1>图片编辑</h1>
      <p>强大的在线图片编辑工具</p>
    </div>

    <div class="coming-soon">
      <span class="icon">🖼️</span>
      <p class="title">功能开发中</p>
      <p class="desc">图片编辑功能即将上线，敬请期待...</p>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.image-edit-page {
  padding: 24px;
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

.coming-soon {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 120px 20px;
  background: #fff;
  border-radius: 12px;
  text-align: center;

  .icon { font-size: 64px; margin-bottom: 24px; }
  .title { font-size: 20px; font-weight: 600; color: #1f2937; margin: 0 0 8px; }
  .desc { font-size: 14px; color: #6b7280; margin: 0; }
}
</style>
