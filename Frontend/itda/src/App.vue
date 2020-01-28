<template>
    <v-app>
        <NaviBar></NaviBar>
        <v-content>
        <router-view path="/"></router-view>
        </v-content>
        <Footer></Footer>
    </v-app>
</template>

<script>
    import NaviBar from "./components/NaviBar.vue"
    import Footer from "./components/Footer.vue"
    import { mapState } from "vuex"
    export default {
        name: 'App',
        components: {
            NaviBar,
            Footer
        },
        computed: {
		...mapState(["isLogin"])
	    }
    };
</script>