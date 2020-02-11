<template>
    <div style="padding: 80px 0px 0px 0px">
        <v-container>
            <h2>내 정보
                <v-btn class="ma-2" outlined="outlined" large="large" fab="fab" color="indigo">
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
                    <user-recruit/>
                </v-col>
            </v-row>
            <h2>스터디 정보</h2>
            <v-row class="mb-4">
                <v-col>
                    <user-study/>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import UserDetail from "../components/Mypage/UserDetail.vue";
    import UserStudy from "../components/Mypage/UserStudy.vue";
    import UserRecruit from "../components/Mypage/UserRecruit.vue";
    import router from "../router/index.js"
    import axios from "axios";
    export default {
        name: "user",

        components: {
            UserDetail,
            UserRecruit,
            UserStudy,
            
        },
        data() {
            return {
                userInfo: {
                    user: {
                        uid: 0,
                        email: "",
                        uimg: "",
                        uname: "",
                        major: "",
                        cid: 0
                    },
                    mystacks: [{}]
                }
            }
        },
        mounted() {
            this.getAllData();
        },
        methods: {
            getAllData() {
                axios
                    .get(`http://54.180.140.163/itda/api/getUser/`, {
                        headers: {
                            "jwt-auth-token": localStorage.getItem("access_token")
                        }
                    })
                    .then(response => {
                        if (response.data.state == 'success') {
                            this.userInfo.user.uid = response.data.user.uid
                            this.userInfo.user.email = response.data.user.email
                            this.userInfo.user.uimg = response.data.user.uimg
                            this.userInfo.user.uname = response.data.user.uname
                            this.userInfo.user.major = response.data.user.major
                            this.userInfo.user.cid = response.data.user.cid
                            for (var i = 0; i < response.data.mystacks.length; i++) {
                                var sid = response
                                    .data
                                    .mystacks[i]
                                    .sid
                                var tname = response
                                    .data
                                    .mystacks[i]
                                    .tname
                                    this
                                    .userInfo
                                    .mystacks
                                    .push({sid, tname})
                            }
                        } else {
                            //실패했으니 다시 로그인해라고 알려주든지?
                        }
                    })
                    .catch(() => {
                        //에러가 났다. 다시 로그인해라.
                    })

                },
            goUserModifyPage() {
                router.push({name: 'usermodify', params:{userInfo:this.userInfo}})
            }

        }
    }
</script>

<style></style>