<template>
    <v-layout class="CalendarSize Center">
        <v-col>
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
                    :events="tmpevents"
                    :event-color="getEventColor"
                    @change="updateRange"
                    @click:event="event1"
                    ></v-calendar>
            </v-sheet>
        </v-col>
    </v-layout>
</template>
<script>
    export default {
        name:'recruitcalendercontent',
        data: () => ({
            focus: '',
            type: 'month',
            start: null,
            end: null,
            tmpevents: [],
            companylist: [],
            wantedlist: [],
            names: [],
            propsCperiod: []
        }),
        watch:{
            options:{
                deep:true,
                immediate:true,
                handler:'updateRange'
            }
        },
        props: {
            wlist: {
                type: Array
            },
            options: {
                type: Object
            }
        },
        computed: {
            title() {
                const {start, end} = this
                if (!start || !end) {
                    return ''
                }
                const startMonth = this.monthFormatter(start)
                const startYear = start.year
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
            },
        },
        created() {
            this.setValues()
        },
        mounted() {},
        methods: {
            setValues() 
            {
                for (var i = 0; i < this.wlist.length; i++) {
                    this.companylist[i] = this.wlist[i]
                        .company
                        this
                        .wantedlist[i] = this
                        .wlist[i]
                        .wanted
                }
            },
            event1(event)
            {   
                alert(event.event.name)
                console.log(event)
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
                    .$refs.calendar.prev()
            },
            next() {
                this
                    .$refs
                    .calendar
                    .next()
            },
            updateRange({start, end}) {
                const events = []
                if (this.companylist.length){
                for (var i = 0; i < this.wlist.length; i++) {
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
            }
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