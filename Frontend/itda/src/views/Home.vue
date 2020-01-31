<template>
    <div class="home">
        <ImgBanner :imgSrc="getImgUrl('333.jpg')">
            <div
                class="font-weight-black text-center"
                style="padding-left :50px; line-height:1.2em; font-size: calc((80vw / 1240) * 40) "
                slot="text">Just Do it! you'll just win.</div>
        </ImgBanner>
        <v-container>
            <recent-list/>
            <wanted-login v-if="isLogin"/>
            <wanted-default/>
        </v-container>
    </div>
</template>
<script>
    import RecentList from '../components/RecentList.vue'
    import WantedLogin from '../components/WantedLogin.vue'
    import WantedDefault from '../components/WantedDefault.vue'
    import ImgBanner from '../components/ImgBanner.vue'
import { mapState } from 'vuex'
    
    export default {
        name: 'home',
        components: {
            WantedLogin,
            WantedDefault,
            ImgBanner,
            RecentList
        },
        data(){
            return{
                recentlist:[]
            }
        },
        methods: {
            getImgUrl(img) {
                return require('../assets/' + img)
            },
        },
        computed: {
            ...mapState(["isLogin"])
        },
    }
</script>