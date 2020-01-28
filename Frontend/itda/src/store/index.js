import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router/index.js';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: null, // 필요한 이유는 계속 이메일과 패스워드를 확인 할 수 없으니까, 
                    // selectedUser가 allUsers에 찾은 사람을 객체로 userInfo를 저장한다. 
    allUsers: [
      {
        id: 1,
        name: 'hoza',
        email: 'koo_m@naver.com',
        password: '123456',
      },
      {
        id: 2,
        name: 'laza',
        email: 'mingyu0713@naver.com',
        password: '123456',
      },
    ],
//    dialog:false,
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
    // 로그인이 실패했을 때.
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
  //    state.dialog = !state.dialog  
    }
  },
  actions: 
  {
    // 로그인 시도. // 뮤테이션에서 실행하기 위해서, 엑션스에서 commit을해서 사용해야한다.
    login({state, commit }, loginObj)
    {
      // 전체 유저에서 해당 이메일로 유저를 찾는다.
       let selectedUser = null;
			 state.allUsers.forEach(user => {
				if (user.email === loginObj.email) selectedUser = user;
			}) // 내가 text로 넣은 이메일이 allUsers에 있다면 selectedUser에 넣는다
      // 1번 방법
      // selectedUser === null 
			// ? commit('loginError') 
			// : selectedUser.password !== loginObj.password
			// ? commit('loginError') 
			// : commit('loginSuccess')
      
      //2번 방법
      // selectedUser === null || selectedUser.password !== loginObj.password
      // ? commit('loginError') 
      // : commit('loginSuccess')
      //3번 라우터를 적용하는 방법. 
        if(  selectedUser === null || selectedUser.password !== loginObj.password)
        commit('loginError')
        else
        {
          commit('loginSuccess', selectedUser)
          router.push({name: "home"})        
        }
      },
      logout({commit})
      {
        commit("logout")
          router.push({name: "home"})
      },
    },
  modules: {
  }
})
// 
// 
// console.log(user1);