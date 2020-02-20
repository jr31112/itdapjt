<template>
    <v-row>
        <v-col cols='12' sm="4">
            <v-card max-hight="300" max-width="300" class="mx-auto">
                <v-img
                    :src="this.userInfo.user.uimg"
                    height="300px"
                    dark="dark"></v-img>
            </v-card>
        </v-col>
        <v-col  cols='12' sm="8">
            <v-card  max-hight="300"  class="mx-auto" v-if="userInfo.user">
                <v-list one-line="two-line">
                    <v-list-item >
                        <v-row>
                            <v-col class="pl-5" cols='4'>
                            <v-list-item-title> 이메일 </v-list-item-title>
                            </v-col>
                            <v-col cols='8'>
                            <v-list-item-title >{{userInfo.user.email}}</v-list-item-title>
                            </v-col>
                        </v-row>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-row>
                            <v-col class="pl-5" cols='4'>
                            <v-list-item-title>이름 </v-list-item-title>
                            </v-col>
                            <v-col cols='8'>
                            <v-list-item-title >{{userInfo.user.uname}}</v-list-item-title>
                            </v-col>
                        </v-row>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                         <v-row>
                            <v-col class="pl-5" cols='4'>
                            <v-list-item-title>전공 </v-list-item-title>
                            </v-col>
                            <v-col cols='8'>
                            <v-list-item-title >{{userInfo.user.major}}</v-list-item-title>
                            </v-col>
                        </v-row>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                         <v-row>
                            <v-col class="pl-5" cols='4'>
                            <v-list-item-title>기술스택</v-list-item-title>
                            </v-col>
                            <v-col cols='8' v-if="userInfo.mystacks.length">
                                <span v-for="k in userInfo.mystacks" :key="k.tmp">{{k.tname}} </span>
                                </v-col>
                            <v-col cols='8' v-else>
                                <span>기술 스택을 추가해주세요!!</span>
                            </v-col>
                        </v-row>
                    </v-list-item>
                    <v-divider v-if="userInfo.user.cid"></v-divider>
                    <v-list-item v-if="userInfo.user.cid">
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
        props: {userInfo:{type: Object}},
    }
</script>

<style></style>