<template>
  <v-row class="studylogincontent">
    <v-col>
      <v-carousel v-if="myStudyList.length" height="auto" hide-delimiters>
        <v-carousel-item v-for="j in parseInt(myStudyList.length/3)" :key="j">
          <v-container>
            <v-row>
              <study-detail v-for="i in 2" :key="i"/>
            </v-row>
          </v-container>
        </v-carousel-item>
		  </v-carousel>
    </v-col>
  </v-row>
</template>

<script>
import StudyDetail from './StudyLoginContent/StudyDetail.vue'
import axios from 'axios'

export default {
    name:"studylogincontent",
    components:{
      StudyDetail
    },
    data(){
      return{
        myStudyList:[]
      }
    },
    methods:{
      getStudyList(){
        axios.get("http://192.168.31.54:8197/itda/api/getUser", {headers:{"jwt-auth-token": localStorage.getItem('token')}})
        .then(res => {this.myStudyList = res.data.myStudies})
      }
    },
    mounted(){
      this.getStudyList()
    }
}
</script>

<style>

</style>