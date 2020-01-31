<template>
  <v-container>
    <!-- 컨테이너 크기를 500으로해 해주고, fill-heigt를 통해서아래 align center를 이용할 수 있게 됨. -->
    <v-layout wrap="no wrap">
      <v-flex xs12="xs12">
        <v-alert class="mb-1" :value="isLoginError" type="error">아이디와 비밀번호를 확인해주세요.</v-alert>
        <v-alert class="mb-1" :value="isPwError" type="error">비밀번호를 확인해주세요.</v-alert>
        <v-alert class="mb-1" :value="isFormError" type="error">입력 값을 모두 넣어주세요.</v-alert>
        <v-alert class="mb-1" :value="isRegisterError" type="error">이미 존재하는 회원의 ID 입니다.</v-alert>
        <v-alert class="mb-1" :value="isLogin" type="success">로그인이 완료되었습니다.</v-alert>
        <v-alert class="mb-1" :value="cong" type="success">회원가입을 축하합니다! 로그인 해주세요.</v-alert>
        <v-card v-if="chk">
          <v-toolbar flat="flat">
            <v-toolbar-title>Register</v-toolbar-title>
            <!-- <v-btn color="green darken-1" text="text" @click="dialog = false">Close</v-btn> -->
          </v-toolbar>
          <div class="pa-4" style="max-width: 520px" name="regi">
            <v-text-field v-model="uname" name="uname" :value="uname" label="Name*"></v-text-field>
            <v-text-field v-model="email_rg" name="email_rg" :value="email_rg" label="Email*"></v-text-field>
            <v-row>
              <v-col cols="6">
                <v-text-field
                  v-model="password_rg"
                  :value="password_rg"
                  name="password_rg"
                  type="password"
                  label="password*"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  v-model="password_rg1"
                  :value="password_rg1"
                  name="password_rg1"
                  type="password"
                  label="password check*"
                  @keyup.enter="goRegister()"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-container>
              <v-layout>
                <v-btn
                  class="mb-1"
                  color="primary"
                  depressed="depressed"
                  block="block"
                  large="large"
                  @click="goRegister()"
                >Save</v-btn>
              </v-layout>
              <v-layout>
                <v-btn
                  class="mb-1"
                  depressed="depressed"
                  block="block"
                  large="large"
                  @click="chk = !chk"
                >Cancel</v-btn>
              </v-layout>
            </v-container>
          </div>
        </v-card>
        <v-card v-else>
          <v-toolbar flat="flat">
            <v-toolbar-title>Login</v-toolbar-title>
            <!-- <v-btn color="green darken-1" text="text" @click="$emit.dialog = !$emit.dialog">Close</v-btn> -->
          </v-toolbar>
          <div class="pa-4" style="max-width: 520px">
            <!-- pa는 패딩오토 -->
            <v-text-field v-model="email_lg" :value="email" name="email" label="email을 입력하세요"></v-text-field>
            <v-text-field
              v-model="password_lg"
              :value="password"
              name="pw"
              type="password"
              label="password를 입력하세요"
              @keyup.enter="goLogin()"
            ></v-text-field>
            <v-btn
              class="mb-1"
              color="primary"
              depressed="depressed"
              block="block"
              large="large"
              @click="login(
                    {
                        email: email_lg,
                        pw: password_lg
                    }
              )"
            >Login</v-btn>
            <v-btn
              class="mb-1"
              depressed="depressed"
              block="block"
              large="large"
              @click="chk = !chk"
            >Register</v-btn>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { mapState, mapActions } from "vuex";
import axios from "axios";
import router from "../router/index.js";
//import RegisterVue from './Register.vue';
export default {
  data() {
    return {
      isPwError: false,
      isRegisterError: false,
      isFormError: false,
      chk: false,
      cong: false,
      email_rg: "",
      email_lg: "",
      password_rg: "",
      password_rg1: "",
      password_lg: "",
      name: "",
      dialog: false,
      token: localStorage.getItem("access_token")
    };
  },
  computed: {
    ...mapState(["isLogin", "isLoginError", "isDialog"])
  },
  components: {},
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    goTotalWantedPage() {
      router.push({ name: "totalwanted" }).catch(() => {});
    },
    goRegister() {
      //회원가입.
      if (
        this.name =="" ||
        this.email_rg == "" ||
        this.password_rg == "" ||
        this.password_rg1 == "" ) 
      {
        this.isFormError = true;
        this.isResisterError = false;
        return;
      }
      ////////////////이부분의 처리 필요.
      else if (this.password_rg !== this.password_rg1) {
         this.isFormError = true;
         this.isResisterError = false;
         this.isisPwError =true;
      }
      else if( !this.email_rg.includes("@")){
        alert("이메일 형식을 확인해 주세요!");
        return;
      }
      else {
        axios
          .post("http://192.168.31.54:8197/itda/api/signUp", {
            email: this.email_rg,
            pw: this.password_rg,
            uname: this.name_rg
          })
          .then(res => {
            if (res.data.state == "success") {
              this.chk = !this.chk;
              this.isFormError = false;
              this.isRegisterError = false;
              this.cong = !this.cong;
            } 
            else 
            {
              this.isFormError = false;
              this.isRegisterError = true;
              return;
            }
          })
          .catch(err => {
            console.log("hi" + err);
            alert("에러!!!");
          });
      }
    },
    ...mapActions(["login", "register"]),

    goLogin() {
      this.$store.dispatch("login", {
        email: this.email_lg,
        pw: this.password_lg
      });
    }
  }
};
</script>