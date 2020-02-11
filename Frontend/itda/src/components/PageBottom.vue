<template>
    <v-footer app="app">
        <v-spacer></v-spacer>
        <v-btn
            @click="onMove('https://www.jobplanet.co.kr/')"
            target="_blank"
            text="text">
            <v-avatar size="25px" class="mx-1">
                <v-img :src="getImgUrl('job.png')"/>
            </v-avatar>

            <span class="jw">JoB Planet</span>
        </v-btn>    
        <v-btn
            @click="onMove('https://www.saramin.co.kr/')"
            target="_blank"
            text="text">
            <v-avatar size="27px" class="mx-1">
                <v-img :src="getImgUrl('saramin.png')"/>
            </v-avatar>

            <span class="jw">SaramIn</span>
        </v-btn>
        <v-btn
            @click="onMove('https://www.jobkorea.co.kr/')"
            target="_blank"
            text="text">
            <v-avatar size="27px" class="mx-1">
                <v-img :src="getImgUrl('korea.png')"/>
            </v-avatar>
            <span class="jw">Job Korea</span>
        </v-btn>

        <div>
            <v-avatar size="45px" class="mx-1">
                <v-img :src="getImgUrl('itda1.png')"/>
            </v-avatar>
            &copy;
            {{new Date().getFullYear()}}</div>
    </v-footer>
</template>
<script>
    export default {
        methods: {
            getImgUrl(img) {
                return require("../assets/" + img);
            },
            onMove(text) {
                window.open(text, "_blank");
            }
        }
    };
</script>
<style >
    .jw{
        padding: 5px;
        display: inline;
    }

    @media only screen and (max-width: 400px) {
        .jw{
            display: none;
        }
    }
</style>