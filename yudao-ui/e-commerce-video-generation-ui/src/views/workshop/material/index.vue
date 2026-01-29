<script setup lang="ts">
import { ref, computed } from 'vue'
import { ArrowLeft, Picture, VideoCamera, Clock, Star } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 当前选中的Tab
const activeTab = ref<'image' | 'video'>('video')

// ========== 图片素材相关 ==========
const imageModel = ref('nano-banana-pro')
const imageDescription = ref('')
const imageAspectRatio = ref('1:1')
const imageResolution = ref('high')
const imageBatchCount = ref(1)

// 图片模型选项
const imageModelOptions = [
  { value: 'nano-banana-pro', label: 'Nano Banana Pro' }
]

// 图片宽高比选项
const aspectRatioOptions = [
  { value: '1:1', label: '1:1 (正方形)' },
  { value: '16:9', label: '16:9 (横屏)' },
  { value: '9:16', label: '9:16 (竖屏)' },
  { value: '4:3', label: '4:3' },
  { value: '3:4', label: '3:4' }
]

// 图片分辨率选项
const resolutionOptions = [
  { value: 'high', label: '高清 (High)' },
  { value: 'medium', label: '标清 (Medium)' },
  { value: 'low', label: '低清 (Low)' }
]

// ========== 视频素材相关 ==========
const videoModel = ref('sora2')
const videoDescription = ref('')
const videoDuration = ref('15')
const videoResolution = ref('720p')
const videoFormat = ref('portrait')
const videoBatchCount = ref(1)

// 视频模型选项
const videoModelOptions = [
  { value: 'sora2', label: 'Sora2', desc: '低至 ¥0.4/15s | 成本低、速度快，批量跑量首选' },
  { value: 'sora2-stable', label: 'Sora2 Stable', desc: '低至 ¥2.5/条 | 输出稳定，成功率高，支持传入人像图片' },
  { value: 'sora2-pro', label: 'Sora2 Pro', desc: '支持15s/25s 双时长和1080p 高清画质 | 长视频创作优选' },
  { value: 'sora2-ultra', label: 'Sora2 Ultra', desc: '旗舰画质 | 稳定性与一致性最佳，顶级质量保证' },
  { value: 'seedance-1.5-pro', label: 'Seedance 1.5 Pro | 即梦同款', desc: '支持首尾帧 | 多分镜画面一致性把控最佳' },
  { value: 'kling-v2.6-pro', label: 'Kling v2.6 Pro | 可灵同款', desc: '高度契合提示词，画面表现自然流畅' }
]

// 视频时长选项
const durationOptions = [
  { value: '5', label: '5秒' },
  { value: '10', label: '10秒' },
  { value: '15', label: '15秒' },
  { value: '25', label: '25秒' }
]

// 视频清晰度选项
const videoResolutionOptions = [
  { value: '720p', label: '720p (标清)' },
  { value: '1080p', label: '1080p (高清)' }
]

// 画布格式选项
const formatOptions = [
  { value: 'portrait', label: '竖屏 (Portrait)' },
  { value: 'landscape', label: '横屏 (Landscape)' },
  { value: 'square', label: '方形 (Square)' }
]

// ========== 最近生成 ==========
const recentImages = ref<any[]>([])
const recentVideos = ref([
  {
    id: 1,
    thumbnail: 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=300&h=400&fit=crop',
    type: 'video'
  }
])

