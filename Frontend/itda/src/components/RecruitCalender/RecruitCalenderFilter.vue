<template>
    <v-container>
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
                <v-col align-self="center" cols="12" sm="4">
                    
                        <v-btn @click.stop="dialog=!dialog" class="ml-auto my-auto" width="100%">
                            기술 스택
                        </v-btn>
                    
                    <v-dialog v-model="dialog" max-width="800px">
                        <v-card>
                            <v-card-title>원하는 기술스택을 골라주세요!</v-card-title>
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
                                <v-spacer></v-spacer>
                                <v-btn color="green darken-1" text="text" @click="dialog = false">Save</v-btn>
                                <v-btn color="blue darken-1" text="text" @click="dialog = false">Close</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-col>
            </v-row>
            <recruit-calender-content
                v-if="recruitList.length"
                :recruitList="recruitList"
                :options="options"
               />
        </v-col>
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'
    import RecruitCalenderContent from "./RecruitCalenderContent.vue"
    export default {
        name: "recruitcalenderfilter",
        components: {
            RecruitCalenderContent
        },
        data() {
            return {
                overlay: false,
                checkedNames: [],
                options: {
                    period: 3,
                    recruit: 3,
                    selectstacklist: [],
                },
                year: "",
                period: [],
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
                dialog: false
            }
        },
        methods: {
            
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
        computed:{
            ...mapState(["stacklist", "recruitList"]),
        }
    }
</script>

<style></style>