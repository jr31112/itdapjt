<template>
    <v-app>
        <v-app-bar app>
            <NaviBar></NaviBar>
        </v-app-bar>
        <v-content>
        <router-view path="/"></router-view>
        </v-content>
        <Footer></Footer>
    </v-app>
</template>

<script>
    import Store from './store/index.js'
    import NaviBar from "./components/NaviBar.vue"
    import Footer from "./components/Footer.vue"

    export default {
        name: 'App',
        components: {
            NaviBar,
            Footer
        },
        Store
    };
</script>