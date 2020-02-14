<template>
    <div style="padding: 80px 0px 0px 0px">
        <v-container>
            <h2>내 정보
                <v-btn class="ma-2" outlined="outlined" large="large" fab="fab" color="indigo" width="40px" height="40px">
                    <v-icon @click="goUserModifyPage">mdi-pencil</v-icon>
                </v-btn>
            </h2>
            <v-row class="mb-4">
                <v-col>
                    <user-detail :userInfo="userInfo"/>
                </v-col>
            </v-row>
            <h2>스크랩한 공고</h2>
            <v-row class="mb-4">
                <v-col>
                    <!-- <user-recruit :UserEndedScrapWanteds="UserEndedScrapWanteds" :UserScrapWanteds="UserScrapWanteds"/> -->
                </v-col>
            </v-row>
        </v-container>
        <v-container class="my-0" white>
          <v-row>
              <v-col>
                <h2>내 스터디 정보</h2>
              </v-col>
          </v-row>
          <study-login-content :myStudyList="loginStudies" v-if="loginStudies.length"/>
          <v-row v-else>
              <v-col>가입한 스터디가 없어요..</v-col>
          </v-row>
      </v-container>
    </div>
</template>

<script>
    import UserDetail from "../components/Mypage/UserDetail.vue"
    import StudyLoginContent from '../components/StudyMain/StudyLoginContent.vue'
    // import UserRecruit from "../components/Mypage/UserRecruit.vue"
    import router from "../router/index.js"
    import axios from "axios"
    // import { mapState } from 'vuex'
    export default {
        name: "user",
        components: {
            UserDetail,
            // UserRecruit,
            StudyLoginContent
        },
        data() {
            return {
                loginStudies:[],
            }
        },
        mounted() {
            this.getAllData();
        },
        methods: {
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
            goUserModifyPage() {
                router.push({name: 'usermodify'})
            },
        },
    }
</script>

<style></style>