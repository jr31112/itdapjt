<template>
    <v-container>
        <v-layout row="row" wrap="wrap">
            <v-flex xs12="xs12" text-xs-center="text-xs-center">
                <h1>
                    대화 리스트
                </h1>
            </v-flex>
            <!-- <v-flex xs6 pa-2> <ListAdd @listJW="listAdd" @listEdit="listEditJW"
            ></ListAdd> </v-flex> -->
            <v-flex xs6="xs6" pa-2="pa-2">
                <p>자! 가자!</P>
                <StudyChatList :listAdd="listAdd"></StudyChatList>

            </v-flex>
        </v-layout>
    </v-container>
</template>

<script >
    import axios from 'axios'
    import {mapState} from "vuex";
    import StudyChatList from './StudyChatList.vue'
    export default {
        data() {
            return {listAdd: []}
        },
        components: {
            StudyChatList
        },
        props: {
            person: {
                type:Array
            },
            study: {
                type:Object
            }
        },
        computed: {
            ...mapState(["isLogin", "isLoginError", "isDialog"])
        },
        mounted() {
            this.getDatainFo()
        },
        methods: {
            
            getDatainFo() {
                console.log(this.study.stid)
                axios
                    .get('https://i02b201.p.ssafy.io:8197/itda/api/getCommentById/' + this.study.stid)
                    .then(response => {
                            this.listAdd = response.data;
                            console.log(this.listAdd)
                    })
                    .catch(() => {});
            }
        }
    }
</script>