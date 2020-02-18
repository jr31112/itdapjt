<template>
        <v-card>
            <v-layout>
                <v-flex xs12="xs12">
                    <v-card-title style="float:right">
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
                :items="allstudys"
                :items-per-page="5"
                :search="search"
                >
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
</template>

<script>
    import axios from 'axios'
    export default {
        name: "adminstudy",
        mounted() {
            this.getAllStudys()
        },
        methods: {
            getAllStudys() {
                axios
                    .get('https://i02b201.p.ssafy.io:8197/itda/api/getAllStudy/')
                    .then(res => {
                        this.allstudys = res.data

                    })
                    .catch((err) => {
                        alert(err)
                    })
           },
           deleteItem(item)
           {
             alert(item.stid)
              axios
              .delete('https://i02b201.p.ssafy.io:8197/itda/api/deleteStudy/'+item.stid)
              .then(res => {
                  
                  if(res.data.state == 'success'){
                      this.getAllStudys();
                  }
              })
              .catch(() => 
              {
              })
           }
        },
        data() {
            return {
                search: '', 
                headers: [
                    {
                        text: 'Num.',
                        align: 'center',
                        sortable: false,
                        value: 'stid'
                    }, {
                        text: '스터디명',
                        value: 'stname'
                    }, {
                        text: '인원수',
                        value: 'pcnt'
                    }, 
                    {
                        text: '내용',
                        value: 'content',
                        sortable: false
                    }, {
                        text: '삭제',
                        align: 'center',
                        value: 'action',
                        sortable: false
                    }
                ],
                allstudys: []
            }
        }
    }
</script>

<style></style>