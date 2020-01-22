import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Recruit from '../views/Recruit.vue'
import RecruitDetail from '../views/RecruitDetail.vue'
import User from '../views/User.vue'
import Admin from '../views/Admin.vue'
import TotalWanted from '../views/TotalWanted.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/recruit',
    name: 'recruit',
    component: Recruit
  },
  {
    path: '/recruit/detail',
    name: 'recruitdetail',
    component: RecruitDetail
  },
  {
    path: '/user',
    name: 'user',
    component: User
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin
  },
  {
    path: '/totalwanted',
    name: 'totalwanted',
    component: TotalWanted
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
