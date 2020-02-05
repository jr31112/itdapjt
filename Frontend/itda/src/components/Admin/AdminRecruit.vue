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
                    <v-card>
                        <v-card-title class="justify-center">
                            <h4>공고 관리</h4>
                        </v-card-title>
                        <v-card-text>
                            <v-container v-scroll:#scroll-target="onScroll">
                                <v-row >
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.company" label="회사명"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.stack" label="필요기술"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.s_date" label="시작일"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.f_date" label="마감일"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4">
                                        <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                    </v-col>
                                </v-row>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="editedItem.rate" label="싸피인취업현황"></v-text-field>
                                </v-col>
                            </v-container>
                        </v-card-text>

                        <v-card-actions  >
                            <v-layout >
                                <v-flex>
                                    <v-btn color="blue darken-1" text="text" @click="save">Save</v-btn>
                                    <v-btn color="blue darken-1" text="text" @click="close">Cancel</v-btn>
                                </v-flex >
                            </v-layout>
                        </v-card-actions>

                    </v-card>
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
    import axios from 'axios'
    export default {
        name: "adminrecruit",
        mounted() {
            this.getWantedAll()
        },
        methods: {
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
            editItem(item) {
                this.editedIndex = this
                    .allwanteds
                    .indexOf(item)
                this.editedItem = Object.assign({}, item)
                this.dialog = true
            },

            deleteItem(item) {
                const index = this
                    .allwanteds
                    .indexOf(item)
                confirm('Are you sure you want to delete this Recruit?') && this
                    .allwanteds
                    .splice(index, 1)
            },

            close() {
                this.dialog = false
                setTimeout(() => {
                    this.editedItem = Object.assign({}, this.defaultItem)
                    this.editedIndex = -1
                }, 300)
            },

            save() {
                if (this.editedIndex > -1) {
                    Object.assign(this.allwanteds[this.editedIndex], this.editedItem)
                } else {
                    this
                        .allwanteds
                        .push(this.editedItem)
                }
                this.close()
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
                onScroll: false,
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