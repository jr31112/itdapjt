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
    isLoginError: false
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
        state.userInfo = payload
        localStorage.setItem('user_info', payload)
        
    },
    //로그인이 실패했을 때.
    loginError(state)
    { 
        state.isLogin = false
        state.isLoginError= true
    },
    logout(state)
    {
      state.isLogin = false
      state.isLoginError =false
      state.userInfo =null
    }
  },
  actions: 
  {
    login({commit},{email,password})
    {
        let selectedUser = null;
        axios
        .post('https://reqres.in/api/login', {
          email,password
          })
          .then(res=> {
            selectedUser = res;
            //성공시, email,비번이옴.  
            console.log(selectedUser);
            commit('loginSuccess', selectedUser)
          })
          .catch(err=> {
            console.log("hihi"+err);
            commit('loginError')
          });
      },
      logout({commit})
      {
        commit("logout")
          router.push({name: "home"})
      },
      goDetailPage(id){
        router.push({name:'recruitdetail',params:{id:id}})
      }
    },
  modules: {
  }
})
// 
// 
// console.log(user1);