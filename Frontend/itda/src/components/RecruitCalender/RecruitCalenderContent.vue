<template>
    <v-layout>
        <v-col>
            <v-sheet height="64">
                <v-toolbar flat="flat" color="white">
                    <v-btn outlined="outlined" class="mr-4" color="grey darken-2" @click="setToday">
                        Today
                    </v-btn>
                    <v-btn fab="fab" text="text" small="small" color="grey darken-2" @click="prev">
                        <v-icon small="small">mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-toolbar-title>{{ calendartitle }}</v-toolbar-title>
                    <v-btn fab="fab" text="text" small="small" color="grey darken-2" @click="next">
                        <v-icon small="small">mdi-chevron-right</v-icon>
                    </v-btn>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </v-sheet>

            <v-sheet>
                <v-calendar
                    ref="calendar"
                    v-model="focus"
                    color="primary"
                    :events="tmpevents"
                    :event-color="getEventColor"
                    @change="updateRange"
                    @click:event="goDetailPage"></v-calendar>
            </v-sheet>

        </v-col>
    </v-layout>
</template>
<script>
    // import router from '../../router/index.js'
    export default {
        name: 'recruitcalendercontent',
        data: () => ({
            focus: '',
            start: null,
            end: null,
            tmpevents: [],
            companylist: [],
            wantedlist: [],
            calendartitle: ""
            
        }),
        watch: {
            options: {
                deep: true,
                immediate: true,
                handler: 'updateRange'
            },
            selectstacklist: {
                deep: true,
                handler: 'updateRange'
            },
            calendartitle: {}
        },
        props: {
            wlist: {
                type: Array
            },
            options: {
                type: Object
            },
            selectstacklist: {
                type: Array
            }
        },
        computed: {
            title() {
                var {
                    start,
                    end
                } = this
                if (!start || !end) {
                    return ''
                }
                var startYear = start.year
                var startMonth = this.monthFormatter(this.start)
                // const startYear = start.year
                return startMonth + startYear
                // return `${startMonth} ${startYear}`
            },
            //오늘 날짜를 계산해 주는 함수
            monthFormatter() {
                return this
                    .$refs
                    .calendar
                    .getFormatter({timeZone: 'UTC', month: 'long'})
            }
        },
        created() {
            this.setValues()
        },
        methods: {
            goDetailPage(event) {
                console.log(event.event.name)
            },
            setValues() {
                for (var i = 0; i < this.wlist.length; i++) {
                    this.companylist[i] = this
                        .wlist[i]
                        .company
                        this
                        .wantedlist[i] = this
                        .wlist[i]
                        .wanted
                }
            },
            getEventColor(event) {
                return event.color
            },
            setToday() {
                this.focus = this.today
            },
            prev() {
                this
                    .$refs
                    .calendar
                    .prev()
            },
            next() {
                this
                    .$refs
                    .calendar
                    .next()
            },
            updateRange({start, end}) {
                const events = []
                var idxlist = []
                if (this.companylist.length) {
                    // 신입이나 인턴이 포함되어 있는 인덱스를 value로 하는 list 반환
                    idxlist = this.searchOfJtype(this.options.recruit)
                    idxlist = this.searchOfStack(idxlist)
                    for (var i = 0; i < idxlist.length; i++) {
                        if (this.options.period != 2) {
                            events.push({
                                name: this
                                    .companylist[i]
                                    .corpNm,
                                start: this
                                    .wantedlist[i]
                                    .startDate
                                    .substring(0, 10),
                                end: this
                                    .wantedlist[i]
                                    .startDate
                                    .substring(0, 10),
                                color: 'blue'
                            })

                        }

                        if (this.options.period != 1) {
                            events.push({
                                name: this
                                    .companylist[i]
                                    .corpNm,
                                start: this
                                    .wantedlist[i]
                                    .endDate
                                    .substring(0, 10),
                                end: this
                                    .wantedlist[i]
                                    .endDate
                                    .substring(0, 10),
                                color: 'red'
                            })

                        }
                    }
                    // this.$set(this.tmpevents, idx, events)
                    this.tmpevents = events
                    this.start = start
                    this.end = end
                    var startYear = start.year
                    var startMonth = this.monthFormatter(this.start)
                    this.calendartitle = startYear + "년   " + startMonth
                } else {
                    return;
                }
            },
            searchOfJtype(jtype) {
                var tidx = []
                if (jtype == 1) {
                    // 인턴
                    for (var idx = 0; idx < this.wlist.length; idx++) {
                        for (var jobsidx = 0; jobsidx < this.wlist[idx].jobs.length; jobsidx++) {
                            if (!this.wlist[idx].jobs[jobsidx].jtype) {
                                continue
                            }
                            if (this.wlist[idx].jobs[jobsidx].jtype.includes("인턴")) {
                                tidx.push(idx)
                                break
                            }
                        }

                    }
                } else if (jtype == 2) {
                    for (var num = 0; num < this.wlist.length; num++) {
                        for (var jobsnum = 0; jobsnum < this.wlist[num].jobs.length; jobsnum++) {
                            if (!this.wlist[num].jobs[jobsnum].jtype) {
                                continue
                            }
                            if (this.wlist[num].jobs[jobsnum].jtype.includes("신입") || this.wlist[num].jobs[jobsnum].jtype.includes("정규")) {
                                tidx.push(num)
                                break
                            }
                        }
                    }
                } else {
                    for (var k = 0; k < this.wlist.length; k++) {
                        tidx.push(k)
                    }
                }

                return tidx
            },
            searchOfStack(idxlist) {
                var tidxlist = []
                if(idxlist.length == 0 && this.selectstacklist.length ==0){
                    return idxlist
                }
                for (var i = 0; i < idxlist.length; i++) {
                    outplace : for (var j = 0; j < this.wlist[idxlist[i]].jobs.length; j++) {
                        for (var k = 0; k < this.wlist[idxlist[i]].jobs[j].stack.length; k++) {
                            for (var z = 0; z < this.selectstacklist.length; z++) {
                                if (this.wlist[idxlist[i]].jobs[j].stacks[k].sid == this.selectstacklist[z].sid) {
                                    tidxlist.push(idxlist[i])
                                    break outplace
                                }
                            }
                        }
                    }
                }
                return tidxlist
            },
            isEmpty(str) {
                if (typeof str == "undefined" || str == null || str == "" || str.length == 0) 
                    return true;
                else 
                    return false;
                }
            }
    }
</script>
<style>
    .Center {
        margin: auto !important;
    }
    .CalendarSize {
        height: 80%;
        width: 100%;
    }
</style>