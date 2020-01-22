<template>
    <v-sheet>
        <v-select
            v-model="type"
            :items="types"
            dense="dense"
            outlined="outlined"
            hide-details="hide-details"
            class="ma-2"
            label="type"></v-select>
        <v-select
            v-model="mode"
            :items="modes"
            dense="dense"
            outlined="outlined"
            hide-details="hide-details"
            label="event-overlap-mode"
            class="ma-2"></v-select>
        <v-select
            v-model="weekday"
            :items="weekdays"
            dense="dense"
            outlined="outlined"
            hide-details="hide-details"
            label="weekdays"
            class="ma-2"></v-select>
    </v-sheet>
</template>

<script>
    export default {}
</script>

<style></style>