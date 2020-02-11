import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import { iconsSet as icons } from './assets/icons/icons.js'
import CoreuiVue from '@coreui/vue'

Vue.use(CoreuiVue)
Vue.use(require('vue-moment'))
Vue.config.productionTip = false
const ignoreWarnMessage = 'The .native modifier for v-on is only valid on components but it was used on <div>.';
Vue.config.warnHandler = function (msg, vm, trace) {
  // `trace` is the component hierarchy trace
  if (msg === ignoreWarnMessage) {
    msg = null;
    vm = null;
    trace = null;
  }
  console.log(vm)
  console.log(trace)
}
new Vue({
  router,
  beforeCreate()
  {
    this.$store.dispatch("getMemberInfo");
  },
  store,
  vuetify,
  icons,
  render: h => h(App)
}).$mount('#app')

