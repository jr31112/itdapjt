<template>
    <v-layout>
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
                        v-model="recruit"
                        :items="recruitmentOptions"
                        attach="attach"
                        chips="chips"
                        label="채용 전형"
                        multiple="multiple"
                        @change="optionrecruit"></v-select>
                </v-col>
                <v-col cols="12" sm="4">
                    <v-btn class="ml-auto" @click.stop="overlay=!overlay">서비스 준비 중입니다.</v-btn>

                </v-col>
            </v-row>
            <recruit-calender-content
                v-if="recentlist.length != 0"
                :wlist="recentlist"
                :options="options"/>
        </v-col>
    </v-layout>
</template>

<script>
    import RecruitCalenderContent from "./RecruitCalenderContent.vue"
    import axios from 'axios'
    export default {
        name: "recruitcalenderfilter",
        components: {
            RecruitCalenderContent
        },
        data() {
            return {
                checkedNames: [],
                overlay: false,
                options: {
                    period: 0,
                    recruit: 0
                },
                period: [],
                recentlist: [],
                techstack: [],
                recruit: [],
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
                    }
                ],
                stacklist: [
                    {
                        sid: 0,
                        tname: ""
                    }
                ],
                selectstacklist: []
            }
        },
        methods: {
            getRecentRecruit() {
                axios
                    .get(`http://54.180.140.163/itda/api/getWantedAll/`)
                    .then(response => {
                        this.recentlist = response.data
                    })
                    .catch(error => {
                        console.log(error)
                    })
                },
            getTechStack() {
                axios
                    .get(`http://54.180.140.163/itda/api/getAllStacks/`)
                    .then(response => {
                        console.log(response)
                        for (var i = 0; i < response.data.length; i++) {
                            var sid = response
                                .data[i]
                                .sid
                            var tname = response
                                .data[i]
                                .tname
                                this
                                .stacklist
                                .splice(i, 0, {sid, tname})
                        }
                    })
                    .catch(error => {
                        console.log(error)
                    })
                },
            optionmanager() {
                if (this.period.length == 1) {
                    if (this.period[0] == "시작") {
                        this.options.period = 1
                    } else {
                        this.options.period = 2
                    }
                } else {
                    this.options.period = 0
                }
            },
            optionrecruit() {
                if (this.recruit.length == 1) {
                    if (this.recruit[0] == "인턴") {
                        this.options.recruit = 1
                    } else {
                        this.options.recruit = 2
                    }
                } else {
                    this.options.recruit = 0
                }
            }

        },
        mounted() {
            this.getRecentRecruit()
            console.log("공고 받기 완료")
            this.getTechStack()
            console.log("기술 스택 리스트 받기 완료")
            console.log(this.stacklist)
        }
    }
</script>

<style></style>