<template>
  <v-app-bar color="blue" app>
    <router-link :to="{name:'recruitmain'}">
      <v-img :src="getImgUrl('logoo.png')" style="width :6.5rem" />
    </router-link>
    <div class="page-router">
      <v-btn
        @click="goRecruitCalanderPage()"
        class="hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text theme--dark v-size--default"
        style="min-width: 48px;"
      >
        상세보기
      </v-btn>
      <v-btn
        @click="goStudyPage()"
        class="hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text theme--dark v-size--default"
        style="min-width: 48px;"
      >
        스터디보기
      </v-btn>
    </div>
    <v-spacer></v-spacer>
    <div class="search">
      <div class="v-responsive mr-0 mr-md-6 hidden-xs-only" style="max-width: 300px;">
        <div class="v-responsive__content">
          <div class="v-input v-input--hide-details v-input--dense theme--dark v-text-field v-text-field--single-line v-text-field--solo v-text-field--solo-inverted v-text-field--solo-flat v-text-field--is-booted v-text-field--enclosed v-text-field--rounded">
            <div class="v-input__control">
              <div class="v-input__slot">
                <div class="v-text-field__slot">
                  <label
                    for="search"
                    class="v-label theme--dark"
                    style="left: 0px; right: auto; position: absolute;"
                  ></label>
                  <input
                    id="search"
                    v-model="content"
                    type="text"
                    class="ds-input"
                    placeholder="공고 통합 검색"
                    autocomplete="off"
                    spellcheck="false"
                    role="combobox"
                    aria-autocomplete="list"
                    aria-expanded="false"
                    aria-label="search input"
                    aria-owns="algolia-autocomplete-listbox-0"
                    dir="auto"
                    @keyup.enter="goSearch(content)"
                  />
                </div>
                <div class="v-input__prepend-inner" @click.prevent="goSearch(content)">
                  <div class="v-input__icon v-input__icon--prepend-inner">
                    <i aria-hidden="true" class="v-icon notranslate mdi mdi-magnify theme--dark"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="user">

    </div>
    <v-btn
      v-if="isMr()==='0'"
      class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
      @click="GoManagePage"
    >
      페이지 관리
    </v-btn>
    <v-btn
      v-if="isLogin"
      class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
      @click="goMypage"
    >
      내정보 보기
    </v-btn>
    <v-btn
      v-if="isLogin"
      @click="goLogout"
      @click.stop="dialog =!dialog"
      class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
    >
      로그아웃
    </v-btn>
    <v-btn
      v-else
      @click.stop="dialog=!dialog"
      @click="goLogin()"
      class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
    >
      로그인/회원가입
    </v-btn>
    <v-dialog 
      v-if="isDialog"
      v-model="dialog"
      z-index="3"
      overlay-opacity="0.4"
      max-width="545px"
    >
      <sign-up-in @statusControl="dialog =false"/>
    </v-dialog>
  </v-app-bar>
</template>
<script>
import SignUpIn from "./SignUpIn.vue";
import { mapState } from "vuex";
import router from "../../router";
export default {
  name:'navbar',
  components: {
    SignUpIn
  },
  data() {
    return {
      content:null,
      chk: true,
      email: null,
      password: null,
      dialog: false,
      token: localStorage.getItem("access_token"),
      isLoginFlag: false
    };
  },
  computed: {
    ...mapState([ "isLogin", "isLoginError", "isDialog"])
  },
  methods: {
    isMr()
    {
      let token = localStorage.getItem("mid")
      return token
    },
    getImgUrl(img) {
      
      return require("../../assets/" + img);
    },
    goRecruitCalanderPage() {
      router.push({ name: "recruitcalender" }).catch(() => {});
    },
    goStudyPage() {
      router.push({ name: "studymain" }).catch(() => {});
    },
    goLogin()
    {
      this.$store.state.isDialog =true
    },
    goLogout()
    {
      this.$store.state.isDialog = false 
      this.$store.dispatch('logout')
      router.push({name:"recruitmain"}).catch(() => {});
    },
    goSearch(content)
    {
       router.push({ name: "searchresult", query:{cont: content}}).catch(() => {});
    },
    goMypage(){
      router.push({name:"mypage",params:{id:1}})
    },
    GoManagePage()
    {
      router.push({name:"admin"}).catch(() => {});
    }
  
  }
};
</script>