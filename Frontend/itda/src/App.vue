<template>
    <v-app>
        <page-top />
        <v-content>
        <router-view path="/"></router-view>
        </v-content>
        <page-bottom />
    </v-app>
</template>

<script>
    import PageTop from "./components/PageTop.vue"
    import PageBottom from "./components/PageBottom.vue"
    import { mapState } from "vuex"
    export default {
        name: 'App',
        components: {
            PageTop,
            PageBottom
        },
        computed: {
		...mapState(["isLogin","isManager"])
        }
        
    };
</script>