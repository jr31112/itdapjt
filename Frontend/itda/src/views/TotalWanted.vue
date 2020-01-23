<template>
    <v-layout class="CalendarSize Center">
        <v-col>

            <v-row justify="center">
                <v-col cols="6" md="2">
                    <v-select
                        :items="periodOptions"
                        class="mt-3"
                        label="기간 설정"
                        outlined="outlined"
                        dense="dense"
                        hide-detaileds="hide-detaileds"
                        append-outer-icon="append-outer-icon"></v-select>
                </v-col>

                <v-col cols="6" md="2">
                    <v-select
                        :items="recruitmentOptions"
                        class="mt-3"
                        label="고용 형태"
                        outlined="outlined"
                        dense="dense"
                        hide-detaileds="hide-detaileds"
                        append-outer-icon="append-outer-icon"></v-select>
                </v-col>
                <v-col cols="6" md="2">
                    <v-select
                        :items="techstackmentOptions"
                        class="mt-3"
                        label="기술 스택"
                        outlined="outlined"
                        dense="dense"
                        hide-detaileds="hide-detaileds"
                        append-outer-icon="append-outer-icon"></v-select>
                </v-col>
            </v-row>

            <v-sheet height="64">
                <v-toolbar class="Center" flat="flat" color="white">
                    <v-btn outlined="outlined" class="mr-4" color="grey darken-2" @click="setToday">
                        Today
                    </v-btn>
                    <v-btn fab="fab" text="text" small="small" color="grey darken-2" @click="prev">
                        <v-icon small="small">mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-toolbar-title>{{ title }}</v-toolbar-title>
                    <v-btn fab="fab" text="text" small="small" color="grey darken-2" @click="next">
                        <v-icon small="small">mdi-chevron-right</v-icon>
                    </v-btn>

                    <v-spacer></v-spacer>
                    <v-menu bottom="bottom" right="right">
                        <v-list>
                            <v-list-item @click="type = 'month'">
                                <v-list-item-title>Month</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>
                </v-toolbar>
            </v-sheet>

            <v-sheet>
                <v-calendar
                    ref="calendar"
                    v-model="focus"
                    color="primary"
                    :events="events"
                    :event-color="getEventColor"
                    :event-more-text="getText"
                    :now="today"
                    :type="type"
                    @change="updateRange"></v-calendar>
            </v-sheet>

        </v-col>
    </v-layout>
</template>

<script>
    export default {
        data: () => ({
            focus: '',
            type: 'month',

            start: null,
            end: null,

            selectedEvent: {},
            selectedElement: null,

            events: [],
            // 공고 리스트 저장소
            wanteds: [],
            colors: [
                'blue',
                'indigo',
                'deep-purple',
                'cyan',
                'green',
                'orange',
                'grey darken-1'
            ],
            names: [],
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
            techstackmentOptions: [
                {
                    text: 'Java',
                    value: 'java'
                }, {
                    text: 'Python',
                    value: 'python'
                }
            ]

        }),
        computed: {
            title() {
                const {start, end} = this
                if (!start || !end) {
                    return ''
                }
                const startMonth = this.monthFormatter(start)
                // const endMonth = this.monthFormatter(end)    const suffixMonth = startMonth
                // === endMonth ? '' : endMonth

                const startYear = start.year
                // const endYear = end.year      const suffixYear = startYear === endYear ? '' :
                // endYear    const startDay = start.day + this.nth(start.day)   const endDay =
                // end.day + this.nth(end.day)

                switch (this.type) {
                    case 'month':
                        return `${startMonth} ${startYear}`
                }
                return ''
            },
            monthFormatter() {
                return this
                    .$refs
                    .calendar
                    .getFormatter({timeZone: 'UTC', month: 'long'})
            }
        },
        mounted() {
            // 전체 공고를 가져오는 함수( 예상 함수명 : getWantedAll event 객체에 넣는다.) name : 회사명, start : 공고
            // 시작일, end : 공고 종료일, color : 기술 스택별 색상 Post를 받아오는 함수 서버에서 공고를 받아올 함수
            this.addwanteds()
        },
        methods: {
            addwanteds() {
                this.wanteds = [
                    {
                        title: "농협은행IT",
                        start: "2020-01-23",
                        end: "2020-01-23",

                        TechStack: "java"
                    }, {
                        title: "농협은행IT",
                        start: "2020-01-23",
                        end: "2020-01-23",
                        TechStack: "C"
                    }, {
                        title: "SSAFY 공고",
                        start: "2020-01-23",
                        end: "2020-01-23",
                        TechStack: "Java, Python"
                    }
                ]
                alert("mounted" + this.wanteds.length)
            },
            viewDay({date}) {
                this.focus = date
                this.type = 'day'
            },
            getEventColor(event) {
                return event.color
            },
            getText(event) {
                return event.text
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
            // 받아온 Post를 event로 처리하는 함수
            updateRange({start, end}) {
                const events = []
                // for (var idx = 0; idx < this.wanteds.length; idx++) {
                //     events.push({
                //         name: this.wanteds[idx].title,
                //         start: this.wanteds[idx].start,
                //         end: this.wanteds[idx].end,
                //         color:'blue'
                //     })
                    
                // }
                alert("updateRange에서 Wanted 길이" + this.wanteds.length)
                    events.push(
                        {name: "안녕 정원 python", start: "2020-01-23", end: "2020-01-23", color: 'blue'}
                    ),
                    events.push(
                        {name: "안녕 정원 python", start: "2020-01-25", end: "2020-01-25", color: 'blue'}
                    ),
                    events.push(
                        {name: "안녕 정원", start: "2020-01-23", end: "2020-01-23", color: 'red'}
                    ),

                    events.push(
                        {name: "민규야..힘내자", start: "2020-01-23", end: "2020-01-23", color: 'yellow'}
                    ),
                    events.push(
                        {name: "안녕 정원", start: "2020-01-23", end: "2020-01-23", color: 'green'}
                    )

                    this.start = start
                    this.end = end

                    this.events = events

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
        width: 70%;
    }
</style>