<template>
    <div>
        <v-container>
            <v-divider></v-divider>
            <v-card>
                <v-card-title>스터디</v-card-title>
                <v-list-item >
                    <v-row>
                        <v-col class="pl-5" cols='4'>
                            <v-list-item-title>
                                스터디 종류
                            </v-list-item-title>
                        </v-col>
                        <v-col cols='8'>
                            <v-list-item-title >{{study.stname}}</v-list-item-title>
                        </v-col>
                    </v-row>
                </v-list-item >
                <v-list-item>
                    <v-row>
                        <v-col class="pl-5" cols='4'>
                            <v-list-item-title>
                                스터디 주제
                            </v-list-item-title>
                        </v-col>
                        <v-col cols='8'>
                            <v-list-item-title >{{study.typeName}}</v-list-item-title>
                        </v-col>
                    </v-row>
                </v-list-item>
                <v-list-item>
                    <v-row>
                        <v-col class="pl-5" cols='4'>
                            <v-list-item-title>
                                스터디 목적
                            </v-list-item-title>
                        </v-col>
                        <v-col cols='8'>
                            <v-list-item-title >{{study.content}}</v-list-item-title>
                        </v-col>
                    </v-row>
                </v-list-item>
                <v-list-item>
                    <v-row>
                        <v-col class="pl-5" cols='4'>
                            <v-list-item-title>
                                스터디 인원
                            </v-list-item-title>
                        </v-col>
                        <v-col cols='8'>
                            <v-list-item-title >{{person.length}}명</v-list-item-title>
                        </v-col>
                    </v-row>
                </v-list-item>

            </v-card>
            <v-divider></v-divider>
        </v-container>

        <v-container>
            <v-divider></v-divider>
            <FullCalendar
                defalutView="timeGridWeek"
                :plugins="calendarPlugins"
                :custom-buttons="customButtons"
                :header="header"
                :minTime='minTime'
                :maxTime='maxTime'
                :contentHeight="contentHeight"/>
            <v-divider></v-divider>
            <v-dialog v-model="overlayRead" scrollable="scrollable" max-width="500px">
                <v-card>
                    <v-card-title>스케줄 등록</v-card-title>
                    <v-card-text>
                        <v-form ref="form" v-model="valid">
                            <v-text-field
                                v-model="meetingDate"
                                placeholder="2020-02-02"
                                label="날짜"
                                required="required"></v-text-field>
                            <v-text-field
                                v-model="startTime"
                                placeholder="00:00"
                                label="시작 시간"
                                required="required"></v-text-field>
                            <v-text-field
                                v-model="endTime"
                                placeholder="00:00"
                                label="종료 시간"
                                required="required"></v-text-field>

                            <v-textarea v-model="EndTime" solo="solo" label="스케줄 정보를 입력해주세요"></v-textarea>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="success" text="text" :disabled="!valid" @click="validate">submit</v-btn>
                        <v-btn color="blue darken-1" text="text" @click="close">Close</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-container>
        <div>
            <StudyChat 
            :study="study"
            :person="person"
            ></StudyChat>
            <!-- 파일 업로드 -->
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import FullCalendar from '@fullcalendar/vue'
    import timeGridPlugin from '@fullcalendar/timegrid';
    import resourceTimelinePlugin from '@fullcalendar/resource-timeline'
    import StudyChat from '../components/StudyMain/StudyChat.vue'
    export default {
        name: "studydetail",
        data() {
            return {
                calendarPlugins: [
                    timeGridPlugin, resourceTimelinePlugin
                ],
                minTime: '12:00',
                maxTime: '24:00',
                contentHeight: 'auto',
                person: [],
                study: {},
                meetingDate:"",
                startTime: "",
                endTime: "",
                valid: {},
                overlayRead: false,
                picker: null,
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'StudyAdd'
                },
                customButtons: {
                    StudyAdd: {
                        text: "스터디 추가",
                        click: () => this.changeOverlay() // assuming you use Vue Router
                    }
                }
            }
        },
        components: {
            StudyChat,
            FullCalendar
        },
        methods: {
            changeOverlay() {
                this.overlayRead = !this
                    .overlayRead
                    console
                    .log(this.overlayRead)
            },
            upload() {
                alert("hello")
            },
            getStudy() {
                axios
                    .get(
                        'https://i02b201.p.ssafy.io:8197/itda/api/getStudy/' + this.$route.params.id
                    )
                    .then(response => {
                        this.study = response.data.study
                    })
            },
            getPerson() {
                axios
                    .get(
                        'https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/' + this.$route.params.id
                    )
                    .then(response => {
                        this.person = response.data
                    })
            },
            validate() {
                var select = JSON.parse(localStorage.getItem('select'))
                if (!select && this.formData.stype != 4) {
                    alert('스터디 주제를 확인해주세요')
                } else {
                    if (select) {
                        this.formData.typeFk = select.id
                        this.formData.typeName = select.Nm
                    } else {
                        this.formData.typeFk = 0
                        this.formData.typeName = '기타'
                    }
                    if (this.$refs.form.validate()) {
                        axios
                            .post(
                                'https://i02b201.p.ssafy.io:8197/itda/api/createStudy',
                                this.formData,
                                {
                                    'headers': {
                                        "jwt-auth-token": localStorage.getItem("access_token")
                                    }
                                }
                            )
                            .then(() => {
                                alert('스터디를 생성하였습니다.')
                                this
                                    .$refs
                                    .form
                                    .reset()
                                this.overlay = false
                                localStorage.removeItem('select')
                                this.getDefaultStudies()
                                this.getLoginStudies()
                            })
                            .catch()
                        }
                }
            },
            close() {
                this.formData = {
                    stname: '',
                    content: '',
                    maxPcnt: null,
                    stype: null,
                    sgroup: null,
                    typeFk: null,
                    typeName: ''
                },
                this
                    .$refs
                    .form
                    .reset()
                localStorage.removeItem('select')
                this.overlay = false
            }
        },
        mounted() {
            this.getStudy()
            this.getPerson()
        }
    }
</script>

<style lang='scss'>
    @import '~@fullcalendar/core/main.css';
    @import '~@fullcalendar/timegrid/main.css';
</style>