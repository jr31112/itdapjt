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
import StudyDetail from '../views/StudyDetail.vue'
import SearchResult from '../views/SearchResult.vue'
import SearchData from '../views/SearchData.vue'
import UserModify from "../views/UserModify.vue"
import AboutUs from '../components/Aboutus/AboutUs.vue'

Vue.use(VueRouter)
// 관리자가 0 , 기본 1
const rejectAuthUser = (to, from, next)=>
{
  let token = localStorage.getItem("mid") 
  if(token === '0')   
  { 
    next()
  } 
  else 
  {
    alert("관리자페이지입니다.")
    next("/")
  }
}


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
    // 로그인이 이미 되었는데, 들어갈필요없가 없으니,  라우터 전에 확인하는 것이다. 
    beforeEnter : rejectAuthUser, 
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
    path: "/study/:id",
    name: "studydetail",
    component: StudyDetail
  },
  {
    path: "/searchdata/:type",
    name: "searchdata",
    component: SearchData
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
    path: "/usermodify/",
    name: "usermodify",
    component: UserModify
  },
  {
    path: "/aboutus",
    name: "aboutus",
    component: AboutUs
  }

  ]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
