<template>
    <div class="recruitmain">
        <ImgBanner :imgSrc="getImgUrl('star.png')">
         <div
            class="font-weight-black text-center"
            style="padding-left :50px; line-height:1.2em; font-size: calc((80vw / 1240) * 40) "
            slot="text">IT 취업의 길을 IT:DA<br/> 당신의 탈출을 응원합니다! </div>              
        </ImgBanner>
        <v-container>
            <recruit-recent-list v-on:update="update"/>
            <recruit-login-content v-if="isLogin" :loginContent="wantedlist.loginContent"/>
            <recruit-default-content :defaultContent="wantedlist.defaultContent"/>
        </v-container>
        <v-container v-if="isLogin">
            <v-row>
              <v-col>
                <h2>내가 가입한 스터디 보기</h2>
              </v-col>
          </v-row>
            <study-login-content :myStudyList="loginStudies" v-if="loginStudies.length" v-on:update="update"/>
            <v-row v-else>
                <v-col>가입한 스터디가 없어요..</v-col>
            </v-row>
        </v-container>
    </div>
</template>
<script>
    import RecruitLoginContent from '../components/RecruitMain/RecruitLoginContent.vue'
    import RecruitDefaultContent from '../components/RecruitMain/RecruitDefaultContent.vue'
    import RecruitRecentList from '../components/RecruitMain/RecruitRecentList.vue'
    import StudyLoginContent from '../components/StudyMain/StudyLoginContent.vue'
    import ImgBanner from '../components/RecruitMain/ImgBanner.vue'
    import { mapState } from 'vuex'
    import axios from 'axios'
    
    export default {
        name: 'recruitmain',
        components: {
            RecruitLoginContent,
            RecruitDefaultContent,
            RecruitRecentList,
            StudyLoginContent,
            ImgBanner,
        },
        data(){
            return{
                wantedlist:{
                    loginContent:{
                        scrapList:[],
                        stackList:[]
                    },
                    defaultContent:{
                        deadlineList:[],
                        popularList:[]
                    }
                },
                loginStudies:[]
            }
        },
        methods: {
            update(){
                this.getLoginContent()
            },
            getImgUrl(img) {
                return require('../assets/' + img)
            },
            getLoginContent(){
                if (this.isLogin){
                    axios.get(`http://192.168.31.54:8197/itda/api/getWantedByScrap/`, {'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
                        .then(response => {
                            this.wantedlist.loginContent.scrapList = response.data;
                        })
                        .catch(() => {});
                    axios.get(`http://192.168.31.54:8197/itda/api/getWantedByStack/`, {'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
                        .then(response => {
                            this.wantedlist.loginContent.stackList = response.data;
                        })
                        .catch(() => {});
                }
            },
            getDefaultContent(){
                axios.get(`http://192.168.31.54:8197/itda/api/getWantedByRecent/`)
                    .then(response=>{
                        this.wantedlist.defaultContent.deadlineList = response.data
                    })
                    .catch(()=>{})
                axios.get(`http://192.168.31.54:8197/itda/api/getWantedByView/`)
                    .then(response=>{
                        this.wantedlist.defaultContent.popularList = response.data
                    })
                    .catch(()=>{})
            },
            getLoginStudies(){
                if (this.isLogin){
                    axios.get("http://192.168.31.54:8197/itda/api/getUser", {headers:{"jwt-auth-token": localStorage.getItem("access_token")}})
                        .then(response => {
                            this.loginStudies = response.data.myStudies
                            var len = this.loginStudies.length % 4
                            if (len){
                                for (var i=0;i<4-len;i++)
                                    this.loginStudies.push({})
                            }
                    })
                }
            },  
        },
        computed: {
            ...mapState(["isLogin"])
        },
        mounted(){
            if (this.isLogin){
                this.getLoginContent()
            }
            this.getDefaultContent()
        },
        watch:{
            isLogin:{
                deep:true,
                immediate:true,
                handler(){
                    this.getLoginContent()
                    this.getLoginStudies()
                }
            },
        }
    }
</script>
