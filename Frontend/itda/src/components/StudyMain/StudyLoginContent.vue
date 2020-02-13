<template>
  <v-row class="studylogincontent">
    <v-col>
      <v-carousel v-if="myStudyList.length" height="auto" hide-delimiters>
        <v-carousel-item v-for="j in parseInt(myStudyList.length/4)" :key="j">
          <v-container>
            <v-row>
              <study-detail v-for="i in 4" :key="i" :study="myStudyList[4*j+i-5]" v-on:update="update"/>
            </v-row>
          </v-container>
        </v-carousel-item>
		  </v-carousel>
    </v-col>
  </v-row>
</template>

<script>
import StudyDetail from './StudyLoginContent/StudyDetail.vue'

export default {
    name:"studylogincontent",
    components:{
      StudyDetail,
    },
    props:{
      myStudyList:{type:Array}
    },
    methods:{
      update(){
        console.log('detail1')
        this.$emit('update')
        },
    }
}
</script>

<style>

</style>