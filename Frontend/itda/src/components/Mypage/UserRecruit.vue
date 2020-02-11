<template>
    <v-container>
        <v-layout>

            <v-flex xs6="xs6" pa-2="pa-2">
                <user-current-recruit :UserScrapWanteds="UserScrapWanteds"/>
            </v-flex>
            <v-flex xs6="xs6" pa-2="pa-2">
                <user-end-recruit :UserScrapWanteds="UserScrapWanteds"/>
            </v-flex>

        </v-layout>
    </v-container>
</template>

<script>
    import UserCurrentRecruit from "./UserCurrentRecruit.vue"
    import UserEndRecruit from "./UserEndRecruit.vue"
    export default {
        name: "userrecruit",
        components: {
            UserCurrentRecruit,
            UserEndRecruit
        },
        props:{
            UserEndedScrapWanteds:{
                type: Array
            },
            UserScrapWanteds:{
                type: Array
            }
        },
        mounted(){

        },
        methods:{
            
        }

    }
</script>

<style></style>