<template>
    <div class="recruitmain">
        <ImgBanner :imgSrc="getImgUrl('333.jpg')">
            <div
                class="font-weight-black text-center"
                style="padding-left :50px; line-height:1.2em; font-size: calc((80vw / 1240) * 40) "
                slot="text">Just Do it! you'll just win.</div>
        </ImgBanner>
        <v-container>
            <recruit-recent-list v-on:update="update"/>
            <recruit-login-content v-if="isLogin" :loginContent="wantedlist.loginContent"/>
            <recruit-default-content :defaultContent="wantedlist.defaultContent"/>
            <recruit-study-list/>
        </v-container>
    </div>
</template>
<script>
    import RecruitLoginContent from '../components/RecruitMain/RecruitLoginContent.vue'
    import RecruitDefaultContent from '../components/RecruitMain/RecruitDefaultContent.vue'
    import RecruitRecentList from '../components/RecruitMain/RecruitRecentList.vue'
    import RecruitStudyList from '../components/RecruitMain/RecruitStudyList.vue'
    import ImgBanner from '../components/RecruitMain/ImgBanner.vue'
    import { mapState } from 'vuex'
    import axios from 'axios'
    
    export default {
        name: 'recruitmain',
        components: {
            RecruitLoginContent,
            RecruitDefaultContent,
            RecruitRecentList,
            RecruitStudyList,
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
                }
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
            }
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
                handler:'getLoginContent'
            },
        }
    }
</script>