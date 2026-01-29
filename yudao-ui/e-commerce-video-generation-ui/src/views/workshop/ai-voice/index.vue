<script setup lang="ts">
import { ref } from 'vue'

// 声音模型
const voiceModel = ref('castar-v2')

// 声音选择
const selectedVoice = ref('')

// 配音文本
const voiceText = ref('')

// 声音参数
const speechRate = ref(1.3)
const volume = ref(1.0)
const pitch = ref(0.0)
const emotion = ref('happy')

const emotions = [
  { value: 'happy', label: '开心' },
  { value: 'sad', label: '悲伤' },
  { value: 'angry', label: '愤怒' },
  { value: 'neutral', label: '平静' },
  { value: 'excited', label: '兴奋' }
]
</script>

<template>
  <div class="ai-voice-page">
    <div class="page-content">
      <!-- 左侧表单 -->
      <div class="form-area">
        <!-- 页面标题Banner -->
        <div class="page-banner">
          <div class="banner-wave"></div>
        </div>

        <!-- 声音模型 -->
        <div class="form-section">
          <div class="section-header">
            <span class="section-icon">🎤</span>
            <span class="section-title">声音模型</span>
          </div>
          <el-select v-model="voiceModel" class="full-select">
            <el-option label="castar AI配音大模型V2" value="castar-v2" />
            <el-option label="castar AI配音大模型V1" value="castar-v1" />
          </el-select>
          <p class="model-desc">最经典的语音克隆模型，支持音色克隆，专为自然语音对话场景设计</p>
        </div>

        <!-- 声音选择 -->
        <div class="form-section">
          <div class="section-header">
            <span class="section-icon">🔊</span>
            <span class="section-title">声音选择</span>
          </div>
          <el-select v-model="selectedVoice" class="full-select" placeholder="点击选择声音...">
            <el-option label="男声-沉稳" value="male-stable" />
            <el-option label="男声-活力" value="male-active" />
            <el-option label="女声-温柔" value="female-gentle" />
            <el-option label="女声-活泼" value="female-active" />
            <el-option label="童声" value="child" />
          </el-select>
          <button class="clone-btn">
            <span class="clone-icon">🎙️</span>
            <span>克隆音色</span>
          </button>
        </div>

        <!-- 配音文本 -->
        <div class="form-section">
          <div class="section-header">
            <span class="section-icon">📝</span>
            <span class="section-title">配音文本</span>
          </div>
          <p class="text-hint">输入需要配音的文本内容...</p>
          <textarea
            v-model="voiceText"
            class="voice-text"
            placeholder="例如：大家好！欢迎来到我的直播间，今天给大家带来一款超值好物，性价比超高，赶快下单吧！"
            rows="5"
          ></textarea>
          <p class="text-tip">✨ 支持多语言文本输入，AI自动识别</p>
        </div>

        <!-- 声音参数 -->
        <div class="form-section">
          <div class="section-header">
            <span class="section-icon">⚙️</span>
            <span class="section-title">声音参数</span>
          </div>

          <div class="param-item">
            <div class="param-header">
              <span class="param-label">语速</span>
              <span class="param-value">{{ speechRate.toFixed(1) }}</span>
            </div>
            <el-slider v-model="speechRate" :min="0.5" :max="2" :step="0.1" />
            <div class="param-range">
              <span>0.5</span>
              <span>2</span>
            </div>
          </div>

          <div class="param-item">
            <div class="param-header">
              <span class="param-label">音量</span>
              <span class="param-value">{{ volume.toFixed(1) }}</span>
            </div>
            <el-slider v-model="volume" :min="0" :max="2" :step="0.1" />
            <div class="param-range">
              <span>0</span>
              <span>2</span>
            </div>
          </div>

          <div class="param-item">
            <div class="param-header">
              <span class="param-label">音调</span>
              <span class="param-value">{{ pitch.toFixed(1) }}</span>
            </div>
            <el-slider v-model="pitch" :min="-12" :max="12" :step="0.5" />
            <div class="param-range">
              <span>-12</span>
              <span>12</span>
            </div>
          </div>

          <div class="param-item">
            <div class="param-header">
              <span class="param-label">情绪</span>
            </div>
            <el-select v-model="emotion" class="full-select">
              <el-option
                v-for="item in emotions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </div>
        </div>

        <!-- 生成按钮 -->
        <button class="generate-btn">
          立即生成配音
        </button>
      </div>

      <!-- 右侧结果区 -->
      <div class="result-area">
        <div class="result-header">
          <div class="result-title">
            <span class="title-icon">🔊</span>
            <span>最新生成</span>
          </div>
        </div>

        <div class="result-content">
          <div class="empty-state">
            <span class="empty-icon">🔊</span>
            <p class="empty-title">暂无生成记录</p>
            <p class="empty-desc">输入文本内容开始生成配音</p>
          </div>
        </div>

        <!-- 底部播放器 -->
        <div class="audio-player">
          <div class="player-info">
            <span class="player-title">暂未播放</span>
            <span class="player-desc">点击音频卡片开始播放</span>
          </div>
          <div class="player-controls">
            <span class="time">-10s</span>
            <button class="play-btn">▶</button>
            <span class="time">+10s</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.ai-voice-page {
  min-height: calc(100vh - 65px);
}

