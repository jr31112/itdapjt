<template>
  <v-col class="studydetail" cols="12" md="3" v-if="study.stid">
    <v-hover v-slot:default="{ hover }">
      <v-card light align-center height="200" :elevation="hover ? 16 : 2" class="mx-auto" @click.prevent="goDetail(study.stid)">
        <v-container class="py-0">
          <v-row class="pt-2 px-0">
            <p class="mx-auto title mb-0" id="cardtitle">{{study.stname}}</p>
          </v-row>
        </v-container>
        <v-divider class="my-0"></v-divider>
        <v-container class="pb-0">
            <p class="mx-1 pa-1 deep-purple lighten-3 white--text">{{category1[study.stype-1]}}</p>
            <p class="mx-1 pa-1 light-blue lighten-3 white--text" >{{study.typeName}}</p>
            <p class="mx-1 pa-1 green lighten-3 white--text">{{category2[study.sgroup-1]}}</p>
          
        </v-container>
        <v-card-text>
          
        </v-card-text>
      </v-card>
    </v-hover>
  </v-col>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import router from '../../../router'

export default {
    name:'studydetail',
    props:{
      study:{type:Object}
    },
    data(){
      return{
        person:[],
        overlayRead:false,
        category1:['공고 대비','기업 대비','기술 대비','기타 등등',],
        category2:['자소서 준비', '필기 준비', '면접 준비', '기타 등등',],
      }
    },
    computed:{
      ...mapState(["isLogin", "userInfo"]),
      ismember(){
            for (var i=0;i<this.person.length;i++){
                if(this.person[i].uid === this.userInfo.user.uid)
                    return true
            }
            return false
        },
    },
    methods:{
      getStudyDetail(id){
        if (id) {
          axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/'+id)
              .then(response => {
                  this.person = response.data
          })
        }
      },
      goDetail(id){
        router.push({name:'studydetail', params:{id:id}})
      }
    },
    mounted(){
        this.getStudyDetail(this.study.stid)
    },
    
}
</script>

<style>
#cardtitle{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
</style>