<template>
    <v-container>
        <v-card>
            <v-layout>
                <v-row class="mx-auto ma-7">
                    <v-col class="pa-3" cols="12" md="3">
                        <v-select v-model="category" :items="category1Options" label="종류"></v-select>
                    </v-col>
                    <v-col class="pb-0" cols="12" md="9">
                        <v-text-field
                            v-model="content"
                            label="키워드"
                            placeholder="키워드를 입력해주세요"
                            outlined="outlined"></v-text-field>
                    </v-col>
                </v-row>
            </v-layout>
            <v-data-table 
                :headers="headers"
                :items-per-page="5"
                :items="defaultContent"
                :search="content">
                <template v-slot:item.pcnt="{ item }">
                <span>{{item.pcnt}}/{{item.maxPcnt}}</span>
                </template> 

                <template v-slot:item.action="{ item }">
                    <v-icon small="small" @click="deleteItem(item)">
                        delete
                    </v-icon>
                </template>
            </v-data-table>
        </v-card>
    </v-container>
</template>
<script>
    import axios from 'axios'
    export default {
        name: "searchfilter",

        data() {
            return {
                category: null,
                content: '',
                search:'',
                category1Options: [
                    {
                        text: '전체보기',
                        value: 0
                    }, {
                        text: '기업별',
                        value: 1
                    }, {
                        text: '기술스택별',
                        value: 2
                    }
                ],
                defaultContent: [],
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
                    }, 
                    {
                        text: 'Actions',
                        value: 'wanted.active',
                        align: 'center',
                    }
                ]
            }
        },
        mounted()
        {   
            console.log(this.$route)
            this.getWantedList()     
        },
        created() {
            this.content= this.$route.query.cont
        },
        methods: {
            getWantedList() {
                axios
                    .get('http://192.168.31.54:8197/itda/api/getWantedAll')
                    .then(response => {
                        this.defaultContent = response.data
                        console.log(this.defaultContent)
                    })
                    .catch(error => {
                        // alert('안돼')
                        console.log(error)
                    })
                },
        }

    }
</script>

<style></style>