// ========== 优秀案例素材 ==========
const showcaseItems = ref([
  { id: 1, image: 'https://images.unsplash.com/photo-1502823403499-6ccfcf4fb453?w=300&h=400&fit=crop', type: 'ai' },
  { id: 2, image: 'https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?w=300&h=400&fit=crop', type: 'ai' },
  { id: 3, image: 'https://images.unsplash.com/photo-1517841905240-472988babdf9?w=300&h=400&fit=crop', type: 'ai' },
  { id: 4, image: 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=300&h=400&fit=crop', type: 'ai' },
  { id: 5, image: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300&h=400&fit=crop', type: 'ai' },
  { id: 6, image: 'https://images.unsplash.com/photo-1524504388940-b1c1722653e1?w=300&h=400&fit=crop', type: 'ai' },
  { id: 7, image: 'https://images.unsplash.com/photo-1519699047748-de8e457a634e?w=300&h=400&fit=crop', type: 'ai' },
  { id: 8, image: 'https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=300&h=400&fit=crop', type: 'ai' },
  { id: 9, image: 'https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?w=300&h=400&fit=crop', type: 'ai' },
  { id: 10, image: 'https://images.unsplash.com/photo-1531746020798-e6953c6e8e04?w=300&h=400&fit=crop', type: 'ai' }
])

// 当前展示的案例（根据Tab切换）
const currentShowcase = computed(() => showcaseItems.value)
const currentRecent = computed(() => activeTab.value === 'image' ? recentImages.value : recentVideos.value)

// 返回上一页
const goBack = () => {
  router.back()
}

// 增加/减少批量数量
const decreaseCount = (type: 'image' | 'video') => {
  if (type === 'image' && imageBatchCount.value > 1) {
    imageBatchCount.value--
  } else if (type === 'video' && videoBatchCount.value > 1) {
    videoBatchCount.value--
  }
}

const increaseCount = (type: 'image' | 'video') => {
  if (type === 'image' && imageBatchCount.value < 10) {
    imageBatchCount.value++
  } else if (type === 'video' && videoBatchCount.value < 10) {
    videoBatchCount.value++
  }
}

// AI优化提示词
const optimizePrompt = () => {
  console.log('优化提示词')
}

// 插入模特
const insertModel = () => {
  console.log('插入模特')
}

// 生成图片
const generateImage = () => {
  console.log('生成图片', {
    model: imageModel.value,
    description: imageDescription.value,
    aspectRatio: imageAspectRatio.value,
    resolution: imageResolution.value,
    batchCount: imageBatchCount.value
  })
}

// 生成视频
const generateVideo = () => {
  console.log('生成视频', {
    model: videoModel.value,
    description: videoDescription.value,
    duration: videoDuration.value,
    resolution: videoResolution.value,
    format: videoFormat.value,
    batchCount: videoBatchCount.value
  })
}
</script>

<template>
  <div class="material-page">
    <!-- 顶部标题栏 -->
    <div class="page-header">
      <button class="back-btn" @click="goBack">
        <el-icon><ArrowLeft /></el-icon>
        <span>返回</span>
      </button>
      <div class="header-divider"></div>
      <div class="header-info">
        <h1>素材制作</h1>
        <p>AI 文本生成视频，通过描述文字快速生成高质量营销视频素材</p>
      </div>
    </div>

    <!-- Tab切换 -->
    <div class="tabs-wrapper">
      <div class="tabs">
        <button
          class="tab-item"
          :class="{ active: activeTab === 'image' }"
          @click="activeTab = 'image'"
        >
          <el-icon><Picture /></el-icon>
          <span>图片素材</span>
        </button>
        <button
          class="tab-item"
          :class="{ active: activeTab === 'video' }"
          @click="activeTab = 'video'"
        >
          <el-icon><VideoCamera /></el-icon>
          <span>视频素材</span>
        </button>
      </div>
    </div>

    <!-- 主内容区 -->
    <div class="main-content">
      <!-- 左侧表单区 -->
      <div class="form-section">
        <!-- 图片素材表单 -->
        <div v-if="activeTab === 'image'" class="form-card">
          <div class="form-title">
            <el-icon><Picture /></el-icon>
            <span>文本生成图片</span>
          </div>

          <!-- 生成模型 -->
          <div class="form-item">
            <label>生成模型</label>
            <el-select v-model="imageModel" placeholder="选择模型" class="full-width">
              <el-option
                v-for="item in imageModelOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </div>

          <!-- 图片描述 -->
          <div class="form-item">
            <label>图片描述 <span class="required">*</span></label>
            <el-input
              v-model="imageDescription"
              type="textarea"
              :rows="5"
              placeholder="请输入图片描述..."
              class="description-input"
            />
            <div class="input-hint">
              <span class="hint-icon">💡</span>
              <span>提示：越详细的描述会生成越精准的结果</span>
            </div>
          </div>

          <!-- 图片宽高比 -->
          <div class="form-item">
            <label>图片宽高比</label>
            <el-select v-model="imageAspectRatio" placeholder="选择宽高比" class="full-width">
              <el-option
                v-for="item in aspectRatioOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <div class="input-sub-hint">选择生成图片的宽高比</div>
          </div>

          <!-- 图片分辨率 -->
          <div class="form-item">
            <label>图片分辨率</label>
            <el-select v-model="imageResolution" placeholder="选择分辨率" class="full-width">
              <el-option
                v-for="item in resolutionOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <div class="input-sub-hint">选择生成图片的清晰度</div>
          </div>

          <!-- 批量生成数量 -->
          <div class="form-item">
            <label>批量生成数量</label>
            <div class="count-control">
              <button class="count-btn" @click="decreaseCount('image')">
                <span>−</span>
              </button>
              <input type="text" :value="imageBatchCount" readonly class="count-input" />
              <button class="count-btn" @click="increaseCount('image')">
                <span>+</span>
              </button>
            </div>
            <div class="input-sub-hint">非会员用户默认生成 1 张图片，升级会员可批量生成</div>
          </div>

          <!-- 参考图片 -->
          <div class="form-item">
            <label>参考图片（可选）</label>
            <div class="upload-area">
              <div class="upload-icon">
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none">
                  <path d="M16 6v20M6 16h20" stroke="#9ca3af" stroke-width="2" stroke-linecap="round"/>
                </svg>
              </div>
              <p class="upload-text">点击上传参考图片</p>
              <p class="upload-hint">支持 JPG、PNG、JPEG，最大 10MB，最多 10 张</p>
            </div>
          </div>

          <!-- 生成按钮 -->
          <button class="generate-btn" @click="generateImage">
            <span class="btn-icon">✨</span>
            <span>开始生成图片</span>
          </button>
        </div>

        <!-- 视频素材表单 -->
        <div v-if="activeTab === 'video'" class="form-card">
          <div class="form-title">
            <el-icon><VideoCamera /></el-icon>
            <span>文本生成视频</span>
          </div>

          <!-- 生成模型 -->
          <div class="form-item">
            <label>生成模型</label>
            <el-select v-model="videoModel" placeholder="选择模型" class="full-width model-select">
              <el-option
                v-for="item in videoModelOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
                <div class="model-option">
                  <div class="model-name">{{ item.label }}</div>
                  <div class="model-desc">{{ item.desc }}</div>
                </div>
              </el-option>
            </el-select>
          </div>

          <!-- 视频描述 -->
          <div class="form-item">
            <label>视频描述 <span class="required">*</span></label>
            <el-input
              v-model="videoDescription"
              type="textarea"
              :rows="5"
              placeholder="请输入视频描述..."
              class="description-input"
            />
            <div class="action-buttons">
              <button class="action-btn" @click="optimizePrompt">
                <span class="btn-icon">✨</span>
                <span>AI优化提示词</span>
              </button>
              <button class="action-btn" @click="insertModel">
                <span class="btn-icon">👤</span>
                <span>插入模特</span>
              </button>
            </div>
            <div class="input-hint">
              <span class="hint-icon">💡</span>
              <span>提示：越详细的描述会生成越精准的结果</span>
            </div>
          </div>

          <!-- 生成时长 -->
          <div class="form-item">
            <label>生成时长（秒）</label>
            <el-select v-model="videoDuration" placeholder="选择时长" class="full-width">
              <el-option
                v-for="item in durationOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </div>

          <!-- 视频清晰度 -->
          <div class="form-item">
            <label>视频清晰度</label>
            <el-select v-model="videoResolution" placeholder="选择清晰度" class="full-width">
              <el-option
                v-for="item in videoResolutionOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <div class="input-sub-hint">OpenAI Sora2 仅支持 720p 清晰度</div>
          </div>

          <!-- 画布格式 -->
          <div class="form-item">
            <label>画布格式</label>
            <el-select v-model="videoFormat" placeholder="选择格式" class="full-width">
              <el-option
                v-for="item in formatOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <div class="input-sub-hint">选择视频的宽高比</div>
          </div>

          <!-- 批量生成数量 -->
          <div class="form-item">
            <label>批量生成数量</label>
            <div class="count-control">
              <button class="count-btn" @click="decreaseCount('video')">
                <span>−</span>
              </button>
              <input type="text" :value="videoBatchCount" readonly class="count-input" />
              <button class="count-btn" @click="increaseCount('video')">
                <span>+</span>
              </button>
            </div>
            <div class="input-sub-hint">非会员用户默认生成 1 个视频，升级会员可批量生成</div>
          </div>

          <!-- 首帧图片 -->
          <div class="form-item">
            <label>首帧图片（可选）</label>
            <div class="upload-area">
              <div class="upload-icon">
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none">
                  <path d="M16 6v20M6 16h20" stroke="#9ca3af" stroke-width="2" stroke-linecap="round"/>
                </svg>
              </div>
              <p class="upload-text">点击上传首帧图片</p>
              <p class="upload-hint">支持本地上传或选择已生成的图片素材</p>
            </div>
          </div>

          <!-- 生成按钮 -->
          <button class="generate-btn" @click="generateVideo">
            <span class="btn-icon">✨</span>
            <span>开始生成视频</span>
          </button>

          <!-- 模型信息 -->
          <div class="model-info">
            <span>当前使用：OpenAI Sora2</span>
          </div>
          <div class="model-features">
            <span class="feature-icon">✨</span>
            <span>支持首帧图片引导、语言选择、时长可选 15/25 秒</span>
          </div>
        </div>

        <!-- 提示技巧 -->
        <div class="tips-card">
          <div class="tips-title">
            <span class="tips-icon">💡</span>
            <span>{{ activeTab === 'image' ? '图片' : '视频' }}生成技巧</span>
          </div>
          <ul class="tips-list">
            <template v-if="activeTab === 'image'">
              <li>描述产品的外观特征和细节</li>
              <li>指定光线、角度、构图方式</li>
              <li>使用专业摄影术语提升效果</li>
              <li>可以多次生成选择最佳结果</li>
              <li>建议使用高分辨率获得更好效果</li>
            </template>
            <template v-else>
              <li>描述产品的动作和运镜方式</li>
              <li>指定光线、角度、镜头语言</li>
              <li>参考专业视频术语提升效果</li>
              <li>可以多次生成选择最佳结果</li>
              <li>建议时长：5-10秒最佳</li>
            </template>
          </ul>
        </div>
      </div>

      <!-- 右侧展示区 -->
      <div class="showcase-section">
        <!-- 最近生成 -->
        <div class="recent-section">
          <div class="section-header">
            <el-icon><Clock /></el-icon>
            <span class="section-title">最近生成</span>
          </div>
          <p class="section-subtitle">您最近生成的{{ activeTab === 'image' ? '图片' : '视频' }}素材</p>

          <div v-if="currentRecent.length === 0" class="empty-state">
            <div class="empty-icon">
              <svg width="48" height="48" viewBox="0 0 48 48" fill="none">
                <rect x="8" y="8" width="32" height="32" rx="4" stroke="#d1d5db" stroke-width="2"/>
                <path d="M16 32l6-8 4 5 6-10 8 13H16z" stroke="#d1d5db" stroke-width="2" stroke-linejoin="round"/>
                <circle cx="18" cy="18" r="3" stroke="#d1d5db" stroke-width="2"/>
              </svg>
            </div>
            <p class="empty-title">还没有生成的素材</p>
            <p class="empty-desc">生成第一个{{ activeTab === 'image' ? '图片' : '视频' }}素材开始吧</p>
          </div>

          <div v-else class="recent-grid">
            <div v-for="item in currentRecent" :key="item.id" class="recent-item">
              <img :src="item.thumbnail" alt="" />
              <div v-if="item.type === 'video'" class="video-badge">视频素材</div>
            </div>
          </div>
        </div>

        <!-- 优秀案例素材 -->
        <div class="showcase-gallery">
          <div class="section-header">
            <el-icon><Star /></el-icon>
            <span class="section-title">优秀案例素材</span>
          </div>
          <p class="section-subtitle">AI 生成的精选视频示例</p>

          <div class="gallery-grid">
            <div v-for="item in currentShowcase" :key="item.id" class="gallery-item">
              <img :src="item.image" alt="" />
              <div class="ai-badge">
                <span class="badge-icon">✨</span>
                <span>AI生成</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.material-page {
  padding: 24px 32px;
  background: #fff;
  min-height: 100vh;
}

// 顶部标题栏
.page-header {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 20px;

  .back-btn {
    display: flex;
    align-items: center;
    gap: 6px;
    padding: 8px 12px;
    background: transparent;
    border: none;
    color: #6b7280;
    font-size: 14px;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
      color: #1f2937;
    }
  }

  .header-divider {
    width: 1px;
    height: 24px;
    background: #e5e7eb;
  }

  .header-info {
    h1 {
      font-size: 18px;
      font-weight: 600;
      color: #1f2937;
      margin: 0 0 2px;
    }

    p {
      font-size: 13px;
      color: #9ca3af;
      margin: 0;
    }
  }
}

// Tab切换
.tabs-wrapper {
  margin-bottom: 20px;

  .tabs {
    display: inline-flex;
    gap: 4px;
    padding: 4px;
    background: #fff;
    border-radius: 12px;
    border: 1px solid #f0f0f0;

    .tab-item {
      display: flex;
      align-items: center;
      gap: 6px;
      padding: 10px 20px;
      border: none;
      border-radius: 8px;
      font-size: 14px;
      color: #6b7280;
      background: transparent;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        background: #f9fafb;
      }

      &.active {
        background: #1f2937;
        color: #fff;
      }
    }
  }
}

