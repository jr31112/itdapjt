<template>
  <v-card
    class="mx-auto overflow-hidden"
    height="800"
  >
  <v-app-bar app> 
    <!-- //class="d-flex d-sm-none" --> 
    <v-app-bar-nav-icon class="d-flex d-sm-none" 
    @click="drawer=!drawer"><v-icon >menu</v-icon>
    </v-app-bar-nav-icon>
 
    
     <!-- <v-navigation-drawer v-model="drawer" temporary absolute> 
        <v-list-item link>
             <v-avatar
                  size="30px"
                  class="mx-3"
             >
            <v-img :src="getImgUrl('jw.jpg')"
              />
            </v-avatar>
           <v-list-item-title>Jung Won</v-list-item-title>
    </v-list-item>
    </v-navigation-drawer> -->
    <router-link :to="{name:'recruitmain'}">
      <v-img :src="getImgUrl('logoo.png')" style="width :6.5rem" />
    </router-link>
    <div class="page-router">
      <v-btn
        @click="goRecruitCalanderPage()"
        class="btn-underline hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text v-size--default"
        style="min-width: 48px;"
        text
        color="white"
      >
        <span class="highlight">채용 공고</span>
      </v-btn>
      <v-btn
        @click="goStudyPage()"
        class="btn-underline hidden-sm-and-down v-btn v-btn--flat v-btn--router v-btn--text v-size--default"
        style="min-width: 48px;"
        text
        color="white"
      >
        <span class="highlight">스터디</span>
      </v-btn>
    </div>
    <v-spacer></v-spacer>
    <div class="searchbar mt-7 mr-0 mr-md-6 hidden-xs-only" style="max-width: 300px;">
      <v-text-field
        outlined
        label="채용 공고 검색"
        @keyup.enter="goSearch(researchValue)"
        dense="true"
        color="teal"
        v-model="researchValue"
      >
      </v-text-field>
    </div>
    <div class="user">

    </div>
    <v-btn
      v-if="isMr()==='0'"
      class="btn-colored v-btn v-btn--flat v-btn--text theme--dark v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
      @click="GoManagePage"
    >
      <span class="text-colored">페이지 관리</span>
    </v-btn>
    <v-btn
      v-if="isLogin"
      class="mx-3 btn-border-colored v-btn v-btn--flat v-btn--text v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
      @click="goMypage"
      outlined
      color="teal"
    >
      <span class="text-border-colored">마이페이지</span>
    </v-btn>
    <v-btn
      v-if="isLogin"
      @click="goLogout"
      @click.stop="dialog =!dialog"
      class="btn-colored v-btn v-btn--flat v-btn--text theme--dark v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
      depressed 
    >
      <span class="text-colored">로그아웃</span>
    </v-btn>
    <v-btn
      v-else
      @click.stop="dialog=!dialog"
      @click="goLogin()"
      class="btn-colored v-btn v-btn--flat v-btn--text v-size--default"
      aria-label="Support"
      style="min-width: 48px;"
    >
      <span class="text-colored">로그인/회원가입</span>
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
    <v-navigation-drawer 
      v-model="drawer" 
      absolute
      temporary>
      <v-list
        nav
        dense
      >
      <v-list-item >
        <v-list-item-avatar>
          
            <img src="https://randomuser.me/api/portraits/men/81.jpg">
              <!-- <v-img 
                  v-if="this.imageResult===null"
                  src="../../assets/noimg.png"
                  dark="dark"></v-img>
              <v-img v-else
              :src= this.imageResult
              dark="dark"></v-img> -->
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>김정원님!</v-list-item-title>
              <v-list-item-subtitle>환영합니다.</v-list-item-subtitle>
            </v-list-item-content>
           </v-list-item>
          <v-divider></v-divider>
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
            <v-spacer></v-spacer>

          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Account</v-list-item-title>
          </v-list-item>
         </v-list>
    </v-navigation-drawer>
     </v-card>
      <!-- <v-list>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list> -->
    
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
      imageResult:false,
      drawer:false,
      content:null,
      chk: true,
      email: null,
      password: null,
      dialog: false,
      token: localStorage.getItem("access_token"),
      isLoginFlag: false,
      researchValue:""
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
       router.push({ name: "searchresult", query:{cont: content}})
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

<style>
.highlight {
    display: inline;
    position:relative;
    overflow: hidden;
    color: #070103;
    /* font-size: 150%; */
}
.highlight span {
    position:relative;
    z-index: -1;
}
.highlight::before {
    content:'';
    display: block;
    width:100%;
    height: 50%;
    background: rgba(0, 170, 179, 0.5);
    position: absolute;
    bottom:0;
    left:-100%;
    transition: all .35s;
}
.btn-underline:hover .highlight::before {
    left: 0;
}

.btn-underline {
  color: none;
}
.btn-colored {
  background:rgba(0, 170, 179);
}
.btn-border-colored {
  border: rgba(0, 170, 179);
}
.text-colored {
  color: white;
  /* font-size: 150%; */

}
.text-border-colored {
  color: rgba(0, 170, 179);
}
/* .text-colored2 {
  color: #070103;
  font-size: 150%;
}
.btn-colored2:hover {
  background:rgba(0, 170, 179, 0.5);
} */

/* .search-input {
  border-color: grey;
} */
</style>