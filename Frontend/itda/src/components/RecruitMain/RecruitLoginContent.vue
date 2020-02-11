<template>
  <v-row class="wantedlogin">
    <recruit-stack-list :stackList="loginContent.stackList"/>
    <recruit-scrap-list :scrapList="loginContent.scrapList"/>
  </v-row>
</template>

<script>
import RecruitStackList from './RecruitLoginContent/RecruitStackList.vue'
import RecruitScrapList from './RecruitLoginContent/RecruitScrapList.vue'

export default {
    name: "recruitlogincontent",
    props:{
      loginContent:{type:Object}
    },
    components:{
        RecruitStackList,
        RecruitScrapList
    }
}
</script>

<style>

</style>