// 主内容区
.main-content {
  display: flex;
  gap: 24px;
  align-items: flex-start;
}

// 左侧表单区
.form-section {
  flex: 0 0 380px;
  max-width: 380px;
  display: flex;
  flex-direction: column;
  gap: 16px;

  .form-card {
    background: #fff;
    border: 1px solid #f0f0f0;
    border-radius: 16px;
    padding: 24px;
  }

  .form-title {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 15px;
    font-weight: 600;
    color: #1f2937;
    margin-bottom: 16px;

    .el-icon {
      font-size: 16px;
      color: #a855f7;
    }
  }

  .form-item {
    margin-bottom: 16px;

    &:last-of-type {
      margin-bottom: 0;
    }

    label {
      display: block;
      font-size: 13px;
      font-weight: 500;
      color: #1f2937;
      margin-bottom: 8px;

      .required {
        color: #ef4444;
      }
    }

    .full-width {
      width: 100%;
    }

    .input-hint {
      display: flex;
      align-items: center;
      gap: 4px;
      margin-top: 8px;
      font-size: 12px;
      color: #9ca3af;

      .hint-icon {
        font-size: 12px;
      }
    }

    .input-sub-hint {
      margin-top: 6px;
      font-size: 12px;
      color: #9ca3af;
    }
  }

  // 数量控制
  .count-control {
    display: flex;
    align-items: center;
    gap: 0;
    width: fit-content;

    .count-btn {
      width: 36px;
      height: 36px;
      display: flex;
      align-items: center;
      justify-content: center;
      border: 1px solid #e5e7eb;
      background: #fff;
      color: #6b7280;
      font-size: 16px;
      cursor: pointer;
      transition: all 0.2s;

      &:first-child {
        border-radius: 8px 0 0 8px;
      }

      &:last-child {
        border-radius: 0 8px 8px 0;
      }

      &:hover {
        background: #f9fafb;
        color: #1f2937;
      }
    }

    .count-input {
      width: 60px;
      height: 36px;
      border: 1px solid #e5e7eb;
      border-left: none;
      border-right: none;
      text-align: center;
      font-size: 14px;
      color: #1f2937;
      background: #fff;
      outline: none;
    }
  }

  // 上传区域
  .upload-area {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 24px 16px;
    border: 2px dashed #e5e7eb;
    border-radius: 12px;
    background: #fafafa;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
      border-color: #a855f7;
      background: #faf5ff;
    }

    .upload-icon {
      margin-bottom: 8px;
      opacity: 0.6;
    }

    .upload-text {
      font-size: 13px;
      color: #6b7280;
      margin: 0 0 4px;
    }

    .upload-hint {
      font-size: 11px;
      color: #9ca3af;
      margin: 0;
      text-align: center;
    }
  }

  // 动作按钮
  .action-buttons {
    display: flex;
    gap: 8px;
    margin-top: 10px;

    .action-btn {
      display: flex;
      align-items: center;
      gap: 4px;
      padding: 6px 12px;
      border: 1px solid #e5e7eb;
      border-radius: 6px;
      background: #fff;
      color: #6b7280;
      font-size: 12px;
      cursor: pointer;
      transition: all 0.2s;

      .btn-icon {
        font-size: 12px;
      }

      &:hover {
        border-color: #a855f7;
        color: #a855f7;
        background: #faf5ff;
      }
    }
  }

  // 生成按钮
  .generate-btn {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    padding: 12px;
    background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
    color: #fff;
    border: none;
    border-radius: 10px;
    font-size: 14px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s;
    box-shadow: 0 4px 15px rgba(168, 85, 247, 0.3);
    margin-top: 20px;

    .btn-icon {
      font-size: 14px;
    }

    &:hover {
      opacity: 0.95;
      transform: translateY(-1px);
      box-shadow: 0 6px 20px rgba(168, 85, 247, 0.4);
    }
  }

  // 模型信息
  .model-info {
    text-align: center;
    margin-top: 12px;
    font-size: 12px;
    color: #6b7280;
  }

  .model-features {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 4px;
    margin-top: 6px;
    font-size: 11px;
    color: #a855f7;

    .feature-icon {
      font-size: 11px;
    }
  }
}

