<template>
  <v-container>
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
                    <v-text-field v-model="formData.title" label="스케줄 정보" required="required" :rules="[v => !!v || '스터디 정보를 입력해주세요']"></v-text-field>
                    <v-text-field v-model="meetingDate" placeholder="2020-02-02" label="날짜" required="required" :rules="[v => !!v || '스터디 날짜을 입력해주세요']"></v-text-field>
                    <v-text-field v-model="startTime" placeholder="00:00" label="시작 시간" required="required" :rules="[v => !!v || '시작 시간을 입력해주세요']"></v-text-field>
                    <v-text-field v-model="endTime" placeholder="00:00" label="종료 시간" required="required" :rules="[v => !!v || '종료 시간을 입력해주세요']"></v-text-field>

                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="success" text="text" :disabled="!valid" @click="submit">submit</v-btn>
                <v-btn color="blue darken-1" text="text" @click="close">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from 'axios'
import FullCalendar from '@fullcalendar/vue'
import timeGridPlugin from '@fullcalendar/timegrid'
import resourceTimelinePlugin from '@fullcalendar/resource-timeline'
export default {
    name:"studyschedule",
    components:{
        FullCalendar,
    },
    props:{
        study:{type:Object}
    },
    data() {
        return {
            calendarPlugins: [
                timeGridPlugin, resourceTimelinePlugin
            ],
            minTime: '12:00',
            maxTime: '24:00',
            contentHeight: 'auto',
            meetingDate:"",
            startTime:'',
            endTime:'',
            picker: null,
            overlayRead: false,
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'StudyAdd'
            },
            valid: false,
            formData:{
                stid:this.study.stid,
                title:'',
                startTime:'',
                endTime:'',
            },
            customButtons: {
                StudyAdd: {
                    text: "스터디 추가",
                    click: () => this.open() // assuming you use Vue Router
                }
            }
        }
    },
    methods:{
        submit() {
            this.formData.startTime = this.meetingDate + " " + this.startTime + ":00"
            this.formData.endTime = this.meetingDate + " " + this.endTime + ":00"
            if (this.$refs.form.validate()) {
                console.log(this.formData)
                axios.post('https://i02b201.p.ssafy.io:8197/itda/api/createMeeting', this.formData, {'headers': {"jwt-auth-token": localStorage.getItem("access_token")}})
                    .then(() => {
                        alert('일정을 생성하였습니다.')
                        this.$refs.form.reset()
                        this.overlay = false
                        this.formData.title = null
                        this.formData.startTime = ''
                        this.formData.endTime = ''
                    })
                    .catch(err=>{console.log(err)})
                }
            },
            open() {
                this.overlayRead = true
            },
            close() {
                this.overlayRead = false
            }
        },
}
</script>

<style>

</style>