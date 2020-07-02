import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'

Vue.use(VueRouter)

const requireAuth = () => (to, from, next) => {
  const loginPath = `/login?rPath=${encodeURIComponent(to.path)}`
    if(store.state.token===''){
      return next('/login')
    }
    console.log(store.state.token)
  store.getters.isAuth ? next() : next(loginPath)
}


const routes = [
  {
    path:'/main',
    redirect : '/',
    beforeEnter : requireAuth()
  },
  {
    path: '/',
    component: () => import('@/views/Home.vue'),
    beforeEnter: requireAuth()
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/LoginPage.vue'),
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('@/views/SignupPage.vue'),
  },
  {
    path:'/slide',
    name:'Slide',
    component: () => import('@/views/SlideShowPage.vue'),
  },
  {
    path:'/image',
    name:'Image',
    component: () => import('@/views/ImageViewing.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
