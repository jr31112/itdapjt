<template>
    <v-footer>
        <v-spacer></v-spacer>

        <v-btn href="dart.fss.or.krt" target="_blank" text="text">
            <v-avatar size="25px" class="mx-1">
                <v-img :src="getImgUrl('job.png')"/>
            </v-avatar>

            <span class="mr">JoB Planet</span>
        </v-btn>

        <v-btn href="dart.fss.or.krt" target="_blank" text="text">
            <v-avatar size="27px" class="mx-1">
                <v-img :src="getImgUrl('saramin.png')"/>
            </v-avatar>

            <span class="mr">SaramIn</span>
        </v-btn>

        <v-btn href="dart.fss.or.krt" target="_blank" text="text">
            <v-avatar size="25px" class="mx-1">
                <v-img :src="getImgUrl('korea.png')"/>
            </v-avatar>

            <span class="mr">JoB Korea</span>
        </v-btn>

        <div>  
          <v-avatar size="45px" class="mx-1">
                <v-img :src="getImgUrl('itda1.png')" />
            </v-avatar> &copy;
            {{new Date().getFullYear()}}</div>
    </v-footer>
</template>
<script>
    export default {
        methods: {
            getImgUrl(img) {
                return require("../assets/" + img);
            }
        }
    };
</script>