<script setup lang="ts">
import Sidebar from './Sidebar.vue'
</script>

<template>
  <div class="main-layout">
    <Sidebar />
    <div class="main-content">
      <router-view />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.main-layout {
  display: flex;
  min-height: 100vh;
  background: #f9fafb;
}

.main-content {
  flex: 1;
  margin-left: 240px;
  min-height: 100vh;
  overflow-x: hidden;
}
</style>
