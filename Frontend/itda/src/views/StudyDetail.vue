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
        </v-container>
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

        </v-container>
    </div>
</template>

<script>
    import axios from 'axios'
    import FullCalendar from '@fullcalendar/vue'
    import timeGridPlugin from '@fullcalendar/timegrid';
    import resourceTimelinePlugin from '@fullcalendar/resource-timeline'

    export default {
        name: "stduydetail",
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
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'StudyAdd'
                },
                customButtons: {
                    StudyAdd: {
                        text: "스터디 추가",
                        click: () => this
                            .$router
                            .push({name: "yourLeader"}) // assuming you use Vue Router
                    }
                }
            }
        },
        components: {

            FullCalendar
        },
        methods: {
            upload() {
                alert("hello")
            },
            getStudy() {
                axios
                    .get(
                        'https://i02b201.p.ssafy.io:8197/itda/api/getStudy/' + this.$route.params.id
                    )
                    .then(response => {
                        this.study = response
                            .data
                            .study
                            console
                            .log(this.study)
                    })
            },
            getPerson() {
                axios
                    .get(
                        'https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/' + this.$route.params.id
                    )
                    .then(response => {
                        this.person = response
                            .data
                            console
                            .log(this.person)
                    })
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