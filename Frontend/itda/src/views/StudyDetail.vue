<template>
    <div>
        <v-container>
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
        <studySchedule :study="study"/>
        <div>
            <StudyChat 
            :study="study"
            :person="person"
            ></StudyChat>
            <!-- 파일 업로드 -->
        </div>
        <v-container>

            <h2>파일 업로드</h2>
            <v-file-input
                multiple="multiple"
                label="File input"
                ref="file"
                @change="upload()"/>
            <!-- <input type="file" ref="uimg" accept="image/png, image/jpeg, image/bmp"
            placeholder="Input Image" prepend-icon="mdi-camera" label="My Image"
            @change="onChange()" /> -->
            <h2 v-if="this.overlayRead">Hello</h2>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios'
    
    import StudyChat from '../components/StudyMain/StudyChat.vue'
    import StudySchedule from '../components/StudyDetail/StudySchedule.vue'
    export default {
        name: "studydetail",
        components: {
            StudyChat,
            StudySchedule,
        },
        data(){
            return{
                study:{},
                person:[],
                overlayRead:false
            }
        },
        methods: {
            changeOverlay() {
                this.overlayRead = !this.overlayRead
            },
            upload() {
                alert("hello")
            },
            getStudy() {
                axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudy/' + this.$route.params.id)
                    .then(response => {this.study = response.data.study})
            },
            getPerson() {
                axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/' + this.$route.params.id)
                    .then(response => {this.person = response.data})
            },
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