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
                    <v-btn @click.stop="dialog=!dialog" aria-label="Support" class="ml-auto" width="100%" height="75%">
                        <span class="v-btn__content">
                            <span class="subtitle-1 text-capitalize font-weight-light">
                                <div>기술 스택 검사</div>
                            </span>
                        </span>
                    </v-btn>
                    <v-dialog
                        v-model="dialog"
                        max-width="800px"
                        v-if="options.selectstacklist !== undefined">
                        <v-card>
                            <v-card-title>기술 스택 리스트</v-card-title>
                            <v-divider></v-divider>
                            <v-card-text>
                                <v-row v-for="i in 9" :key="i">
                                    <v-col v-for="j in i==9?1:5" :key="j">
                                        <v-checkbox
                                            v-model="options.selectstacklist"
                                            :label="stacklist[5*(i-1) + j-1].tname"
                                            :value="stacklist[5*(i-1) + j-1].sid"></v-checkbox>
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
                v-if="recruitList.length != 0"
                :recruitList="recruitList"
                :options="options"
               />
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
                    period: 3,
                    recruit: 3,
                    selectstacklist: [],
                },
                year: "",
                period: [],
                recruitList: [],
                techstack: [],
                recruit: [],

                periodOptions: [
                    {
                        text: '시작',
                        value: '시작',
                    }, {
                        text: '종료',
                        value: '종료',
                    }
                ],
                recruitmentOptions: [
                    {
                        text: '인턴',
                        value: '인턴',
                        
                    }, {
                        text: '신입',
                        value: '신입',
                    }
                ],
                stacklist: [
                    {
                        sid: 1,
                        tname: "Java",
                        cnt: 4
                    }, {
                        sid: 2,
                        tname: "C",
                        cnt: 20
                    }, {
                        sid: 3,
                        tname: "C++",
                        cnt: 3
                    }, {
                        sid: 4,
                        tname: "C#",
                        cnt: 3
                    }, {
                        sid: 5,
                        tname: "Objective-C",
                        cnt: 0
                    }, {
                        sid: 6,
                        tname: "Python",
                        cnt: 2
                    }, {
                        sid: 7,
                        tname: "PHP",
                        cnt: 0
                    }, {
                        sid: 8,
                        tname: "JavaScript",
                        cnt: 1
                    }, {
                        sid: 9,
                        tname: "Kotlin",
                        cnt: 1
                    }, {
                        sid: 10,
                        tname: "Unix",
                        cnt: 0
                    }, {
                        sid: 11,
                        tname: "Linux",
                        cnt: 5
                    }, {
                        sid: 12,
                        tname: "Windows",
                        cnt: 1
                    }, {
                        sid: 13,
                        tname: "Android",
                        cnt: 1
                    }, {
                        sid: 14,
                        tname: "iOS",
                        cnt: 1
                    }, {
                        sid: 15,
                        tname: "MySQL",
                        cnt: 0
                    }, {
                        sid: 16,
                        tname: "MS-SQL",
                        cnt: 2
                    }, {
                        sid: 17,
                        tname: "Oracle",
                        cnt: 2
                    }, {
                        sid: 18,
                        tname: "MariaDB",
                        cnt: 0
                    }, {
                        sid: 19,
                        tname: "MongoDB",
                        cnt: 0
                    }, {
                        sid: 20,
                        tname: "SQLite",
                        cnt: 0
                    }, {
                        sid: 21,
                        tname: "NoSQL",
                        cnt: 0
                    }, {
                        sid: 22,
                        tname: "HTML",
                        cnt: 3
                    }, {
                        sid: 23,
                        tname: "CSS",
                        cnt: 2
                    }, {
                        sid: 24,
                        tname: "Vue.js",
                        cnt: 0
                    }, {
                        sid: 25,
                        tname: "jQuery",
                        cnt: 0
                    }, {
                        sid: 26,
                        tname: "Ajax",
                        cnt: 0
                    }, {
                        sid: 27,
                        tname: "Spring",
                        cnt: 3
                    }, {
                        sid: 28,
                        tname: "Django",
                        cnt: 0
                    }, {
                        sid: 29,
                        tname: "Flask",
                        cnt: 0
                    }, {
                        sid: 30,
                        tname: "Node.js",
                        cnt: 0
                    }, {
                        sid: 31,
                        tname: "JSP",
                        cnt: 0
                    }, {
                        sid: 32,
                        tname: "JPA/Hibernate",
                        cnt: 0
                    }, {
                        sid: 33,
                        tname: "WAS",
                        cnt: 1
                    }, {
                        sid: 34,
                        tname: "AWS",
                        cnt: 2
                    }, {
                        sid: 35,
                        tname: "OOP",
                        cnt: 0
                    }, {
                        sid: 36,
                        tname: "AI",
                        cnt: 5
                    }, {
                        sid: 37,
                        tname: "Machine Learning",
                        cnt: 0
                    }, {
                        sid: 38,
                        tname: "Docker",
                        cnt: 2
                    }, {
                        sid: 39,
                        tname: "RESTful-API",
                        cnt: 0
                    }, {
                        sid: 40,
                        tname: "JIRA",
                        cnt: 0
                    }, {
                        sid: 41,
                        tname: "Git",
                        cnt: 3
                    }
                ],
               
                dialog: false
            }
        },
        methods: {
            getRecentRecruit() {
                axios
                    .get(`https://i02b201.p.ssafy.io:8197/itda/api/getWantedAll/`)
                    .then(response => {
                        this.recruitList = response.data
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
                    this.options.period = 3
                }
            },
            // 1 신입만, 2 인턴, 3 신입인턴
            optionrecruit() {
                
                if (this.recruit.length == 1) {
                    if (this.recruit[0] == "신입") {
                        this.options.recruit = 1
                    } else {
                        this.options.recruit = 2
                    }
                } else {
                    this.options.recruit = 3
                }
            },
        },

        mounted() {
            this.getRecentRecruit()
        }
    }
</script>

<style></style>