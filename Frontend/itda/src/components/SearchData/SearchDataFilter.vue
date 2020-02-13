<template>
    <v-row>
        <v-spacer></v-spacer>
        <v-col class="pb-0" cols="6">
            <v-text-field
                v-model="options.keyword"
                label="키워드"
                placeholder="키워드를 입력해주세요"
                outlined="outlined"></v-text-field>
        </v-col>
        <v-spacer></v-spacer>
    </v-row>
</template>

<script>
  
    export default {
        name: 'searchdatafilter',
        data(){
          return{
          }
        },
        props: ['options'], 
    }
</script>

<style></style>