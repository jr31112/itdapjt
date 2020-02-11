<template>
  <v-row class="wanteddefault">
      <recruit-deadline-list :deadlineList="defaultContent.deadlineList"/>
      <recruit-popular-list :popularList="defaultContent.popularList"/>
  </v-row>
</template>

<script>
import RecruitDeadlineList from './RecruitDefaultContent/RecruitDeadlineList.vue'
import RecruitPopularList from './RecruitDefaultContent/RecruitPopularList.vue'

export default {
    name: "recruitdefaultcontent",
    props:{
        defaultContent:{type:Object}
    },
    components:{
        RecruitDeadlineList,
        RecruitPopularList
    }
}
</script>

<style>

</style>