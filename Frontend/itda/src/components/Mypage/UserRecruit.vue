<template>
    <v-container>
        <v-tabs v-model="tab" background-color="primary" :right="true">
            <v-tab v-for="recruit in recruits" :key="recruit.tab">{{recruit.title}}</v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
            <v-tab-item v-for="recruit in recruits" :key="recruit.tab">
                <v-container v-if="recruit.wanteds">
                    <user-recruit-detail :recruits="recruit.wanteds"/>
                </v-container>
                <v-container v-else>
                    스크랩된 {{recruit.title}}이 없어요
                </v-container>
            </v-tab-item>
        </v-tabs-items>
    </v-container>
</template>

<script>
    import UserRecruitDetail from "./UserRecruit/UserRecruitDetail.vue"
    export default {
        name: "userrecruit",
        components: {
            UserRecruitDetail,
        },
        props:{
            userInfo:{type:Object}
        },
        data(){
            return{
                tab:0,
                recruits:[
                    {tab:0, title:"진행중인 공고", wanteds:[]},
                    {tab:1, title:"마감된 공고", wanteds:[]}
                ],
            }
        },
        watch:{
            userInfo:{
                deep:true,
                immediate:true,
                handler:"dataTransfer"
            }
        },
        methods:{
            dataTransfer(){
                this.recruits[0].wanteds = this.userInfo.myScrapWanteds
                this.recruits[1].wanteds = this.userInfo.myEndedScrapWanteds
            }
        }
    }
</script>

<style></style>