<template>
    <v-app>
        <v-overlay :value="overlay">
        <v-progress-circular indeterminate size="64"></v-progress-circular>
         </v-overlay>
        <page-top v-if="!ispopup" />
        <v-content>
        <router-view path="/"></router-view>
        </v-content>
        <page-bottom v-if="!ispopup"/>
    </v-app>
</template>

<script>
    import axios from "axios"
    import PageTop from "./components/PageTop.vue"
    import PageBottom from "./components/PageBottom.vue"
    import { mapState } from "vuex"
    export default {
        name: 'App',
        components: {
            PageTop,
            PageBottom
        },
        data(){
            return {
                overlay : false      
            }
        },
        computed: {
            ispopup(){
                if(this.$route.name == "searchdata")
                    return true
                else
                    return false
            },
        ...mapState(["isLogin"]),
        },
        mounted(){
            this. overlay = true
            axios.get(`https://i02b201.p.ssafy.io:8197/itda/api/getWantedAll/`)
                .then(response => {
                    this. overlay = false
                    this.$store.commit("setAllRecruit", response)
                    this.overlay = false
                })
        }
    };
</script>

<style lang="scss">
  // Import Main styles for this application
  @import 'assets/scss/style';
</style>