.page-content {
  display: flex;
  gap: 24px;
  height: 100%;
}

.form-area {
  width: 380px;
  flex-shrink: 0;
}

.page-banner {
  background: linear-gradient(135deg, #3b82f6 0%, #1d4ed8 100%);
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 24px;
  height: 100px;
  position: relative;
  overflow: hidden;

  .banner-wave {
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    height: 40px;
    background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 20'%3E%3Cpath fill='rgba(255,255,255,0.3)' d='M0 10 Q 25 0, 50 10 T 100 10 V20 H0 Z'/%3E%3C/svg%3E") repeat-x;
    background-size: 100px 40px;
    animation: wave 2s linear infinite;
  }

  @keyframes wave {
    0% { background-position-x: 0; }
    100% { background-position-x: 100px; }
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
    margin-bottom: 12px;

    .section-icon { font-size: 16px; }
    .section-title { font-size: 14px; font-weight: 600; color: #1f2937; }
  }

  .full-select {
    width: 100%;
  }

  .model-desc {
    font-size: 12px;
    color: #9ca3af;
    margin: 8px 0 0;
  }

  .clone-btn {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    width: 100%;
    padding: 10px;
    margin-top: 12px;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    background: #fff;
    font-size: 14px;
    color: #374151;
    cursor: pointer;

    &:hover { background: #f9fafb; }

    .clone-icon { font-size: 16px; }
  }

  .text-hint {
    font-size: 12px;
    color: #9ca3af;
    margin: 0 0 12px;
  }

  .voice-text {
    width: 100%;
    padding: 12px;
    border: 1px solid #e5e7eb;
    border-radius: 8px;
    font-size: 14px;
    resize: none;
    outline: none;
    font-family: inherit;

    &:focus { border-color: #3b82f6; }
    &::placeholder { color: #9ca3af; }
  }

  .text-tip {
    font-size: 12px;
    color: #3b82f6;
    margin: 8px 0 0;
  }

  .param-item {
    margin-bottom: 16px;

    &:last-child { margin-bottom: 0; }

    .param-header {
      display: flex;
      justify-content: space-between;
      margin-bottom: 8px;

      .param-label { font-size: 13px; color: #374151; }
      .param-value { font-size: 13px; color: #3b82f6; font-weight: 500; }
    }

    .param-range {
      display: flex;
      justify-content: space-between;
      font-size: 11px;
      color: #9ca3af;
      margin-top: 4px;
    }
  }
}

.generate-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(135deg, #3b82f6 0%, #1d4ed8 100%);
  color: #fff;
  border: none;
  border-radius: 10px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;

  &:hover { opacity: 0.9; }
}

.result-area {
  flex: 1;
  background: #fff;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .result-header {
    padding: 16px 20px;
    border-bottom: 1px solid #e5e7eb;

    .result-title {
      display: flex;
      align-items: center;
      gap: 8px;
      font-size: 14px;
      font-weight: 500;
      color: #1f2937;

      .title-icon { font-size: 16px; }
    }
  }

  .result-content {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;

    .empty-state {
      text-align: center;
      color: #9ca3af;

      .empty-icon { font-size: 48px; display: block; margin-bottom: 16px; }
      .empty-title { font-size: 16px; margin: 0 0 8px; color: #6b7280; }
      .empty-desc { font-size: 14px; margin: 0; }
    }
  }

  .audio-player {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 16px 20px;
    border-top: 1px solid #e5e7eb;
    background: #f9fafb;

    .player-info {
      .player-title { display: block; font-size: 14px; color: #6b7280; }
      .player-desc { display: block; font-size: 12px; color: #9ca3af; }
    }

    .player-controls {
      display: flex;
      align-items: center;
      gap: 16px;

      .time { font-size: 12px; color: #9ca3af; }

      .play-btn {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background: #3b82f6;
        color: #fff;
        border: none;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;

        &:hover { background: #2563eb; }
      }
    }
  }
}
</style>
