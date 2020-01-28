<template>
  <v-container class="detailwantedinfo white py-0">
    <v-divider></v-divider>
    <v-row><v-col class="pb-0"><h2>공고상세정보</h2></v-col></v-row>
    <v-row justify="center"><v-col class="pb-0"><h3>전형 기간</h3></v-col></v-row>
    <v-row>
      <v-col v-if="is_img(wanted.process)"><v-img :src="wanted.process"></v-img></v-col>
      <v-col v-else><p>{{wanted.process}}</p></v-col>
    </v-row>
    <v-row justify="center"><v-col><h3>기타 유의사항</h3></v-col></v-row>
    <v-row justify="center"><v-col><p v-html="wanted.etc"></p></v-col></v-row>
  </v-container>
</template>

<script>
export default {
  name: "detailwantedinfo",
  props:{
    wanted:{type:Object},
  },
  methods:{
    is_img(source){
      if (source.indexOf('.jpg') == -1 & source.indexOf('.png') ==-1 & source.indexOf('.gif') ==-1){
        return false
      }
      else{
        return true
      }
    }
  }
}
</script>

<style>

</style>