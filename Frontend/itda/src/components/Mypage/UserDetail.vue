<template>
    <v-row>
        <v-col>
            <v-card max-hight="500" max-width="375" class="mx-auto">
                <v-img
                    src="https://cdn.vuetifyjs.com/images/lists/ali.png"
                    height="330px"
                    dark="dark"></v-img>
            </v-card>
        </v-col>
        <v-col>
            <v-card max-hight="500" max-width="375" class="mx-auto">
                <v-list one-line="two-line">
                    <v-list-item >
                        <v-list-item-content>
                            <v-list-item-title>email</v-list-item-title>
                            <v-list-item-subtitle>{{userInfo.user.email}}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>이름</v-list-item-title>
                            <v-list-item-subtitle>{{userInfo.user.uname}}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>전공</v-list-item-title>
                            <v-list-item-subtitle>{{userInfo.user.major}}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>기술 스택</v-list-item-title>
                            <v-list-item-subtitle>
                                <tr>
                                    <td v-for="item in userInfo.mystacks" :key="item">{{item.tname}}</td>
                                </tr>
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>취업 정보</v-list-item-title>
                            <v-list-item-subtitle>{{userInfo.user.cid}}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    export default {
        name: "userdetail",
        data() {
            return{

            }
        },
        props: {
            userInfo:{
                type: Array
            }
        },
        mounted() {
            
        },
        methods: {
            
        }
    }
</script>

<style></style>