// 提示技巧
.tips-card {
  background: #fff;
  border: 1px solid #f0f0f0;
  border-radius: 16px;
  padding: 16px 20px;

  .tips-title {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 14px;
    font-weight: 600;
    color: #1f2937;
    margin-bottom: 12px;

    .tips-icon {
      font-size: 14px;
    }
  }

  .tips-list {
    margin: 0;
    padding-left: 18px;

    li {
      font-size: 12px;
      color: #6b7280;
      line-height: 1.8;
    }
  }
}

// 右侧展示区
.showcase-section {
  flex: 1;
  min-width: 0;
  display: flex;
  flex-direction: column;
  gap: 24px;

  .section-header {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 4px;

    .el-icon {
      font-size: 16px;
      color: #6b7280;
    }

    .section-title {
      font-size: 15px;
      font-weight: 600;
      color: #1f2937;
    }
  }

  .section-subtitle {
    font-size: 13px;
    color: #9ca3af;
    margin: 0 0 12px;
  }
}

// 最近生成
.recent-section {
  background: #fff;
  border: 1px solid #e5e7eb;
  border-radius: 16px;
  padding: 20px;

  .empty-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 32px 20px;
    text-align: center;

    .empty-icon {
      margin-bottom: 12px;
      opacity: 0.5;
    }

    .empty-title {
      font-size: 14px;
      color: #6b7280;
      margin: 0 0 4px;
    }

    .empty-desc {
      font-size: 13px;
      color: #9ca3af;
      margin: 0;
    }
  }

  .recent-grid {
    display: flex;
    gap: 12px;
    flex-wrap: wrap;

    .recent-item {
      position: relative;
      width: 100px;
      height: 140px;
      border-radius: 10px;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      .video-badge {
        position: absolute;
        top: 8px;
        left: 8px;
        padding: 3px 8px;
        background: rgba(168, 85, 247, 0.9);
        color: #fff;
        font-size: 10px;
        border-radius: 4px;
      }
    }
  }
}

