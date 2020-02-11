<template>
  <div class="recruitdetail" v-if="Object.keys(recruit).length">
    <v-container class="py-0" white><v-row class="py-0"><v-col><h1>{{recruit.wanted.wantedTitle}}</h1></v-col></v-row></v-container>
    <recruit-detail-company-info :company="recruit.company"/>
    <v-container class="py-0" white></v-container>
    <recruit-detail-wanted-info :wanted="recruit.wanted"/>
    <recruit-detail-comment/>
  </div>
</template>

<script>
import RecruitDetailCompanyInfo from '../components/RecruitDetail/RecruitDetailCompanyInfo.vue'
import RecruitDetailWantedInfo from '../components/RecruitDetail/RecruitDetailWantedInfo.vue'
import RecruitDetailComment from '../components/RecruitDetail/RecruitDetailComment.vue'

import axios from 'axios'

export default {
  name: 'RecruitDetail',
  components: {
    RecruitDetailCompanyInfo,
    RecruitDetailWantedInfo,
    RecruitDetailComment
  },
  data(){
    return{
      recruit : {}
    }
  },
  methods: {
    getDetailData(){
      axios.get(`https://192.168.31.54:8197/itda/api/getWantedByID/${this.$route.params.id}`)
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