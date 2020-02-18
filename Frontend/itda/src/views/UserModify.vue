<template>
    <v-container>
        <v-card>
            <v-card-title>User Info</v-card-title>
            <v-card-subtitle>User Info</v-card-subtitle>
            <v-row>
                <v-col cols='3'>
                    <v-file-input
                        label="your image input"
                        filled="filled"
                        hint="hint"
                        height="200px"
                        prepend-icon="mdi-camera"
                        class="ma-2"
                        @click="goThis()"></v-file-input>
                </v-col>
                <v-col cols='6'>
                    <v-card-subtitle>User Name</v-card-subtitle>
                    <v-card-text>
                        <v-text-field v-model="userInfo.uname" label="Name"></v-text-field>
                    </v-card-text>
                </v-col>
            </v-row>

            <v-card-subtitle>User email</v-card-subtitle>
            <v-card-text>
                <v-text-field v-model="userInfo.email" label="Email"></v-text-field>
            </v-card-text>

            <v-card-subtitle>User Major</v-card-subtitle>
            <v-card-text>
                <v-text-field v-model="userInfo.major" label="Major"></v-text-field>
            </v-card-text>

            <v-card-title>Tech Stack
                <v-spacer></v-spacer>
                <v-dialog v-model="dialog" scrollable="scrollable" max-width="700px">
                    <template v-slot:activator="{ on }">
                        <v-btn
                            class="mx-3"
                            fab="fab"
                            dark="dark"
                            color="gray"
                            width="30px"
                            height="30px"
                            v-on="on">
                            <v-icon dark="dark">mdi-plus</v-icon>
                        </v-btn>
                        <v-icon dark="dark">mdi-plus</v-icon>
                    </template>
                    <v-card width="500px">
                        <v-card-title>Select Stack</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text style="height: 500px;">   
                            <v-row v-for="i in 9" :key="i">
                                    <v-col v-for="j in i==9?1:5" :key="j">
                                        <v-checkbox
                                            v-model="stacklist[5*(i-1) + j-1].value"
                                            :label="stacklist[5*(i-1) + j-1].tname"></v-checkbox>
                                    </v-col>
                            </v-row>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-spacer></v-spacer>
                        <v-card-actions class= mb-3>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text="text" @click="goSaveStack">Save</v-btn>
                            <v-btn color="blue darken-1" text="text" @click="dialog = false">Close</v-btn>
                        </v-card-actions>
                        
                  
                    </v-card>
                </v-dialog>
            </v-card-title>
            <v-card-text
            :rules="[v => !!v || 'Stack을 입력해주세요']"
            >
                <!-- <v-for="i in userInfo.tname.length()" -->
                    <!-- {{userInfo.mystacks.length}} -->
                <v-btn v-for="i in userInfo.mystacks.length" :key="i" @click="removeStack(i-1, userInfo.mystacks[i-1].sid)">{{userInfo.mystacks[i-1].tname}} </v-btn>
            </v-card-text>
        </v-card>


        <v-divider></v-divider>
        <v-card>
            <v-card-title>My study Info</v-card-title>
            <v-card-text></v-card-text>
        </v-card>


    </v-container>
</template>

<script>
import axios from 'axios'
    export default {
        data() {
            return {
                userInfo: {},
                files: [],
                MystackList: [],
                dialog:false,
                stacklist: [
                    {
                        sid: 1,
                        tname: "Java",
                        cnt: 5,
                        value:false
                    }, {
                        sid: 2,
                        tname: "C",
                        cnt: 23,
                        value:false
                    }, {
                        sid: 3,
                        tname: "C++",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 4,
                        tname: "C#",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 5,
                        tname: "Objective-C",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 6,
                        tname: "Python",
                        cnt: 4,
                        value:false
                    }, {
                        sid: 7,
                        tname: "PHP",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 8,
                        tname: "JavaScript",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 9,
                        tname: "Kotlin",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 10,
                        tname: "Unix",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 11,
                        tname: "Linux",
                        cnt: 3,
                        value:false
                    }, {
                        sid: 12,
                        tname: "Windows",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 13,
                        tname: "Android",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 14,
                        tname: "iOS",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 15,
                        tname: "MySQL",
                        cnt: 3,
                        value:false
                    }, {
                        sid: 16,
                        tname: "MS-SQL",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 17,
                        tname: "Oracle",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 18,
                        tname: "MariaDB",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 19,
                        tname: "MongoDB",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 20,
                        tname: "SQLite",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 21,
                        tname: "NoSQL",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 22,
                        tname: "HTML",
                        cnt: 3,
                        value:false
                    }, {
                        sid: 23,
                        tname: "CSS",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 24,
                        tname: "Vue.js",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 25,
                        tname: "jQuery",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 26,
                        tname: "Ajax",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 27,
                        tname: "Spring",
                        cnt: 3,
                        value:false
                    }, {
                        sid: 28,
                        tname: "Django",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 29,
                        tname: "Flask",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 30,
                        tname: "Node.js",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 31,
                        tname: "JSP",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 32,
                        tname: "JPA/Hibernate",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 33,
                        tname: "WAS",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 34,
                        tname: "AWS",
                        cnt: 5,
                        value:false
                    }, {
                        sid: 35,
                        tname: "OOP",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 36,
                        tname: "AI",
                        cnt: 2,
                        value:false
                    }, {
                        sid: 37,
                        tname: "Machine Learning",
                        cnt: 1,
                        value:false
                    }, {
                        sid: 38,
                        tname: "Docker",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 39,
                        tname: "RESTful-API",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 40,
                        tname: "JIRA",
                        cnt: 0,
                        value:false
                    }, {
                        sid: 41,
                        tname: "Git",
                        cnt: 0,
                        value:false
                    }
                ]
            }
        },
        created() {
            this.getAllData()
        },
        methods: {
            removeStack(idx, sid){
                this.userInfo.mystacks.splice(idx, 1)
                this.stacklist[sid-1].value = false
            },
            goSaveStack() {
                this.dialog= false
                var tmp = []
                for (var i=0; i < 41; i++){
                    if (this.stacklist[i].value){
                        tmp.push({
                            "sid":this.stacklist[i].sid,
                            "tname":this.stacklist[i].tname
                        })
                    }
                }
                this.userInfo.mystacks = tmp
            },
            getAllData(){
                axios
                    .get(`https://i02b201.p.ssafy.io:8197/itda/api/getUser/`, {
                        headers: {
                            "jwt-auth-token": localStorage.getItem("access_token")
                        }
                    })
                    .then(response => {
                        if (response.data.state == 'success') {
                            this.userInfo = response.data
                            var tmp_stacks = response.data.mystacks
                            for (var i=0; i<tmp_stacks.length;i++){
                                this.stacklist[tmp_stacks[i].sid-1].value = true
                            }
                        } else {
                            //실패했으니 다시 로그인해라고 알려주든지?
                        }
                    })
                    .catch(() => {
                        //에러가 났다. 다시 로그인해라.
                    })

                },

        }
    }
</script>

<style></style>