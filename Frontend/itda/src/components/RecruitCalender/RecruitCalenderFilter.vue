<template>
    <v-layout style="padding: 80px 0px 0px 0px">
        <v-col>
        <v-row justify="center">
            <v-col cols="12" sm="4">
                <v-select
                    v-model="period"
                    :items="periodOptions"
                    attach="attach"
                    chips="chips"
                    label="기간"
                    multiple="multiple"
                    @change="optionmanager"></v-select>
            </v-col>
            <v-col cols="12" sm="4">
                <v-select
                    v-model="recurit"
                    :items="recruitmentOptions"
                    attach="attach"
                    chips="chips"
                    label="채용 전형"
                    multiple="multiple"></v-select>
            </v-col>
            <v-col cols="12" sm="4">
                <v-select
                    v-model="techstack"
                    :items="techstackmentOptions"
                    attach="attach"
                    chips="chips"
                    label="기술 스택"
                    multiple="multiple"></v-select>
            </v-col>
        </v-row>
        <recruit-calender-content v-if="recentlist.length != 0" :wlist="recentlist" :cperiod="period" :options="options"/>
        </v-col>
    </v-layout>
</template>

<script>
    import RecruitCalenderContent from "./RecruitCalenderContent.vue"
    import axios from 'axios'
    export default {
        name:"recruitcalenderfilter",
        components: {
            RecruitCalenderContent
        },
        data() {
            return {
                options: {
                    period:0
                },
                period: [],
                recentlist: [],
                techstack: [],
                recruit:[],
                periodOptions: [
                    {
                        text: '시작',
                        value: '시작'
                    }, {
                        text: '종료',
                        value: '종료'
                    }
                ],
                recruitmentOptions: [
                    {
                        text: '인턴',
                        value: '인턴'
                    }, {
                        text: '신입',
                        value: '신입'
                    }, {
                        text: '정규직',
                        value: '정규직'
                    }
                ],
                techstackmentOptions: [
                    {
                        text: 'Java',
                        value: 'java'
                    }, {
                        text: 'Python',
                        value: 'python'
                    }, {
                        text: 'Spring',
                        value: 'Spring'
                    }
                ]
            }
        },
        methods: {
            getRecentRecruit() {
                axios
                    .get(`http://192.168.31.54:8197/itda/api/getWantedAll/`)
                    .then(response => {
                        this.recentlist = response.data
                    })
                    .catch(error => {
                        console.log(error)
                    })
                },
            optionmanager(){
                if (this.period.length == 1){
                    if (this.period[0] == "시작"){
                        this.options.period = 1
                    }
                    else{
                        this.options.period = 2
                    }
                }
                else{
                    this.options.period = 0
                }
                
            }
        },
        mounted() {
            this.getRecentRecruit()
        },
    }
</script>

<style></style>