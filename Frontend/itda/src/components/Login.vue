<template>
    <v-container >
        <!-- 컨테이너 크기를 500으로해 해주고, fill-heigt를 통해서아래 align center를 이용할 수 있게 됨. -->
        <v-layout wrap="no wrap">
                <v-flex xs12="xs12">
                <v-alert class="mb-1" :value="isLoginError" type="error">
                    아이디와 비밀번호를 확인해주세요.
                </v-alert>
                <v-alert class="mb-1" :value="isLogin" type="success">
                    로그인이 완료되었습니다.
                </v-alert>
                <v-card>
                    <v-toolbar flat="flat">
                        <v-toolbar-title>Login</v-toolbar-title>
                        <v-btn color="green darken-1" text="text" @click="dialog = false">
                            Close
                        </v-btn>
                    </v-toolbar>
                    <div class="pa-4" style="max-width: 520px">
                        <!-- pa는 패딩오토 -->
                        <v-text-field v-model="email" name="email" label="email을 입력하세요"></v-text-field>
                        <v-text-field
                            v-model="password"
                            name="pw"
                            type="password"
                            label="password를 입력하세요"
                            @keyup.enter="goLogin()"></v-text-field>
                        <v-btn
                            class="mb-1"
                            color="primary"
                            depressed="depressed"
                            block="block"
                            large="large"
                            @click="login(
                                {
                                    email:email,
                                    pw:password
                                }
                                )">로그인
                        </v-btn >
                        <v-btn 
                        class="mb-1" 
                        depressed="depressed" 
                        block="block" 
                        large="large"
                        @click="goRegister"
                        v-model="ok"
                        >
                            회원가입</v-btn>
                    </div>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
    import {mapState, mapActions} from 'vuex';
    // import axios from "axios"
    import router from '../router/index.js';
    export default {
        data() {
            return {
                chk: null,
                email: null, password: null, dialog: false, token: localStorage.getItem("access_token")};
        },
        computed: {
            ...mapState(["isLogin", "isLoginError", "isDialog"])
        },
        components: {},
        methods: {
            getImgUrl(img) {
                return require('../assets/' + img);
            },
            goTotalWantedPage() {
                router
                    .push({name: 'totalwanted'})
                    .catch(() => {});
            },
            goRegister()
            {
           //     this.$emit(goRegister, );
                router
                    .push({name: 'register'})
                    .catch(() => {});
            }
            ,
            ...mapActions(["login"]),
            goLogin() {
                this
                    .$store
                    .dispatch('login', {
                        email: this.email,
                        pw: this.password
                    })
            }
        }
    };
</script>