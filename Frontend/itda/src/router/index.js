import Vue from 'vue'
import VueRouter from 'vue-router'
import Mypage from '../views/Mypage.vue'
import Admin from '../views/Admin.vue'
import NotFound from '../views/NotFound.vue'
import QnA from '../views/QnA.vue'
import RecruitMain from '../views/RecruitMain.vue'
import RecruitDetail from '../views/RecruitDetail.vue'
import RecruitCalender from '../views/RecruitCalender.vue'
import StudyMain from '../views/StudyMain.vue'
import SearchResult from '../views/SearchResult.vue'
import UserModify from "../components/Mypage/UserModify.vue"

import store from '../store/index.js'
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
//후에 마이페이지나 관리자를 만들 때 쓰자.
// const onlyAuthUser = (to, from, next)=>
// {
//   if(store.state.userInfo.auth ==! 1) 
//   {
//     alert("관리자 페이지입니다.")
//     next("/") //from에서 / (home,to기능)으로 간다. 
//   } 
//   else {
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
    name: 'recruitmain',
    component: RecruitMain
  },
  {
    path: '/recruit/detail/:id',
    name: 'recruitdetail',
    component: RecruitDetail
  },
  {
    path: '/recruitlist',
    name: 'recruitcalender',
    component: RecruitCalender
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: Mypage
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin
  },
  { 
    path: "/qna",
    name: "QnA",
    component: QnA
  },
  { 
    path: "/study",
    name: "studymain",
    component: StudyMain
  },
  { 
    path: "/searchresult",
    name: "searchresult",
    component: SearchResult
  },
  { 
    path: "/login",
    name: "login",
    // beforeEnter 로그인이 이미 되었는데, 들어갈필요없가 없으니,  라우터 전에 확인하는 것이다. 
    beforeEnter: rejectAuthUser, 
    component: ()=>
    import("../views/RecruitMain.vue")
  },
  {
    path: "/usermodify",
    name: "usermodify",
    component: UserModify

  }

  ]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
