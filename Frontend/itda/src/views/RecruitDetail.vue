<template>
  <div class="recruitdetail" v-if="Object.keys(recruit).length">
    <v-container class="py-0" white><v-row class="py-0">
      <v-col>
        <span class="headline">{{recruit.wanted.wantedTitle}}</span>
        <v-icon v-if="isLogin && recruit.scrap" id="icon" color="yellow" size="200%" @click.prevent="doScrap(recruit.wanted.wid)">star</v-icon>
        <v-icon v-if="isLogin && !recruit.scrap" id="icon" color="grey lighten-2" size="200%" @click.prevent="doScrap(recruit.wanted.wid)">star_border</v-icon>
      </v-col>
    </v-row></v-container>
    <recruit-detail-company-info :company="recruit.company"/>
    <v-container class="py-0" white></v-container>
      <recruit-detail-wanted-info :wanted="recruit.wanted" :stacks="recruit.stacks"/>
  </div>
</template>

<script>
import { mapState } from 'vuex'

import RecruitDetailCompanyInfo from '../components/RecruitDetail/RecruitDetailCompanyInfo.vue'
import RecruitDetailWantedInfo from '../components/RecruitDetail/RecruitDetailWantedInfo.vue'

import axios from 'axios'

export default {
  name: 'RecruitDetail',
  components: {
    RecruitDetailCompanyInfo,
    RecruitDetailWantedInfo,
  },
  data(){
    return{
      recruit : {}
    }
  },
  methods: {
    doScrap(wid){
		  axios.post('https://i02b201.p.ssafy.io:8197/itda/api/scrapWanted/',{"wid":wid},{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
            .then(()=>{this.getDetailData()})
	  },
    getDetailData(){
      if (this.isLogin){
        axios.get(`https://i02b201.p.ssafy.io:8197/itda/api/getWantedByID/${this.$route.params.id}`,{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
        .then(response=>{
          this.recruit = response.data
        })
        .catch(()=>{})
      }
      else{
        axios.get(`https://i02b201.p.ssafy.io:8197/itda/api/getWantedByID/${this.$route.params.id}`)
        .then(response=>{
          this.recruit = response.data
        })
        .catch(()=>{})
      }
    },
  },
  mounted(){
    this.getDetailData()
  },
  computed:{
    ...mapState(["isLogin"])
  }
}
</script>

<style>
#icon{
  position:relative;
  top: -5px;
}
</style>