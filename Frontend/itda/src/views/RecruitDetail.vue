<template>
  <div class="recruitdetail" v-if="Object.keys(recruit).length">
    <v-container class="py-0" white><v-row class="py-0"><v-col><h1>{{recruit.wanted.wantedTitle}}</h1></v-col></v-row></v-container>
    <detail-company-info :company="recruit.company"/>
    <v-container class="py-0" white><v-row><v-col class="pb-0"><h2>직무정보</h2></v-col></v-row></v-container>
    <detail-jobs :jobs="recruit.jobs"/>
    <v-container class="py-0" white></v-container>
    <detail-wanted-info :wanted="recruit.wanted"/>
    <detail-wanted-comment/>
  </div>
</template>

<script>
import DetailCompanyInfo from '../components/DetailCompanyInfo.vue'
import DetailWantedInfo from '../components/DetailWantedInfo.vue'
import DetailJobs from '../components/DetailJobs.vue'
import DetailWantedComment from '../components/DetailWantedComment.vue'

import axios from 'axios'

export default {
  name: 'RecruitDetail',
  components: {
    DetailCompanyInfo,
    DetailWantedInfo,
    DetailJobs,
    DetailWantedComment
  },
  data(){
    return{
      recruit : {}
    }
  },
  methods: {
    getDetailData(){
      axios.get(`http://192.168.31.54:8197/itda/api/getWantedByID/${this.$route.params.id}`)
        .then(response=>{
          this.recruit = response.data
        })
        .catch(()=>{})
    },
  },
  mounted(){
    this.getDetailData()
  }
}
</script>

<style>

</style>