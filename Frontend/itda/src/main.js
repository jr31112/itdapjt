import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vuetify from './plugins/vuetify';
import { iconsSet as icons } from './assets/icons/icons.js'
import CoreuiVue from '@coreui/vue'
import firebase from 'firebase'

// Your web app's Firebase configuration
var firebaseConfig = {
  apiKey: "AIzaSyAi0OWwP8o1_uDvenwNxMqkvLb_JMLo11c",
  authDomain: "itda-266600.firebaseapp.com",
  databaseURL: "https://itda-266600.firebaseio.com",
  projectId: "itda-266600",
  storageBucket: "itda-266600.appspot.com",
  messagingSenderId: "66784820057",
  appId: "1:66784820057:web:db0bf89f0ca7e169186a48"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

Vue.use(CoreuiVue)
Vue.use(require('vue-moment'))

// Vue.config.productionTip = false
// const ignoreWarnMessage = 'The .native modifier for v-on is only valid on components but it was used on <div>.';
// Vue.config.warnHandler = function (msg, vm, trace) {
//   // `trace` is the component hierarchy trace
//   if (msg === ignoreWarnMessage) {
//     msg = null;
//     vm = null;
//     trace = null;
//   }else{
//     console.log(vm + trace)
//   }  
// },
new Vue({
  beforeCreate()
  {
     this.$store.dispatch("getMemberInfo");
  },
  router,
  store,
  vuetify,
  icons,
  render: h => h(App)
}).$mount('#app')

