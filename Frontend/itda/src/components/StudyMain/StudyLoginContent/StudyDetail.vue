<template>
  <v-col class="studydetail" cols="12" md="3" v-if="study.stid">
    <v-hover v-slot:default="{ hover }">
      <v-card light align-center height="200" :elevation="hover ? 16 : 2" class="mx-auto" @click.prevent="openRead">
        <v-card-title>{{study.stname}}</v-card-title>
        <v-divider class="my-0"></v-divider>
        <v-card-text>
          
        </v-card-text>
      </v-card>
    </v-hover>
    <v-dialog v-model="overlayRead" scrollable max-width="500px" white>
        <v-card>
            <v-card-title>{{study.stname}}<v-spacer></v-spacer>{{study.pcnt}}/{{study.maxPcnt}}</v-card-title>
            <v-card-text>
                <p>스터디 종류 : {{category1[study.stype-1]}}</p>
                <p>스터디 주제 : {{study.typeName}}</p>
                <p>스터디 목적 : {{category2[study.sgroup-1]}}</p>
                <p>스터디 내용</p>
                <v-divider></v-divider>
                <p>{{study.content}}</p>
                <p v-for="people in person" :key="people.uid+people.uname">{{people.uname}}</p>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="red darken-1" text @click="del" v-if="isLogin&&ismember&study.captain==userInfo.user.uid">스터디 삭제</v-btn>
                <v-btn color="blue darken-1" text @click="exit" v-if="isLogin&&ismember">스터디 탈퇴</v-btn>
                <v-btn color="black darken-1" text @click="close">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'

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
      openRead(){
        this.overlayRead = true
      },
      close() {
        this.overlayRead = false
      },
      exit() {
        axios.delete('https://i02b201.p.ssafy.io:8197/itda/api/deleteStudyGroup/' + this.study.stid ,{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
        .then(()=>{
          alert('스터디 탈퇴가 완료되었습니다.')
          this.getStudyDetail(this.study.stid)
          this.$emit('update')
        })
      },
      del() {
          axios.delete('https://i02b201.p.ssafy.io:8197/itda/api/deleteStudy/' + this.study.stid ,{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
          .then(
              ()=>{
                  alert('스터디 삭제가 완료되었습니다.')
                  this.getStudyDetail(this.study.stid)
                  this.$emit('update')
          })
      },
      getStudyDetail(id){
        if (id) {
          axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/'+id)
              .then(response => {
                  this.person = response.data
          })
        }
      },
    },
    mounted(){
        this.getStudyDetail(this.study.stid)
    },
    
}
</script>

<style>

</style>