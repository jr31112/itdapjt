<template>
    <div class="v-toolbar__content" style="height: 64px;">
      <router-link class="mt-2" :to="{name:'recruitmain'}">
        <v-img :src="getImgUrl('logoo.png')" style="width :6.5rem" />
      </router-link>
      <div class="spacer"></div>
      <div class="v-responsive mr-0 mr-md-6 hidden-xs-only" style="max-width: 300px;">
        <div class="v-responsive__content">
          <div
            class="v-input v-input--hide-details v-input--dense theme--dark v-text-field v-text-field--single-line v-text-field--solo v-text-field--solo-inverted v-text-field--solo-flat v-text-field--is-booted v-text-field--enclosed v-text-field--rounded"
          >
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
                    style="left: 0px; right: auto; position: absolute;"
                  ></label>
                  <input
                    id="search"
                    type="text"
                    class="ds-input"
                    placeholder="기업명을 검색해주세요"
                    autocomplete="off"
                    spellcheck="false"
                    role="combobox"
                    aria-autocomplete="list"
                    aria-expanded="false"
                    aria-label="search input"
                    aria-owns="algolia-autocomplete-listbox-0"
                    dir="auto"
                    style
                  />
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
        style="min-width: 48px;"
      >
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
                style="bottom: calc(100% - 4px); left: auto; right: calc(100% - 4px); top: auto; display: none;"
              >
                <i aria-hidden="true" class="v-icon notranslate mdi mdi-tag-text theme--dark"></i>
              </span>
            </span>
          </span>
          <i
            aria-hidden="true"
            class="v-icon notranslate v-icon--right mdi mdi-open-in-new theme--dark"
          ></i>
        </span>
      </a>
      <v-btn
        v-on:click="goTotalWantedPage()"
        class="hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text theme--dark v-size--default"
        aria-label="For Enterprise"
        style="min-width: 48px;"
      >
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
          class="v-menu__content theme--light"
          style="max-height: calc(100% - 16px); min-width: 0px; top: 12px; left: 12px; transform-origin: left top; z-index: 0; display: none;"
        ></div>
      </div>
      <div class="v-menu">
        <div
          role="menu"
          class="v-menu__content theme--light"
          style="max-height: calc(100% - 16px); min-width: 0px; top: 12px; left: 12px; transform-origin: left top; z-index: 0; display: none;"
        ></div>
      </div>
      <!--
            +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      -->
      <!-- // v-on:click="goTotalWantedPage()" -->
      <v-btn
        v-if="isLogin"
        @click="$store.dispatch('logout')"
        @click.stop="dialog =!dialog"
        class="v-btn v-btn--flat v-btn--text theme--dark v-size--default"
        aria-label="Support"
        style="min-width: 48px;"
      >
        <span class="v-btn__content">
          <span class="subtitle-1 text-capitalize font-weight-light">
            <div class="v-markdown">Log Out</div>
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
        style="min-width: 48px;"
      >
        <!-- v-else router :to="{name: 'login'}" -->
        <span class="v-btn__content">
          <span class="subtitle-1 text-capitalize font-weight-light">
            <div class="v-markdown">Login | SignUp</div>
          </span>
        </span>
        <!--먼가 여기서 IsDialog를 살려주면 될것같다 , 처음 로그인이라면 ~ , -->
      </v-btn>
      <!-------------------------------------- 모달창 ------------------------------------------>
      <v-dialog
        v-if="isDialog"
        v-model="dialog"
        z-index="3"
        overlay-opacity="0.4"
        max-width="545px"
      >
        <sign-up-in/>
      </v-dialog>
    </div>
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
      chk: true,
      email: null,
      password: null,
      dialog: false,
      token: localStorage.getItem("access_token")
    };
  },
  computed: {
    ...mapState(["isLogin", "isLoginError", "isDialog"])
  },
  methods: {
    getImgUrl(img) {
      return require("../../assets/" + img);
    },
    goTotalWantedPage() {
      router.push({ name: "recruitcalender" }).catch(() => {});
    },
  }
};
</script>