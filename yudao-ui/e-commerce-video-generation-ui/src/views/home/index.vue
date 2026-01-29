<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// Hero区域的展示图片
const heroImages = [
  { id: 1, src: 'https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=200&h=280&fit=crop', type: 'portrait' },
  { id: 2, src: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=200&h=280&fit=crop', type: 'portrait' },
  { id: 3, src: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200&h=280&fit=crop', type: 'portrait' },
  { id: 4, src: 'https://images.unsplash.com/photo-1517841905240-472988babdf9?w=200&h=280&fit=crop', type: 'portrait' },
  { id: 5, src: 'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=200&h=280&fit=crop', type: 'portrait' },
  { id: 6, src: 'https://images.unsplash.com/photo-1524504388940-b1c1722653e1?w=200&h=280&fit=crop', type: 'portrait' }
]

// 统计数据
const stats = [
  { value: '1000+', label: '创作者使用' },
  { value: '98%', label: '好评率' },
  { value: '200+', label: 'AI模板' }
]

// 产品视频展示
const productShowcase = [
  { id: 1, src: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400&h=300&fit=crop', title: '商品链接即可制成片' },
  { id: 2, src: 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400&h=300&fit=crop', title: '' },
  { id: 3, src: 'https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=400&h=300&fit=crop', title: '' }
]

// 数字人展示
const digitalHumanShowcase = [
  { id: 1, src: 'https://images.unsplash.com/photo-1531746020798-e6953c6e8e04?w=300&h=400&fit=crop' },
  { id: 2, src: 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=300&h=400&fit=crop' }
]

// 合作品牌
const brands = [
  { id: 1, name: '10,000+', desc: '活跃用户' },
  { id: 2, name: '1,000,000+', desc: '视频已生成' },
  { id: 3, name: '98%', desc: '用户满意度' },
  { id: 4, name: '< 2分钟', desc: '平均生成时间' }
]

// 会员套餐
const packages = [
  { id: 1, name: '基础版', price: '免费', features: ['每日3次生成', '基础模板', '标准画质'], color: '#6366f1' },
  { id: 2, name: '专业版', price: '¥99/月', features: ['无限生成', '全部模板', '高清画质', '优先渲染'], color: '#a855f7', popular: true },
  { id: 3, name: '企业版', price: '¥299/月', features: ['团队协作', 'API接入', '专属客服', '定制服务'], color: '#ec4899' }
]

const goToLogin = () => {
  router.push('/login')
}

const goToDashboard = () => {
  router.push('/dashboard')
}
</script>

<template>
  <div class="home-page">
    <!-- 导航栏 -->
    <header class="navbar">
      <div class="navbar-content">
        <div class="logo">
          <div class="logo-icon">C</div>
          <span class="logo-text">Castar AI</span>
        </div>
        <nav class="nav-links">
          <a href="#features">产品功能</a>
          <a href="#pricing">价格方案</a>
          <a href="#cases">精彩案例</a>
        </nav>
        <div class="nav-actions">
          <button class="btn-login" @click="goToLogin">登录</button>
          <button class="btn-start" @click="goToLogin">免费开始</button>
        </div>
      </div>
    </header>

    <!-- Hero区域 -->
    <section class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title">
          <span class="gradient-text">激发无限创意</span>
          <br />
          <span class="gradient-text">释放影视潜能</span>
        </h1>
        <p class="hero-desc">AI驱动的视频创作平台，输入TikTok链接，30秒生成营销视频</p>
        <button class="hero-btn" @click="goToLogin">免费体验</button>
      </div>

      <!-- 人物展示网格 -->
      <div class="hero-gallery">
        <div class="gallery-grid">
          <div v-for="img in heroImages" :key="img.id" class="gallery-item">
            <img :src="img.src" alt="" />
          </div>
        </div>
      </div>

      <!-- 统计数据 -->
      <div class="hero-stats">
        <div v-for="stat in stats" :key="stat.label" class="stat-item">
          <span class="stat-value">{{ stat.value }}</span>
          <span class="stat-label">{{ stat.label }}</span>
        </div>
      </div>
    </section>

    <!-- 商品链接制作视频 -->
    <section class="feature-section product-feature" id="features">
      <div class="section-content">
        <div class="feature-info">
          <h2 class="section-title">商品链接即可制成片</h2>
          <p class="section-desc">只需输入商品链接，AI自动分析商品信息，智能生成营销视频</p>
          <ul class="feature-list">
            <li>✓ 支持淘宝、天猫、京东、拼多多等平台</li>
            <li>✓ 自动提取商品图片、标题、卖点</li>
            <li>✓ 多种视频模板一键套用</li>
          </ul>
        </div>
        <div class="feature-showcase">
          <div class="showcase-grid">
            <img v-for="item in productShowcase" :key="item.id" :src="item.src" alt="" />
          </div>
        </div>
      </div>
    </section>

    <!-- 数字人口播 -->
    <section class="feature-section digital-human-feature">
      <div class="section-content reverse">
        <div class="feature-info">
          <h2 class="section-title">AI 数字人自动演绎</h2>
          <p class="section-desc">逼真的数字人形象，自然的语音和表情，让您的产品介绍更具感染力</p>
          <ul class="feature-list">
            <li>✓ 50+ 高清数字人形象</li>
            <li>✓ 支持多国语言配音</li>
            <li>✓ 表情动作自然流畅</li>
          </ul>
        </div>
        <div class="feature-showcase">
          <div class="digital-human-grid">
            <img v-for="item in digitalHumanShowcase" :key="item.id" :src="item.src" alt="" />
          </div>
        </div>
      </div>
    </section>

    <!-- 跨境电商AI赋能 -->
    <section class="feature-section ai-feature">
      <div class="section-content">
        <div class="feature-info">
          <h2 class="section-title">跨境电商运营AI赋能</h2>
          <p class="section-desc">专为跨境电商打造，支持多语言、多平台，助力全球化运营</p>
          <ul class="feature-list">
            <li>✓ 支持英语、日语、韩语等20+语言</li>
            <li>✓ 适配TikTok、YouTube、Instagram等平台</li>
            <li>✓ 智能字幕翻译和配音</li>
          </ul>
        </div>
      </div>
    </section>

    <!-- 用户评价 -->
    <section class="testimonial-section">
      <h2 class="section-title centered">他们都在用 Castar AI</h2>
      <div class="brand-stats">
        <div v-for="brand in brands" :key="brand.id" class="brand-stat">
          <span class="brand-value">{{ brand.name }}</span>
          <span class="brand-label">{{ brand.desc }}</span>
        </div>
      </div>
    </section>

    <!-- 价格方案 -->
    <section class="pricing-section" id="pricing">
      <h2 class="section-title centered">选择适合您的方案</h2>
      <div class="pricing-grid">
        <div
          v-for="pkg in packages"
          :key="pkg.id"
          class="pricing-card"
          :class="{ popular: pkg.popular }"
        >
          <div v-if="pkg.popular" class="popular-badge">最受欢迎</div>
          <h3 class="package-name">{{ pkg.name }}</h3>
          <div class="package-price">{{ pkg.price }}</div>
          <ul class="package-features">
            <li v-for="feature in pkg.features" :key="feature">{{ feature }}</li>
          </ul>
          <button class="package-btn" :style="{ background: pkg.color }">立即开通</button>
        </div>
      </div>
    </section>

    <!-- CTA区域 -->
    <section class="cta-section">
      <h2>准备好开始创作了吗？</h2>
      <p>立即注册，免费体验AI视频创作</p>
      <button class="cta-btn" @click="goToLogin">免费开始使用</button>
    </section>

    <!-- 页脚 -->
    <footer class="footer">
      <div class="footer-content">
        <div class="footer-brand">
          <div class="logo">
            <div class="logo-icon">C</div>
            <span class="logo-text">Castar AI</span>
          </div>
          <p>AI驱动的视频创作平台</p>
        </div>
        <div class="footer-links">
          <div class="link-group">
            <h4>产品</h4>
            <a href="#">AI影棚</a>
            <a href="#">AI画布</a>
            <a href="#">数字人</a>
          </div>
          <div class="link-group">
            <h4>支持</h4>
            <a href="#">使用手册</a>
            <a href="#">联系客服</a>
            <a href="#">常见问题</a>
          </div>
          <div class="link-group">
            <h4>公司</h4>
            <a href="#">关于我们</a>
            <a href="#">用户协议</a>
            <a href="#">隐私政策</a>
          </div>
        </div>
      </div>
      <div class="footer-bottom">
        <p>© 2024 Castar AI. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style lang="scss" scoped>
.home-page {
  min-height: 100vh;
  background: #fff;
}

// 导航栏
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border-bottom: 1px solid #f0f0f0;

  .navbar-content {
    max-width: 1400px;
    margin: 0 auto;
    padding: 16px 40px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .logo {
    display: flex;
    align-items: center;
    gap: 10px;

    .logo-icon {
      width: 36px;
      height: 36px;
      background: #000;
      border-radius: 8px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
      font-size: 18px;
      font-weight: 700;
    }

    .logo-text {
      font-size: 18px;
      font-weight: 600;
      color: #1f2937;
    }
  }

  .nav-links {
    display: flex;
    gap: 32px;

    a {
      color: #6b7280;
      text-decoration: none;
      font-size: 14px;
      transition: color 0.2s;

      &:hover {
        color: #1f2937;
      }
    }
  }

  .nav-actions {
    display: flex;
    gap: 12px;

    .btn-login {
      padding: 8px 20px;
      background: transparent;
      border: 1px solid #e5e7eb;
      border-radius: 8px;
      color: #1f2937;
      font-size: 14px;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        background: #f3f4f6;
      }
    }

    .btn-start {
      padding: 8px 20px;
      background: #000;
      border: none;
      border-radius: 8px;
      color: #fff;
      font-size: 14px;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        background: #1f2937;
      }
    }
  }
}

// Hero区域
.hero-section {
  padding: 140px 40px 80px;
  max-width: 1400px;
  margin: 0 auto;
  text-align: center;

  .hero-content {
    margin-bottom: 60px;
  }

  .hero-title {
    font-size: 56px;
    font-weight: 700;
    line-height: 1.2;
    margin-bottom: 24px;

    .gradient-text {
      background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      background-clip: text;
    }
  }

  .hero-desc {
    font-size: 18px;
    color: #6b7280;
    margin-bottom: 32px;
  }

  .hero-btn {
    padding: 16px 48px;
    background: #000;
    color: #fff;
    border: none;
    border-radius: 12px;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
    }
  }

  .hero-gallery {
    margin-bottom: 60px;

    .gallery-grid {
      display: flex;
      justify-content: center;
      gap: 16px;

      .gallery-item {
        width: 140px;
        height: 200px;
        border-radius: 16px;
        overflow: hidden;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }

        &:nth-child(2), &:nth-child(5) {
          transform: translateY(20px);
        }

        &:nth-child(3), &:nth-child(4) {
          transform: translateY(-20px);
        }
      }
    }
  }

  .hero-stats {
    display: flex;
    justify-content: center;
    gap: 80px;

    .stat-item {
      text-align: center;

      .stat-value {
        display: block;
        font-size: 36px;
        font-weight: 700;
        color: #1f2937;
      }

      .stat-label {
        font-size: 14px;
        color: #6b7280;
      }
    }
  }
}

// 功能区域
.feature-section {
  padding: 100px 40px;

  &:nth-child(even) {
    background: #f9fafb;
  }

  .section-content {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    gap: 80px;

    &.reverse {
      flex-direction: row-reverse;
    }
  }

  .feature-info {
    flex: 1;

    .section-title {
      font-size: 36px;
      font-weight: 700;
      color: #1f2937;
      margin-bottom: 16px;
    }

    .section-desc {
      font-size: 16px;
      color: #6b7280;
      margin-bottom: 24px;
      line-height: 1.6;
    }

    .feature-list {
      list-style: none;
      padding: 0;
      margin: 0;

      li {
        font-size: 14px;
        color: #4b5563;
        margin-bottom: 12px;
        display: flex;
        align-items: center;
        gap: 8px;
      }
    }
  }

  .feature-showcase {
    flex: 1;

    .showcase-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 16px;

      img {
        width: 100%;
        border-radius: 12px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      }
    }

    .digital-human-grid {
      display: flex;
      gap: 16px;

      img {
        width: 200px;
        height: 280px;
        object-fit: cover;
        border-radius: 16px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
      }
    }
  }
}

