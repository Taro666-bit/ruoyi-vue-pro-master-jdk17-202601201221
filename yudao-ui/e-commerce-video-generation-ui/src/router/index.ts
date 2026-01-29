import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/home/index.vue'),
    meta: { title: '首页' }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    meta: { title: '登录' }
  },
  // 电商工坊 - 独立布局，不显示主菜单
  {
    path: '/app/workshop/ecommerce',
    name: 'EcommerceWorkshop',
    component: () => import('@/views/workshop/ecommerce/index.vue'),
    meta: { title: '电商工坊' },
    children: [
      // AI影棚
      {
        path: 'ai-studio',
        name: 'AIStudio',
        redirect: '/app/workshop/ecommerce/ai-studio/viral-clone'
      },
      {
        path: 'ai-studio/viral-clone',
        name: 'ViralClone',
        component: () => import('@/views/workshop/ai-studio/ViralClone.vue'),
        meta: { title: '爆款复刻' }
      },
      {
        path: 'ai-studio/product-display',
        name: 'ProductDisplay',
        component: () => import('@/views/workshop/ai-studio/ProductDisplay.vue'),
        meta: { title: '产品展示' }
      },
      {
        path: 'ai-studio/share-video',
        name: 'ShareVideo',
        component: () => import('@/views/workshop/ai-studio/ShareVideo.vue'),
        meta: { title: '使用分享视频' }
      },
      {
        path: 'ai-studio/tvc-video',
        name: 'TVCVideo',
        component: () => import('@/views/workshop/ai-studio/TVCVideo.vue'),
        meta: { title: 'TVC模式视频' }
      },
      {
        path: 'ai-studio/digital-human',
        name: 'DigitalHuman',
        component: () => import('@/views/workshop/ai-studio/DigitalHuman.vue'),
        meta: { title: '数字人口播' }
      },
      {
        path: 'ai-studio/face-swap',
        name: 'FaceSwap',
        component: () => import('@/views/workshop/ai-studio/FaceSwap.vue'),
        meta: { title: '视频换脸' }
      },
      {
        path: 'ai-studio/multi-dialog',
        name: 'MultiDialog',
        component: () => import('@/views/workshop/ai-studio/MultiDialog.vue'),
        meta: { title: '多人对话' }
      },
      {
        path: 'ai-studio/lip-sync',
        name: 'LipSync',
        component: () => import('@/views/workshop/ai-studio/LipSync.vue'),
        meta: { title: '对口型' }
      },
      {
        path: 'ai-studio/motion-clone',
        name: 'MotionClone',
        component: () => import('@/views/workshop/ai-studio/MotionClone.vue'),
        meta: { title: '动作模仿' }
      },
      // AI画布
      {
        path: 'ai-canvas',
        name: 'AICanvas',
        redirect: '/app/workshop/ecommerce/ai-canvas/white-bg'
      },
      {
        path: 'ai-canvas/white-bg',
        name: 'WhiteBg',
        component: () => import('@/views/workshop/ai-canvas/WhiteBg.vue'),
        meta: { title: '商品一键白底' }
      },
      {
        path: 'ai-canvas/change-bg',
        name: 'ChangeBg',
        component: () => import('@/views/workshop/ai-canvas/ChangeBg.vue'),
        meta: { title: '产品换背景' }
      },
      {
        path: 'ai-canvas/marketing-template',
        name: 'MarketingTemplate',
        component: () => import('@/views/workshop/ai-canvas/MarketingTemplate.vue'),
        meta: { title: '商品营销模板' }
      },
      {
        path: 'ai-canvas/image-clone',
        name: 'ImageClone',
        component: () => import('@/views/workshop/ai-canvas/ImageClone.vue'),
        meta: { title: '商品图片复刻' }
      },
      {
        path: 'ai-canvas/series-image',
        name: 'SeriesImage',
        component: () => import('@/views/workshop/ai-canvas/SeriesImage.vue'),
        meta: { title: '商品系列图' }
      },
      {
        path: 'ai-canvas/model-display',
        name: 'ModelDisplay',
        component: () => import('@/views/workshop/ai-canvas/ModelDisplay.vue'),
        meta: { title: '模特商品展示' }
      },
      {
        path: 'ai-canvas/model-change',
        name: 'ModelChange',
        component: () => import('@/views/workshop/ai-canvas/ModelChange.vue'),
        meta: { title: '模特换装' }
      },
      {
        path: 'ai-canvas/scene-change',
        name: 'SceneChange',
        component: () => import('@/views/workshop/ai-canvas/SceneChange.vue'),
        meta: { title: '场景变换' }
      },
      // AI脚本编导
      {
        path: 'ai-script',
        name: 'AIScript',
        component: () => import('@/views/workshop/ai-script/index.vue'),
        meta: { title: 'AI脚本编导' }
      },
      // AI配音
      {
        path: 'ai-voice',
        name: 'AIVoice',
        component: () => import('@/views/workshop/ai-voice/index.vue'),
        meta: { title: 'AI配音' }
      }
    ]
  },
  // 素材制作 - 独立布局，不显示主菜单
  {
    path: '/app/workshop/material',
    name: 'MaterialWorkshop',
    component: () => import('@/views/workshop/material/index.vue'),
    meta: { title: '素材制作' }
  },
  {
    path: '/app',
    component: () => import('@/components/layout/MainLayout.vue'),
    redirect: '/app/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        meta: { title: '主页' }
      },
      // 图片编辑
      {
        path: 'workshop/image-edit',
        name: 'ImageEdit',
        component: () => import('@/views/workshop/image-edit/index.vue'),
        meta: { title: '图片编辑' }
      },
      // 资源中心
      {
        path: 'resources/models',
        name: 'MyModels',
        component: () => import('@/views/resources/models/index.vue'),
        meta: { title: '我的模特' }
      },
      {
        path: 'resources/assets',
        name: 'MyAssets',
        component: () => import('@/views/resources/assets/index.vue'),
        meta: { title: '我的资产' }
      },
      {
        path: 'resources/products',
        name: 'MyProducts',
        component: () => import('@/views/resources/products/index.vue'),
        meta: { title: '我的产品' }
      },
      // 账户中心
      {
        path: 'account/profile',
        name: 'Profile',
        component: () => import('@/views/account/profile/index.vue'),
        meta: { title: '个人资料' }
      },
      {
        path: 'account/membership',
        name: 'Membership',
        component: () => import('@/views/account/membership/index.vue'),
        meta: { title: '账单 / 升级会员' }
      }
    ]
  },
  // 兼容旧路由
  {
    path: '/dashboard',
    redirect: '/app/dashboard'
  },
  {
    path: '/workshop/:pathMatch(.*)*',
    redirect: to => `/app/workshop/${to.params.pathMatch}`
  },
  {
    path: '/resources/:pathMatch(.*)*',
    redirect: to => `/app/resources/${to.params.pathMatch}`
  },
  {
    path: '/account/:pathMatch(.*)*',
    redirect: to => `/app/account/${to.params.pathMatch}`
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/'
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
