import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export interface UserInfo {
  id: string
  nickname: string
  avatar: string
  phone: string
  email: string
  points: number
  memberLevel: 'free' | 'basic' | 'pro' | 'enterprise'
  memberExpireDate?: string
}

export const useUserStore = defineStore(
  'user',
  () => {
    const token = ref<string>('')
    const userInfo = ref<UserInfo | null>(null)

    const isLoggedIn = computed(() => !!token.value)

    const memberLevelText = computed(() => {
      const levelMap = {
        free: '免费版',
        basic: '尝鲜版',
        pro: '精英版',
        enterprise: '企业版'
      }
      return userInfo.value ? levelMap[userInfo.value.memberLevel] : '免费版'
    })

    const setToken = (newToken: string) => {
      token.value = newToken
    }

    const setUserInfo = (info: UserInfo) => {
      userInfo.value = info
    }

    const logout = () => {
      token.value = ''
      userInfo.value = null
    }

    return {
      token,
      userInfo,
      isLoggedIn,
      memberLevelText,
      setToken,
      setUserInfo,
      logout
    }
  },
  {
    persist: true
  }
)
