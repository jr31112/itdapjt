<template>
  <v-row class="wantedlogin">
    <recruit-stack-list/>
    <recruit-scrap-list/>
  </v-row>
</template>

<script>
import RecruitStackList from './RecruitLoginContent/RecruitStackList.vue'
import RecruitScrapList from './RecruitLoginContent/RecruitScrapList.vue'

export default {
    name: "recruitlogincontent",
    components:{
        RecruitStackList,
        RecruitScrapList
    }
}
</script>

<style>

</style>