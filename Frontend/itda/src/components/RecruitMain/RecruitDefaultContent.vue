<template>
  <v-row class="wanteddefault">
      <recruit-deadline-list/>
      <recruit-popular-list/>
  </v-row>
</template>

<script>
import RecruitDeadlineList from './RecruitDefaultContent/RecruitDeadlineList.vue'
import RecruitPopularList from './RecruitDefaultContent/RecruitPopularList.vue'

export default {
    name: "recruitdefaultcontent",
    components:{
        RecruitDeadlineList,
        RecruitPopularList
    }
}
</script>

<style>

</style>