<template>
  <tr @click="openRead">
    <td class="text-center">{{study.stid}}</td>
    <td class="text-center" id="response">{{category1[study.stype-1]}}</td>
    <td class="text-center">{{study.typeName}}</td>
    <td class="text-center" id="response">{{category2[study.sgroup-1]}}</td>
    <td class="text-left">{{study.stname}}</td>
    <td class="text-center">{{study.pcnt}}/{{study.maxPcnt}}</td>
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
                <v-btn color="blue darken-1" text @click="enter" v-if="isLogin&&!ismember&&study.pcnt<study.maxPcnt">스터디 가입</v-btn>
                <v-btn color="blue darken-1" text @click="exit" v-if="isLogin&&ismember">스터디 탈퇴</v-btn>
                <v-btn color="black darken-1" text @click="close">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
  </tr>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
    name:"studydetail",
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
    methods:{
        openRead(){
            this.overlayRead = true
        },
        close() {
            this.overlayRead = false
        },
        enter() {
            axios.post('http://54.180.140.163:8197/itda/api/createStudyGroup',{"stid":this.study.stid},{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
            .then(
                ()=>{
                    alert('스터디 가입이 완료되었습니다.')
                    this.getStudyDetail(this.study.stid)
                    this.$emit('update')
            })
        },
        exit() {
            axios.delete('http://54.180.140.163:8197/itda/api/deleteStudyGroup/' + this.study.stid ,{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
            .then(
                ()=>{
                    alert('스터디 탈퇴가 완료되었습니다.')
                    this.getStudyDetail(this.study.stid)
                    this.$emit('update')
            })
        },
        del() {
            axios.delete('http://54.180.140.163:8197/itda/api/deleteStudy/' + this.study.stid ,{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
            .then(
                ()=>{
                    alert('스터디 삭제가 완료되었습니다.')
                    this.getStudyDetail(this.study.stid)
                    this.$emit('update')
            })
        },
        getStudyDetail(id){
            axios.get('http://54.180.140.163:8197/itda/api/getStudyGroup/'+id)
                .then(response => {
                    this.person = response.data
                })
        },
    },
    mounted(){
        this.getStudyDetail(this.study.stid)
    },
    computed: {
        ...mapState(["isLogin", "userInfo"]),
        ismember(){
            for (var i=0;i<this.person.length;i++){
                if(this.person[i].uid === this.userInfo.user.uid)
                    return true
            }
            return false
        },
    },
}
</script>

<style>
@media only screen and (max-width: 1030px){
    #response{
        display: none;
    }
}
</style>