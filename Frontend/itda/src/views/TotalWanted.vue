<template>
    <v-layout class="CalendarSize Center">
        <v-col>
            <v-sheet height = "40">
                <h2>Hello</h2>
            </v-sheet>
            <v-sheet height="64">   
                <v-toolbar class="Center" flat="flat" color="white" >
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
                    :event-more="false"
                    :event-color="getEventColor"
                    :now="today"
                    :type="type"                 
                    @change="updateRange"></v-calendar>
                <v-menu
                    v-model="selectedOpen"
                    :close-on-content-click="false"
                    :activator="selectedElement"
                    offset-x="offset-x">
                    <v-card color="grey lighten-4" min-width="350px" flat="flat">
                      
                        
                    </v-card>
                </v-menu>
            </v-sheet>
            
        </v-col>
    </v-layout>
</template>

<script>
    export default {
        data: () => ({
            focus: '',
            type: 'month',
            typeToLabel: {
                month: 'Month',
                week: 'Week',
                day: 'Day',
                '4day': '4 Days'
            },
            start: null,
            end: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            events: [],
            colors: [
                'blue',
                'indigo',
                'deep-purple',
                'cyan',
                'green',
                'orange',
                'grey darken-1'
            ],
            names: [
                'Meeting',
                'Holiday',
                'PTO',
                'Travel',
                'Event',
                'Birthday',
                'Conference',
                'Party'
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
            this
                .$refs
                .calendar
                .checkChange()
        },
        methods: {
            viewDay({date}) {
                this.focus = date
                this.type = 'day'
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

                events.push({
                    name: "안녕 정원",
                    start: "2020-01-23",
                    end:"2020-01-25",
                    color:'blue'
                }),
                   events.push({
                    name: "안녕 정원",
                    start: "2020-01-23",
                    end:"2020-01-23",
                    color:'red'
                }),
                  events.push({
                    name: "안녕 정원",
                    start: "2020-01-23",
                    end:"2020-01-23",
                    color:'yellow'
                }),
                  events.push({
                    name: "안녕 정원",
                    start: "2020-01-23",
                    end:"2020-01-23",
                    color:'green'
                })

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