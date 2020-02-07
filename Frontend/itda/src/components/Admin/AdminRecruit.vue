<template >
    <v-card>
        <v-layout >
            <v-flex xs11="xs11">
                <v-card-title style="float:right">
                    <v-text-field
                        v-model="search"
                        append-icon="search"
                        label="Search"
                        single-line="single-line"
                        hide-details="hide-details"></v-text-field>
                </v-card-title>
            </v-flex>

            <v-spacer></v-spacer>
            <v-flex xs1="xs1">
                <v-dialog v-model="dialog" max-width="900px">
                    <template v-slot:activator="{ on }">
                        <v-btn color="primary" dark="dark" class=" mt-7" v-on="on">New +</v-btn>
                    </template>
                    <admin-recruit-dialog>
                    </admin-recruit-dialog>    
                </v-dialog>

            </v-flex>
        </v-layout>
        <v-data-table
            :headers="headers"
            :items-per-page="5"
            :items="allwanteds"
            :search="search">
            <template v-slot:item.action="{ item }">
                <v-icon small="small" @click="deleteItem(item)">
                    delete
                </v-icon>
            </template>
        </v-data-table>
    </v-card>
</template>
<script>
import AdminRecruitDialog from "./AdminRecruitDialog.vue"    
import axios from 'axios'
    export default {
        name: "adminrecruit",
        components: 
        {
            AdminRecruitDialog
        },
        mounted() {
            this.getWantedAll()
        },
       
        methods: {
            getWantedAll() 
            {
                axios
                    .get('http://192.168.31.54:8197/itda/api/getWantedAll/')
                    .then(res => {
                        this.allwanteds = res.data

                    })
                    .catch((err) => {
                        alert(err)
                    })
                }
        },
        computed: {
            formTitle() {
                return this.editedIndex === -1
                    ? 'New Item'
                    : 'Edit Item'
            }
        },
        watch: {
            dialog(val) {
                val || this.close()
            }
        },
        data() {
            return {
                search: '',
                dialog: false,
                headers: [
                    {
                        text: '공고명',
                        align: 'left',
                        filterable: false,
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
                allwanteds: [],
                editedIndex: -1,
                editedItem: {
                    company: '',
                    s_date: 0,
                    f_date: 0,
                    rate: 0
                }
            }
        }
    }
</script>

<style></style>