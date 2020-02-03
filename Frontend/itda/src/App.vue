<template>
    <v-app>
        <Header/>
        <v-content>
        <router-view path="/"></router-view>
        </v-content>
        <Footer/>
    </v-app>
</template>

<script>
    import Header from "./components/Header.vue"
    import Footer from "./components/Footer.vue"
    import { mapState } from "vuex"
    export default {
        name: 'App',
        components: {
            Header,
            Footer
        },
        computed: {
		...mapState(["isLogin"])
        }
        
    };
</script>