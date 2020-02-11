<template>
  <v-container class="detailwantedinfo white py-0">
    <v-divider></v-divider>
    <v-row><v-col class="pb-0"><h2>공고상세정보</h2></v-col></v-row>
    <iframe :src="wanted.detail" frameborder="0"></iframe>
  </v-container>
</template>

<script>
export default {
  name: "detailwantedinfo",
  props:{
    wanted:{type:Object},
  },
}
</script>

<style>

</style>