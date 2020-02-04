<template>
  <v-data-table :headers="headers" 
  :items="desserts" 
  :items-per-page="5"
  :hide-default-footer="true"></v-data-table>
</template>

<script>
export default {
    name: "adminrequest",
    data(){
        return{
           headers:[], 
           desserts:[] 
        }
    }
}
</script>

<style>

</style>