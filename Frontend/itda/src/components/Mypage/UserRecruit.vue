<template>
    <v-container>
        <v-layout>

            <v-flex xs6="xs6" pa-2="pa-2">
                <user-current-recruit :currentRecrutis="currentRecrutis"/>
            </v-flex>
            <v-flex xs6="xs6" pa-2="pa-2">
                <user-end-recruit :endRecruits="endRecruits"/>
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
        data(){return {
            endRecruits:[],
            currentRecrutis:[]
        }
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
            this.setRecruit()
        },
        methods:{
            setRecruit(){
                this.endRecruits=this.UserEndedScrapWanteds
                this.currentRecrutis=this.UserScrapWanteds
            }
        }

    }
</script>

<style></style>