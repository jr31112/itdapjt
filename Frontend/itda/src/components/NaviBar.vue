<template>
    <header
        class="v-sheet v-sheet--tile theme--dark v-toolbar v-app-bar v-app-bar--clipped v-app-bar--fixed v-app-bar--is-scrolled primary"
        id="documentation-app-bar"
        data-booted="true"
        style="height: 64px; margin-top: 0px; transform: translateY(0px); left: 0px; right: 0px;">
        <div class="v-toolbar__content" style="height: 64px;"> 
            <router-link class="mt-2" :to="{name:'home'}"><v-img :src="getImgUrl('logoo.png')" style="width :6.5rem"/></router-link>
            <div class="spacer"></div>
            <div class="v-responsive mr-0 mr-md-6 hidden-xs-only" style="max-width: 300px;">
                <div class="v-responsive__content">
                    <div
                        class="v-input v-input--hide-details v-input--dense theme--dark v-text-field v-text-field--single-line v-text-field--solo v-text-field--solo-inverted v-text-field--solo-flat v-text-field--is-booted v-text-field--enclosed v-text-field--rounded">
                        <div class="v-input__control">
                            <div class="v-input__slot">
                                <div class="v-input__prepend-inner">
                                    <div class="v-input__icon v-input__icon--prepend-inner">
                                        <i aria-hidden="true" class="v-icon notranslate mdi mdi-magnify theme--dark"></i>
                                    </div>
                                </div>
                                <div class="v-text-field__slot">
                                    <label
                                        for="search"
                                        class="v-label theme--dark"
                                        style="left: 0px; right: auto; position: absolute;">기업명을 검색해주세요</label >
                                    <input
                                        id="search"
                                        type="text"
                                        class="ds-input"
                                        autocomplete="off"
                                        spellcheck="false"
                                        role="combobox"
                                        aria-autocomplete="list"
                                        aria-expanded="false"
                                        aria-label="search input"
                                        aria-owns="algolia-autocomplete-listbox-0"
                                        dir="auto"
                                        style=""/>
                                    <pre
										aria-hidden="true"
										style="position: absolute; visibility: hidden; white-space: pre; font-family: Roboto, sans-serif; font-size: 16px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: auto; text-transform: none;"
									></pre>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <a
                href="https://www.naver.com"
                target="_blank"
                class="hidden-xs-only v-btn v-btn--flat v-btn--text theme--dark v-size--default"
                aria-label="Store"
                rel="noopener"
                style="min-width: 48px;">
                <span class="v-btn__content">
                    <span class="v-badge v-badge--left theme--dark">
                        <span class="subtitle-1 text-capitalize font-weight-light">
                            <div class="v-markdown">
                                <span class="mr">JoB Korea</span>
                            </div>
                        </span>
                        <span class="v-badge__wrapper">
                            <span
                                aria-atomic="true"
                                aria-label="Badge"
                                aria-live="polite"
                                role="status"
                                class="v-badge__badge red"
                                style="bottom: calc(100% - 4px); left: auto; right: calc(100% - 4px); top: auto; display: none;">
                                <i aria-hidden="true" class="v-icon notranslate mdi mdi-tag-text theme--dark"></i>
                            </span>
                        </span>
                    </span>
                    <i
                        aria-hidden="true"
                        class="v-icon notranslate v-icon--right mdi mdi-open-in-new theme--dark"></i>
                </span>
            </a>
            <v-btn
                v-on:click="goTotalWantedPage()"
                class="hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text theme--dark v-size--default"
                aria-label="For Enterprise"
                style="min-width: 48px;">
                <span class="v-btn__content">
                    <span class="subtitle-1 text-capitalize font-weight-light">
                        <div class="v-markdown">
                            <span class="mr">상세보기</span>
                        </div>
                    </span>
                </span>
            </v-btn>
            <div class="v-menu">
                <div
                    role="menu"
                    class="v-menu__content theme--light "
                    style="max-height: calc(100% - 16px); min-width: 0px; top: 12px; left: 12px; transform-origin: left top; z-index: 0; display: none;"></div>
            </div>

            <div class="v-menu">
                <div
                    role="menu"
                    class="v-menu__content theme--light "
                    style="max-height: calc(100% - 16px); min-width: 0px; top: 12px; left: 12px; transform-origin: left top; z-index: 0; display: none;"></div>
            </div>
            <!--
            +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            -->

            <!-- // v-on:click="goTotalWantedPage()" -->
            <v-btn
                v-if="isLogin"
                @click="$store.dispatch('logout')"
                class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
                aria-label="Support"
                style="min-width: 48px;">
                <span class="v-btn__content">
                    <span class="subtitle-1 text-capitalize font-weight-light">
                        <div class="v-markdown">
                            Log Out
                        </div>
                        <!-- 디스패치는 스토어에서 엑션스 내에 함수를 실행가능 -->
                        <!--만약에 $store.commit('logout')으로 뮤테이션에서 바로 접근할 수도 있다. -->
                    </span>
                </span>
            </v-btn>

            <v-btn
                v-else
				@click.stop="dialog=!dialog"
                class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
                aria-label="Support"
                style="min-width: 48px;">
                <!-- v-else router :to="{name: 'login'}" -->
                <span class="v-btn__content">
                    <span class="subtitle-1 text-capitalize font-weight-light">
                        <div class="v-markdown">
                            Login | SignUp
                        </div>
                    </span>
                </span>
            </v-btn>
            <v-dialog  
            v-if ="isDialog" 
			v-model="dialog" 
			z-index="3"
			overlay-opacity="0.4"
			max-width= "545px"
			>
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
                                    <!-- flat은 toolbar의 속성이고 아래 그림자 없애주는건가.. 뭐지..? -->
                                    <v-toolbar-title>Login</v-toolbar-title>
                                    <v-btn color="green darken-1" text="text" @click="dialog = false">
                                        Close
                                    </v-btn>
                                </v-toolbar>
                                <div class="pa-4" style="max-width: 520px">
                                    <!-- pa는 패딩오토 -->
                                    <v-text-field v-model="email" name = "email" label="email을 입력하세요"></v-text-field>
                                    <v-text-field  v-model="pw" name = "pw" type="password" label="password를 입력하세요"></v-text-field>
                                    <v-btn
                                        class="mb-1"
                                        color="primary"
                                        depressed="depressed"
                                        block="block"
                                        large="large"
                                         @click="login(
                                        {
                                            email:email,
                                            pw:pw
                                        }
                                        )">로그인
                                    </v-btn >
                                    <v-btn class="mb-1" depressed="depressed" block="block" large="large">회원가입</v-btn>
                               
                                </div>
                            </v-card>
                        </v-flex>
                    </v-layout>
                </v-container>
            </v-dialog>
        </div>
    </header>
</template>
<script>

	import {mapState, mapActions} from 'vuex';
	// import axios from "axios"
	import router from '../router/index.js';
    export default {
        data() {
            return {email: null, password: null, dialog:false};
        },
        computed: {
            ...mapState(["isLogin", "isLoginError","isDialog"])
        },
        components: {

        },
        methods: {
            getImgUrl(img) {
                return require('../assets/' + img);
            },
            goTotalWantedPage() {
                router
                    .push({name: 'totalwanted'})
                    .catch(() => {});
			},
            ...mapActions(["login"])
        }
    };
</script>