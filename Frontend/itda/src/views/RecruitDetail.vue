<template>
  <div class="recruitdetail">
    <v-container>
      <company-info/>
      <wanted-info/>
      <wanted-comment/>
    </v-container>
  </div>
</template>

<script>
import CompanyInfo from '../components/CompanyInfo.vue'
import WantedInfo from '../components/WantedInfo.vue'
import WantedComment from '../components/WantedComment.vue'


export default {
    name: 'RecruitDetail',
    components: {
    CompanyInfo,
    WantedInfo,
    WantedComment
  }
}
</script>

<style>

</style>