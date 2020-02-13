<template >
    <v-card>
        <v-layout >
            <v-flex xs9="xs9" ma-3="ma-3">
                <v-btn style="float:right " class="ml-auto mt-3" @click="createdWanted()">사람인 New +</v-btn>
            </v-flex>
            <v-flex xs3="xs3" style="float:right">
                <v-card-title >
                    <v-text-field
                        v-model="search"
                        append-icon="search"
                        label="Search"
                        single-line="single-line"
                        hide-details="hide-details"></v-text-field>
                </v-card-title>
            </v-flex>
        </v-layout>
        <v-data-table
            :headers="headers"
            :items-per-page="5"
            :items="allwanteds"
            :search="search">
          
            <template v-slot:item.wanted.startDate="{ item }">
                {{item.wanted.startDate.slice(0,10)}}일 
                {{item.wanted.startDate.slice(11,13)}}시
            </template>
             <template v-slot:item.wanted.endDate="{ item }">
                 {{item.wanted.endDate.slice(0,10)}}일 
                 {{item.wanted.endDate.slice(11,13)}}시
            </template>
            <template v-slot:item.action="{ item }">
                <v-icon small="small" @click="deleteItem(item)">
                    delete
                </v-icon>
            </template>

        </v-data-table>
    </v-card>
</template>
<script>
    import {mapState} from 'vuex'
    import axios from 'axios'

    export default {
        name: "adminrecruit",
        mounted() {
            this.getWantedAll()
        },
        methods: {

            onClick()
            {
                alert("Go away")
            },
            createdWanted()
            {
                 axios
                    .post('http://192.168.31.54:8197/itda/api/createWanted/')
                    .then(res => {
                        this.createData = res; 
                        alert("성공"+ res)
                    })
                    .catch((err) => {
                        alert(err)
                    })
            }
            ,    
            getWantedAll() {
                axios
                    .get('http://192.168.31.54:8197/itda/api/getWantedAll/')
                    .then(res => {
                        this.allwanteds = res.data
                    })
                    .catch((err) => {
                        alert(err)
                    })
                },
            deleteItem(item) {
                alert(item)
            }
        },
        computed: {
            ...mapState(["isLogin"])
        },
        data() {
            return {
                search: '',
                headers: [
                    {
                        text: '공고명',
                        align: 'left',
                        value: 'wanted.wantedTitle'
                    }, {
                        text: '회사명',
                        value: 'company.corpNm',
                        align: 'left'

                    }, {
                        text: '시작일',
                        value: 'wanted.startDate',
                        align: 'center'
                    }, {
                        text: '마감일',
                        value: 'wanted.endDate',
                        align: 'center'
                    }, {
                        text: 'Actions',
                        value: 'action',
                        align: 'center',
                        sortable: false
                    }
                ],
                createData:
                []
                    // {
                    //     active: 0,
                    //     cid: '13245',
                    //     detail: 'fdsfds',
                    //     endDate : '2020-12-12',
                    //     startDate: '2020-02-12',
                    //     wantedTitle: 'fdsfsd',
                    //     wid : '120320'
                    // }
                ,
                allwanteds: []
            }
        }
    }
</script>
<style></style>