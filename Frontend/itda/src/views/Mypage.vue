<template>
  <div style="padding: 80px 0px 0px 0px">
    <v-container>
      <h2>유저 정보 보기</h2>
      <v-row class="mb-4">
        <v-col>
          <user-detail/>
        </v-col>
      </v-row>
      <h2>유저가 스크랩한 공고 보기</h2>
      <v-row class="mb-4">
        <v-col>
          <user-recruit/>
        </v-col>
      </v-row>
      <h2>유저가 가입한 스터디 정보 보기</h2>
      <v-row class="mb-4">
        <v-col>
          <user-study/>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import UserDetail from "../components/Mypage/UserDetail.vue"
import UserStudy from "../components/Mypage/UserStudy.vue"
import UserRecruit from "../components/Mypage/UserRecruit.vue"
export default {
  name:"user",

  components:{
    UserDetail,
    UserRecruit,
    UserStudy
  },
}
</script>

<style>

</style>