// 用户评价
.testimonial-section {
  padding: 100px 40px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);

  .section-title.centered {
    text-align: center;
    color: #fff;
    font-size: 36px;
    margin-bottom: 60px;
  }

  .brand-stats {
    max-width: 1000px;
    margin: 0 auto;
    display: flex;
    justify-content: space-around;

    .brand-stat {
      text-align: center;

      .brand-value {
        display: block;
        font-size: 42px;
        font-weight: 700;
        color: #fff;
        margin-bottom: 8px;
      }

      .brand-label {
        font-size: 14px;
        color: rgba(255, 255, 255, 0.8);
      }
    }
  }
}

// 价格方案
.pricing-section {
  padding: 100px 40px;

  .section-title.centered {
    text-align: center;
    font-size: 36px;
    color: #1f2937;
    margin-bottom: 60px;
  }

  .pricing-grid {
    max-width: 1000px;
    margin: 0 auto;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 24px;
  }

  .pricing-card {
    background: #fff;
    border: 1px solid #e5e7eb;
    border-radius: 20px;
    padding: 32px;
    text-align: center;
    position: relative;
    transition: all 0.3s;

    &:hover {
      transform: translateY(-8px);
      box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
    }

    &.popular {
      border-color: #a855f7;
      box-shadow: 0 0 0 2px rgba(168, 85, 247, 0.2);
    }

    .popular-badge {
      position: absolute;
      top: -12px;
      left: 50%;
      transform: translateX(-50%);
      background: linear-gradient(135deg, #a855f7 0%, #6366f1 100%);
      color: #fff;
      font-size: 12px;
      padding: 4px 16px;
      border-radius: 20px;
    }

    .package-name {
      font-size: 20px;
      font-weight: 600;
      color: #1f2937;
      margin-bottom: 8px;
    }

    .package-price {
      font-size: 36px;
      font-weight: 700;
      color: #1f2937;
      margin-bottom: 24px;
    }

    .package-features {
      list-style: none;
      padding: 0;
      margin: 0 0 24px;
      text-align: left;

      li {
        font-size: 14px;
        color: #6b7280;
        padding: 8px 0;
        border-bottom: 1px solid #f3f4f6;

        &:last-child {
          border-bottom: none;
        }
      }
    }

    .package-btn {
      width: 100%;
      padding: 12px;
      color: #fff;
      border: none;
      border-radius: 10px;
      font-size: 14px;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.2s;

      &:hover {
        opacity: 0.9;
      }
    }
  }
}

// CTA区域
.cta-section {
  padding: 100px 40px;
  background: #000;
  text-align: center;

  h2 {
    font-size: 36px;
    color: #fff;
    margin-bottom: 16px;
  }

  p {
    font-size: 16px;
    color: #9ca3af;
    margin-bottom: 32px;
  }

  .cta-btn {
    padding: 16px 48px;
    background: linear-gradient(135deg, #ec4899 0%, #a855f7 50%, #6366f1 100%);
    color: #fff;
    border: none;
    border-radius: 12px;
    font-size: 16px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 10px 30px rgba(168, 85, 247, 0.4);
    }
  }
}

// 页脚
.footer {
  background: #f9fafb;
  padding: 60px 40px 24px;

  .footer-content {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    padding-bottom: 40px;
    border-bottom: 1px solid #e5e7eb;
  }

  .footer-brand {
    .logo {
      display: flex;
      align-items: center;
      gap: 10px;
      margin-bottom: 12px;

      .logo-icon {
        width: 36px;
        height: 36px;
        background: #000;
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #fff;
        font-size: 18px;
        font-weight: 700;
      }

      .logo-text {
        font-size: 18px;
        font-weight: 600;
        color: #1f2937;
      }
    }

    p {
      color: #6b7280;
      font-size: 14px;
    }
  }

  .footer-links {
    display: flex;
    gap: 80px;

    .link-group {
      h4 {
        font-size: 14px;
        font-weight: 600;
        color: #1f2937;
        margin-bottom: 16px;
      }

      a {
        display: block;
        color: #6b7280;
        text-decoration: none;
        font-size: 14px;
        margin-bottom: 12px;
        transition: color 0.2s;

        &:hover {
          color: #1f2937;
        }
      }
    }
  }

  .footer-bottom {
    max-width: 1200px;
    margin: 0 auto;
    padding-top: 24px;
    text-align: center;

    p {
      font-size: 14px;
      color: #9ca3af;
    }
  }
}

// 响应式
@media (max-width: 1024px) {
  .hero-section {
    .hero-title {
      font-size: 42px;
    }
  }

  .feature-section {
    .section-content {
      flex-direction: column;

      &.reverse {
        flex-direction: column;
      }
    }
  }

  .pricing-section {
    .pricing-grid {
      grid-template-columns: 1fr;
      max-width: 400px;
    }
  }
}

@media (max-width: 768px) {
  .navbar {
    .nav-links {
      display: none;
    }
  }

  .hero-section {
    padding: 120px 20px 60px;

    .hero-title {
      font-size: 32px;
    }

    .hero-gallery {
      .gallery-grid {
        flex-wrap: wrap;

        .gallery-item {
          width: 100px;
          height: 140px;
        }
      }
    }

    .hero-stats {
      gap: 40px;

      .stat-item {
        .stat-value {
          font-size: 28px;
        }
      }
    }
  }

  .testimonial-section {
    .brand-stats {
      flex-wrap: wrap;
      gap: 40px;
    }
  }
}
</style>
