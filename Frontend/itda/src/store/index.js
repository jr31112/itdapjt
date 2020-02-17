import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"
import firebase from 'firebase'

//import router from '../router/index.js';
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    userInfo: null, // 필요한 이유는 계속 이메일과 패스워드를 확인 할 수 없으니까, 
    // selectedUser가 allUsers에 찾은 사람을 객체로 userInfo를 저장한다. 
    isLogin: false,
    isLoginError: false,
    isDialog: true
  },
  //뮤테이션과 엑션스 차이는? 
  //뮤테이션 : state 값 변경. 
  //엑션스 : 비지니스 로직이다.
  mutations: {
    //상태값 변화
    // 로그인이 성공했을 때, 
    loginSuccess(state, payload) {
      state.isLogin = true
      state.isLoginError = false
      state.isDialog = false
      state.userInfo = payload
    },
    //로그인이 실패했을 때.
    loginError(state) {
      state.isLogin = false
      state.isLoginError = true
      state.isDialog = true
    },
    logout(state) {
      state.isLogin = false
      state.isLoginError = false
      state.isDialog = true
      state.userInfo = null
      localStorage.clear()
    },
    ////////////추가
    set_name(state, name)
    {
      state.name =name
    }  
  },
  actions:
  {
    login({ dispatch }, { email, pw }) {
      axios
        .post('http://54.180.140.163:8197/itda/api/login', {
          email, pw
        })
        .then(res => {
          if (res.data.state == 'success') {
            let token = res.headers['jwt-auth-token']
            localStorage.setItem("access_token", token)
            localStorage.setItem("uid", res.data.user.uid)
            dispatch("getMemberInfo")
          }
          else {
            return;
          }
        })
        .catch(err => {
          console.log("hi" + err);
        });
    },
    logout({ commit }) {
      commit("logout")
      if(localStorage.getItem("social") == 'social'){
        firebase.auth().signOut()
          .then(()=>{
            alert("소셜 로그아웃 성공!")
          })
          .catch((err)=>{
            alert(err.message)
          })
      }
      else{
        alert("성공적으로 로그아웃 되었습니다.")
      }
    }
    ,
    getMemberInfo({commit}) {
      let token = localStorage.getItem("access_token")
      if(!token){
        return;
      }
      axios.get("http://54.180.140.163:8197/itda/api/getUser", {headers:{"jwt-auth-token": token}})
        .then(res => {
          console.log("정원 확인userinfo:"  )
          console.log(res)
          let userInfo = res.data
          commit('loginSuccess', userInfo)
          localStorage.setItem("mid",userInfo.user.auth)
          
        })
        .catch(() => {
          localStorage.clear();
          alert("다시 로그인해주세요!")
        })
    }
  },
})
