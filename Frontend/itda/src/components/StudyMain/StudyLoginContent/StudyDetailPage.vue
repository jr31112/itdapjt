<template>
    <v-container>
        <FullCalendar
            defalutView="timeGridWeek"
            :plugins="calendarPlugins"
            @dateClick="handleDateClick"
            @eventRender="eventRender"
            :minTime='minTime'
            :maxTime='maxTime'
            :contentHeight="contentHeight"
            />
    </v-container>

</template>
<script>
    import FullCalendar from '@fullcalendar/vue'
    // import { Calendar } from '@fullcalendar/core';
    import timeGridPlugin from '@fullcalendar/timegrid';
    import resourceTimelinePlugin from '@fullcalendar/resource-timeline'

    export default {
        components: {
            FullCalendar
        },
        data() {
            return {
                calendarPlugins: [timeGridPlugin, resourceTimelinePlugin],
                minTime: '12:00',
                maxTime: '24:00',
                contentHeight: 'auto'
            }
        },
        methods: {
            clickEvent(date, event, view) {
                this
                    .$refs
                    .my_modal
                    .show();
                this.selectedStartTime = date.format("YYYY-MM-DD HH:mm:ss");
                this.myTime = date.toDate();
                console.log(view)
            },
            handleDateClick(arg){
                alert("hello" + arg)
            }
        }
    }
</script>

<style lang='scss'>
    @import '~@fullcalendar/core/main.css';
    @import '~@fullcalendar/timegrid/main.css';
</style>