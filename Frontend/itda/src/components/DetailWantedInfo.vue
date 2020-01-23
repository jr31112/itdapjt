<template>
  <v-row class="detailwantedinfo my-2">
      {{wanted}}
      {{jobs}}
  </v-row>
</template>

<script>
export default {
    name: "detailwantedinfo",
    props:{
      wanted:{type:Object},
      jobs:{type:Array}
    }
}
</script>

<style>

</style>