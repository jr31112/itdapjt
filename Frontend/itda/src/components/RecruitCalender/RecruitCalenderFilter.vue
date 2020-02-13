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
                    <v-btn
                        @click.stop="dialog=!dialog"
                        aria-label="Support"
                         class="ml-auto">
                        <span class="v-btn__content">
                            <span class="subtitle-1 text-capitalize font-weight-light">
                                <div>기술 스택 검사</div>
                            </span>
                        </span>
                    </v-btn>
                    <v-dialog v-model="dialog" max-width="800px" v-if="selectstacklist !== undefined">
                        <v-card>
                            <v-card-title>기술 스택 리스트</v-card-title>
                            <v-divider></v-divider>
                            <v-card-text>
                             
                                    <v-row v-for="i in 9" :key="i" >
                                        <v-col v-for="j in i==9?1:5" :key="j">
                                            <v-checkbox v-model="selectstacklist"
                                                :label="stacklist[5*(i-1) + j-1].tname"
                                                :value="stacklist[5*(i-1) + j-1].sid" ></v-checkbox>
                                        </v-col>
                                    </v-row>
                                
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
                options: {
                    period: 0,
                    recruit: 0
                },
                year: "",
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
                stacklist: [],
                selectstacklist: [],
                dialog: false
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
            getTechStack() {
                axios
                    .get(`http://192.168.31.54:8197/itda/api/getAllStacks/`)
                    .then(response => {
                        this.stacklist = response.data
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