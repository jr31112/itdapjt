<template>
  <v-row class="wantedlogin">
    <wanted-stack/>
    <wanted-scrap/>
  </v-row>
</template>

<script>
import WantedStack from './WantedStack.vue'
import WantedScrap from './WantedScrap.vue'

export default {
    name: "wantedlogin",
    components:{
        WantedStack,
        WantedScrap
    }
}
</script>

<style>

</style>