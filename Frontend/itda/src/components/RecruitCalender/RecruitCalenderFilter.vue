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
                    <!-- <v-btn class="ml-auto" @click.stop="overlay=!overlay">서비스 준비 중입니다.</v-btn> -->
                    <v-btn class="ma-2" @click.stop="overlay=!overlay" outlined color="gray">기술 스택 선택</v-btn>
                    
                    <v-dialog v-model="overlay" scrollable="scrollable" max-width="500px">
                        <v-card>
                            
                            <v-card-title>기술 스택</v-card-title>
                            <v-divider></v-divider>
                            <v-card-text style="height: 300px;">
                                <v-checkbox-group >
                                    <v-checkbox v-for="stack in stacklist" :key="stack.no" v-model="selectstacklist" :label="stack.tname" :value="stack.sid"></v-checkbox>
                                </v-checkbox-group>

                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-btn color="blue darken-1" text="text" @click="dialog = false">Close</v-btn>
                                <v-btn color="blue darken-1" text="text" @click="dialog = false">Save</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-col>
            </v-row>
            <recruit-calender-content
                v-if="recentlist.length != 0"
                :wlist="recentlist"
                :options="options"
                :selectstacklist="selectstacklist"/>
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
                year:"",        
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
                    .get(`https://192.168.31.54:8197/itda/api/getWantedAll/`)
                    .then(response => {
                        this.recentlist = response.data
                    })
                    .catch(error => {
                        console.log(error)
                    })
                },
            getTechStack() {
                axios
                    .get(`https://192.168.31.54:8197/itda/api/getAllStacks/`)
                    .then(response => {
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
            this.getTechStack()
        }
    }
</script>

<style></style>