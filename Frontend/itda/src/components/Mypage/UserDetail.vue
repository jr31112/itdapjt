<template>
    <v-row>
        <v-col>
            <v-card max-hight="500" max-width="375" class="mx-auto">
                <v-img
                    src="http://cdn.vuetifyjs.com/images/lists/ali.png"
                    height="330px"
                    dark="dark"></v-img>
            </v-card>
        </v-col>
        <v-col>
            <v-card max-hight="500" max-width="375" class="mx-auto" v-if="userInfo.user">
                <v-list one-line="two-line">
                    <v-list-item >
                        <v-list-item-content>
                            <v-list-item-title>이메일</v-list-item-title>
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
                            <v-list-item-subtitle v-if="userInfo.user.major">{{userInfo.user.major}}</v-list-item-subtitle>
                            <v-list-item-subtitle v-else>전공 정보를 입력해주세요!</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>기술스택</v-list-item-title>
                            <v-list-item-subtitle v-if="userInfo.mystacks.length">
                                <tr><td v-for="k in userInfo.mystacks" :key="k.tmp">{{k.tname}}</td></tr>
                            </v-list-item-subtitle>
                            <v-list-item-subtitle v-else>
                                <tr><td>기술 스택을 입력해주세요!</td></tr>
                            </v-list-item-subtitle>
                        </v-list-item-content>
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