<template>
  <v-container class="detailwantedinfo white py-0">
    <v-divider></v-divider>
    <v-row>
      <v-col class="pb-0" cols="12" md="4"><h2>공고상세정보</h2></v-col>
      <v-col cols="12" md="8" align-self="end">
        <v-btn v-for="stack in stacks" :key="stack.sid" color="primary" class="ml-2" @click="goSearch(stack.tname)">#{{stack.tname}}</v-btn>
      </v-col>
    </v-row>
    <v-row><v-col class="pb-0" v-html="wanted.detail" align-self="center"></v-col></v-row>
  </v-container>
</template>

<script>
import router from '../../router'
export default {
  name: "detailwantedinfo",
  props:{
    wanted:{type:Object},
    stacks:{type:Array}
  },
  methods:{
    goSearch(content)
	  {
	    router.push({name:"searchresult", query:{cont: content, type:3}})
	  },
  }
}
</script>

<style>
.renewal_template_style4{
  border: black;
  border-width: medium;
  border-style: inset;
  margin-left: auto;
  margin-right: auto;
}
.new_template_style4{
  border: black;
  border-width: medium;
  border-style: inset;
  margin-left: auto;
  margin-right: auto;
}
</style>