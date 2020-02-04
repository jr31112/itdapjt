<template>
    <v-container>
        <v-row>
            <v-col><user-scrap-list/></v-col>
            <v-col><user-stack-list/></v-col>
        </v-row>

    </v-container>
</template>

<script>
import UserScrapList from "./UserScrapList.vue"
import UserStackList from "./UserStackList.vue"
export default {
    name:"userrecruit",
    components:{
        UserScrapList,
        UserStackList
    }
}
</script>

<style>

</style>