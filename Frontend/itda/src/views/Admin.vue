<template>
  <div class="admin">
    <v-container pt-9>
      <h2>공고 관리</h2>
      <v-row class="mb-4">
        <v-col><admin-recruit/></v-col>
      </v-row>
      <h2>유저 관리 </h2>
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
import AdminRecruit from '../components/Admin/AdminRecruit.vue' 
import AdminUser from '../components/Admin/AdminUser.vue'
import AdminRequest from '../components/Admin/AdminRequest.vue'

export default {
  name:"admin",
  components:{
    AdminRecruit,
    AdminUser,
    AdminRequest
  }
}
</script>

<style>

</style>