<template>
  <v-row class="studyfilter">
      <v-col cols="12" md="3">
        <v-select
            v-model="options.category1"
            :items="category1Options"
            label="스터디 종류">
            </v-select>
      </v-col>
      <v-col cols="12" md="3">
          <v-select
            v-model="options.category2"
            :items="category2Options"
            label="스터디 내용"
            ></v-select>
      </v-col>
      <v-col cols="12" md="6">
          <v-text-field
            v-model="options.keyword"
            label="키워드"
            placeholder="키워드를 입력해주세요"
            outlined
          ></v-text-field>
      </v-col>
  </v-row>
</template>

<script>
export default {
    name:"Studyfilter",
    props:{
        options:{type:Object}
    },
    data(){
        return{
            category1Options:[
                {text: '전체보기', value: 0},
                {text: '공고별', value: 1},
                {text: '기업별', value: 2},
                {text: '기술별', value: 3},
                {text: '기  타', value: 4}
            ],
            category2Options:[
                {text: '전체보기', value: 0},
                {text: '자소서 준비', value: 1},
                {text: '필기 준비', value: 2},
                {text: '면접 준비', value: 3},
                {text: '기타 등등', value: 4}
            ],
        }
    }
}
</script>

<style>

</style>