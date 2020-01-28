import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Recruit from '../views/Recruit.vue'
import RecruitDetail from '../views/RecruitDetail.vue'
import User from '../views/User.vue'
import Admin from '../views/Admin.vue'
import TotalWanted from '../views/TotalWanted.vue'
import store from '../store/index.js';
import NotFound from '../views/NotFound.vue'

Vue.use(VueRouter)
const rejectAuthUser = (to, from, next)=>
{
  if(store.state.isLogin ===true) 
  {
    alert("이미 로그인을 하셨습니다.")
    next("/") //from에서 / (home,to기능)으로 간다. 
  } else {
    next() //to=> from으로 간다. 
  }
}
// 후에 마이페이지나 관리자를 만들 때 쓰자.
// const onlyAuthUser = (to, from, next)=>
// {
//   if(store.state.isLogin === false) 
//   {
//     alert("로그인이 필요한 기능입니다.")
//     next("/") //from에서 / (home,to기능)으로 간다. 
//   } else {
//     next() //to=> from으로 간다. 
//   }
// }

const routes = [
  {
    path: '*',
    name: 'notfound',
    component: NotFound
  },
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
  },
  { 
    path: "/login",
    name: "login",
    // beforeEnter 로그인이 이미 되었는데, 들어갈필요없가 없으니,  라우터 전에 확인하는 것이다. 
    beforeEnter: rejectAuthUser, 
    component: ()=>
    import("../views/Home.vue")
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
