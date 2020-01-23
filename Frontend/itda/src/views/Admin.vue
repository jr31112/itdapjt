<template>
  <div class="admin">
    <v-container>
      <h2>공고 관리</h2>
      <v-row class="mb-4">
        <v-col><admin-wanted/></v-col>
      </v-row>
      <h2>유저 관리</h2>
      <v-row class="mb-4">
        <v-col><admin-user/></v-col>
      </v-row>
      <h2>요청 관리</h2>
      <v-row class="mb-4">
        <v-col><admin-request/></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import AdminWanted from '../components/AdminWanted.vue'
import AdminUser from '../components/AdminUser.vue'
import AdminRequest from '../components/AdminRequest.vue'

export default {
  name:"admin",
  components:{
    AdminWanted,
    AdminUser,
    AdminRequest
  }
}
</script>

<style>

</style>