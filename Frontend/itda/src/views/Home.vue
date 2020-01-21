<template>
  <div class="home">
    <v-container>
      <wanted-login/>
      <wanted-default/>
    </v-container>
  </div>
</template>

<script>
import WantedLogin from '../components/WantedLogin.vue'
import WantedDefault from '../components/WantedDefault.vue'

export default {
  name: 'home',
  components: {
    WantedLogin,
    WantedDefault
  }
}
</script>
