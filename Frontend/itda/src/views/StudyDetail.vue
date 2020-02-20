<template>
  <div class="studydetail">
      <div>
      <study-detail-page :study="study" :person="person"/>
      </div>

      <div>
          <!-- 댓글 -->
      </div>
    <div>
        <!-- 파일 업로드 -->
    </div>
  </div>
  
</template>

<script>
import axios from 'axios'
import StudyDetailPage from '../components/StudyMain/StudyLoginContent/StudyDetailPage.vue'
export default {
    name:"stduydetail",
    data(){
        return{
            person:[],
            study:{}
        }
    },
    components:{
        StudyDetailPage
    },
    methods:{
        getStudy(){
            axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudy/'+this.$route.params.id)
                .then(response => {
                    this.study = response.data
                })
        },
        getPerson(){
            axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/'+this.$route.params.id)
              .then(response => {
                  this.person = response.data
          })
        },
    },
    mounted(){
        this.getStudy()
        this.getPerson()
    },
}
</script>

<style>

</style>