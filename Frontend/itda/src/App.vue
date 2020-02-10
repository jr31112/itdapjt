<template>
    <v-app>
        <page-top v-if="!ispopup"/>
        <v-content>
        <router-view path="/"></router-view>
        </v-content>
        <page-bottom v-if="!ispopup"/>
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
            ispopup(){
                if(this.$route.name == "searchdata")
                    return true
                else
                    return false
            },
		...mapState(["isLogin","isManager"])
        }
        
    };
</script>

<style lang="scss">
  // Import Main styles for this application
  @import 'assets/scss/style';
</style>