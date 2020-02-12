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
                :search="search">
                <template v-slot:item.pcnt="{ item }">
                <span>{{item.pcnt}}/{{item.maxPcnt}}</span>
                </template> 

                <template v-slot:item.auth="{ item }">
                <span v-if="item.wanted.endDate.slice(0,4) == '2037'">상시</span>
                <span v-else>
                    <!-- getDate()-->
                    {{this.leftDate}}
                </span>        
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
    import {eventBus} from '../../main.js'
    import axios from 'axios'
    export default {
        name: "searchfilter",

        data() {
            return {
                category: null,
                content: null,
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
                search: '',
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
            this.getWantedList()       
        },
        created() {
            eventBus.$on('NavContent', (content) => {
                this.content = content
                console.log(this.content)
            })
        },
        methods: {
            getWantedList() {
                axios
                    .get('https://192.168.31.54:8197/itda/api/getWantedAll')
                    .then(response => {
                        this.defaultContent = response.data
                        console.log(this.defaultContent)
                    })
                    .catch(error => {
                        // alert('안돼')
                        console.log(error)
                    })
                },
            getDate()
            {
                var endday = new Date(this.wanted.endDate)
			    var today = new Date()
			    var datediff = parseInt((endday-today)/(24*60*60*1000))
			    if (datediff > 0)
				this.leftDate = "D-"+ datediff
				else
				if (datediff == 0)
					this.leftDate = "D-day"
				else
					this.leftDate = "마감"
            }
        }

    }
</script>

<style></style>