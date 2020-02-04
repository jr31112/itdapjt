<template>
    <div class="recruitmain">
        <ImgBanner :imgSrc="getImgUrl('333.jpg')">
            <div
                class="font-weight-black text-center"
                style="padding-left :50px; line-height:1.2em; font-size: calc((80vw / 1240) * 40) "
                slot="text">Just Do it! you'll just win.</div>
        </ImgBanner>
        <v-container>
            <recruit-recent-list/>
            <recruit-login-content v-if="isLogin"/>
            <recruit-default-content />
            <recruit-study-list/>
        </v-container>
    </div>
</template>
<script>
    import RecruitLoginContent from '../components/RecruitMain/RecruitLoginContent.vue'
    import RecruitDefaultContent from '../components/RecruitMain/RecruitDefaultContent.vue'
    import RecruitRecentList from '../components/RecruitMain/RecruitRecentList.vue'
    import RecruitStudyList from '../components/RecruitMain/RecruitStudyList.vue'
    import ImgBanner from '../components/RecruitMain/ImgBanner.vue'
    import { mapState } from 'vuex'
    
    export default {
        name: 'recruitmain',
        components: {
            RecruitLoginContent,
            RecruitDefaultContent,
            RecruitRecentList,
            RecruitStudyList,
            ImgBanner,
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