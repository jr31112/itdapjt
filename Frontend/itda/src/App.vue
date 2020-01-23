<template>
  <v-app>
    <v-app-bar app><NaviBar></NaviBar></v-app-bar>
    <v-content>
      <router-view/>
    </v-content>
    <Footer style =  background-position: center></Footer>
  </v-app>
</template>

<script>
import NaviBar from "./components/NaviBar.vue"
import Footer from "./components/Footer.vue"



export default {
  name: 'App',
  components:{
    NaviBar,
    Footer

  }
};
</script>