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

