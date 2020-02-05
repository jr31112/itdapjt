<template>
    <v-container>
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
    </v-container>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "adminrequest",
        mounted() {
            this.getAllStudys()
        },
        methods: {
            getAllStudys() {
                axios
                    .get('http://192.168.31.54:8197/itda/api/getAllStudy/')
                    .then(res => {
                        console.log(res.data)
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
              .delete('http://192.168.31.54:8197/itda/api/deleteStudy/'+item.stid)
              .then(res => {
                  
                  if(res.data.state == 'success'){
                    console.log(res)
                      this.getAllStudys();
                  }
              })
              .catch((err) => 
              {
                  alert(err)
                  console.log(err)
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