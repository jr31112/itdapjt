import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios"
//import router from '../router/index.js';
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    userInfo: null, // 필요한 이유는 계속 이메일과 패스워드를 확인 할 수 없으니까, 
    // selectedUser가 allUsers에 찾은 사람을 객체로 userInfo를 저장한다. 
    isLogin: false,
    isLoginError: false,
    isDialog: true,
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
      // localStorage.setItem('user_info', payload)

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
    }
  },
  actions:
  {
    login({ dispatch }, { email, pw }) {
      axios
        .post('http://192.168.31.54:8197/itda/api/login', {
          email, pw
        })
        .then(res => {
          if (res.data.state == 'success') {
            let token = res.headers['jwt-auth-token']

            console.log(res.headers)
            localStorage.setItem("access_token", token)
            localStorage.setItem("uid", res.data.uid)
            dispatch("getMemberInfo")
          }
          else {
            alert("이메일과 비밀번호를 확인하세요.")
            return;
          }
        })
        .catch(err => {
          console.log("hi" + err);
          alert("에러!!!")
        });
    },
    logout({ commit }) {
      alert("성공적으로 로그아웃 되었습니다.")
      commit("logout")
    }
    ,
    getMemberInfo({ commit }) {
      let token = localStorage.getItem("access_token")
      if(!token){
        return;
      }
      let config =
      {
        headers:
        {
          "jwt-auth-token": token
        }
      }
      axios
        .get("http://192.168.31.54:8197/itda/api/getUser", config)
        .then(res => {
          let userInfo = {
            email : res.data.email,
            uname : res.data.uname,
            auth : res.data.auth,
            major : res.data.major,
            uimg : res.data.uimg
          }
          commit('loginSuccess', userInfo)
        })
        .catch(err => {
          alert("토큰이 만료되었거나 정상적인 접근이 아닙니다.");
          alert(err);
        })

    }
  },
  modules:
  {
  }
})
//
// 
// console.log(user1);
// 관리자가 0 , 기본 1