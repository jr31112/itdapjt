<template>
    <FullCalendar
        defaultView="dayGridMonth"
        :plugins="calendarPlugins"
        :weekends="true"
        :eventLimit="true"
        :events="calendarEvent"
        :eventLimits=6
        @change="changeEvent"
        @eventClick="handleDateClick"/>
</template>

<script>
    import FullCalendar from '@fullcalendar/vue'
    import dayGridPlugin from '@fullcalendar/daygrid'
    import interactionPlugin from '@fullcalendar/interaction'
    import router from '../../router/index.js'
    export default {
        components: {
            FullCalendar, // make the <FullCalendar> tag available
        },

        data() {
            return {
                calendarPlugins: [
                    dayGridPlugin, interactionPlugin
                ],
                calendarEvent: [],

                baseLen: 0
            }
        },
        props: {
            recruitList: {
                type: Array
            },
            options: {
                type: Object
            }
        },
        watch: {
            options: {
                deep: true,
                immediate: true,
                handler: 'changeEvent'
            }
        },

        methods: {
            handleDateClick(arg) {
            
           router.push({ name: "recruitdetail", params: { id:arg.event._def.extendedProps.wid} });
            },
            changeEvent() {
                this.calendarEvent = []
                var period = this.options.period
                var idx = []
                for (var k = 0; k < this.recruitList.length; k++) {
                    idx.push(k)
                }
                // 공고 내 인턴 혹은 신입이 있는 공고 번호를 찾는 조건문
                if (this.options.recruit !== 3) {
                    idx = this.filterByRecruitOption(idx, this.options.recruit)
                }

                // 공고 내 선택한 스택이 있는 공고 번호를 찾는 조건문
                if (this.options.selectstacklist.length !== 0) {
                    idx = this.filterByStackOption(idx, this.options.selectstacklist)
                }
                var idxLen = idx.length
                for (var i = 0; i < idxLen; i++) {
                    var tidx = idx[i]
                    var wid = this
                        .recruitList[tidx]
                        .wanted
                        .wid
                    var title = this
                        .recruitList[tidx]
                        .company
                        .corpNm
                    var start = this
                        .recruitList[tidx]
                        .wanted
                        .startDate
                        .substring(0, 10)
                    var end = this
                        .recruitList[tidx]
                        .wanted
                        .endDate
                        .substring(0, 10)
                    var wstack = this
                        .recruitList[tidx]
                        .stacks
                    var jobType = this
                        .recruitList[tidx]
                        .wanted
                        .jobType
                    // 1 시작, 2 종료 3 모두
                    if (period !== 2) {
                        this
                            .calendarEvent
                            .push({
                                wid,
                                title,
                                color: "blue",
                                textColor: "white",
                                start: start,
                                wstack,
                                jobType
                            })
                    }
                    // 1 시작, 2 종료 0 모두
                    if (period !== 1) {
                        this
                            .calendarEvent
                            .push({
                                wid,
                                title,
                                color: "red",
                                textColor: "white",
                                start: end,
                                wstack,
                                jobType
                            })
                    }
                }
            },

            filterByRecruitOption(checkIdxList, jobNum) {
                var idx = []
                for (var i = 0; i < checkIdxList.length; i++) {
                    if (this.recruitList[i].wanted.jobType === jobNum) {
                        idx.push(i)
                    }
                }
                return idx
            },
            filterByStackOption(checkIdxList, choiceStackList) {
                var idx = []
                for (var i = 0; i < checkIdxList.length; i++) {
                    if (this.recruitList[checkIdxList[i]].stacks.length === 0) {
                        continue
                    } else {
                        out : for (var j = 0; j < this.recruitList[checkIdxList[i]].stacks.length; j++) {
                            for (var z = 0; z < choiceStackList.length; z++) {
                                if (this.recruitList[checkIdxList[i]].stacks[j].sid === choiceStackList[z]) {
                                    idx.push(checkIdxList[i])

                                    break out;
                                }
                            }
                        }
                    }
                }
                return idx
            }
        }
    }
</script>
<style lang='scss'>

    @import '~@fullcalendar/core/main.css';
    @import '~@fullcalendar/daygrid/main.css';
</style>