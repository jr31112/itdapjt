<template>
<v-container pa-9>
  <v-layout>
  <v-card>
    <v-card-title>
      
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="search"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :search="search"
    ></v-data-table>
    
<!--       :hide-default-footer="true"
 -->
  </v-card>
<v-layout>
</v-container>
</template>

<script>
export default {
    name: "adminwanted",
    data(){
        return{
            
        }
    }
}
</script>

<style>

</style>