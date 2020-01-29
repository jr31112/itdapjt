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
    loginSuccess(state, payload)
    {
        state.isLogin= true
        state.isLoginError = false      
        state.isDialog =false
        state.userInfo = payload
        localStorage.setItem('user_info', payload)
        
    },
    //로그인이 실패했을 때.
    loginError(state)
    { 
        state.isLogin = false
        state.isLoginError= true
        state.isDialog =true
    },
    logout(state)
    {
      state.isLogin = false
      state.isLoginError =false
      state.isDialog= true
      state.userInfo =null
    }
  },
  actions: 
  {
    login({commit},{email,pw})
    {
        axios
        .post('http://192.168.31.54:8197/itda/api/login', {
          email, pw
          })
          .then(res=> {
            if(res.data.state == 'success'){
              let token = res.headers['jwt-auth-token']
              console.log(res.headers)
              alert(token)
              localStorage.setItem("access_token", token)
            }
            else{
              alert("이메일과 비밀번호를 확인하세요.")
              return;
            }
          })
          .catch(err=> {
            console.log("hi"+err);
            commit('loginError')
          });
      },
      logout({commit})
      {
        commit("logout")
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