// 优秀案例
.showcase-gallery {
  .gallery-grid {
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    gap: 12px;

    .gallery-item {
      position: relative;
      aspect-ratio: 3/4;
      border-radius: 10px;
      overflow: hidden;
      cursor: pointer;
      transition: transform 0.2s;

      &:hover {
        transform: scale(1.02);
      }

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      .ai-badge {
        position: absolute;
        top: 8px;
        left: 8px;
        display: flex;
        align-items: center;
        gap: 3px;
        padding: 4px 8px;
        background: linear-gradient(135deg, #ec4899, #a855f7);
        color: #fff;
        font-size: 10px;
        font-weight: 500;
        border-radius: 5px;

        .badge-icon {
          font-size: 10px;
        }
      }
    }
  }
}

// Element Plus 样式覆盖
:deep(.el-select) {
  .el-input__wrapper {
    border-radius: 8px;
    box-shadow: none;
    border: 1px solid #e5e7eb;
    padding: 2px 10px;

    &:hover {
      border-color: #d1d5db;
    }

    &.is-focus {
      border-color: #a855f7;
      box-shadow: 0 0 0 2px rgba(168, 85, 247, 0.1);
    }
  }

  .el-input__inner {
    font-size: 13px;
  }
}

:deep(.el-textarea) {
  .el-textarea__inner {
    border-radius: 8px;
    border: 1px solid #e5e7eb;
    padding: 10px 12px;
    font-size: 13px;
    resize: none;

    &:hover {
      border-color: #d1d5db;
    }

    &:focus {
      border-color: #a855f7;
      box-shadow: 0 0 0 2px rgba(168, 85, 247, 0.1);
    }

    &::placeholder {
      color: #9ca3af;
    }
  }
}

// 模型选项自定义样式
.model-option {
  padding: 4px 0;

  .model-name {
    font-size: 13px;
    font-weight: 500;
    color: #1f2937;
  }

  .model-desc {
    font-size: 11px;
    color: #9ca3af;
    margin-top: 2px;
  }
}

:deep(.el-select-dropdown__item) {
  height: auto;
  padding: 8px 14px;
  line-height: 1.4;
}
</style>
