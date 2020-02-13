<template>
    <div class="admin">
        <v-container>
            <h2>공고 관리</h2>
            <v-row class="mb-4">
                <v-col><admin-recruit/></v-col>
            </v-row>
            <h2>유저 관리</h2>
            <v-row class="mb-4">
                <v-col><admin-user/></v-col>
            </v-row>
            <h2>스터디 관리</h2>
            <v-row class="mb-4">
                <v-col><admin-study/></v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import AdminRecruit from '../components/Admin/AdminRecruit.vue'
    import AdminUser from '../components/Admin/AdminUser.vue'
    import AdminStudy from '../components/Admin/AdminStudy.vue'
    export default {
        name: "admin",
        components: {
            AdminRecruit,
            AdminUser,
            AdminStudy
        }
    }
</script>

<style></style>