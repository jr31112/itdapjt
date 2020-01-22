<template>
  <v-row class="wanteddefault">
      <wanted-deadline/>
      <wanted-view/>
  </v-row>
</template>

<script>
import WantedView from './WantedView.vue'
import WantedDeadline from './WantedDeadline.vue'

export default {
    name: "wanteddefault",
    components:{
        WantedView,
        WantedDeadline
    }
}
</script>

<style>

</style>