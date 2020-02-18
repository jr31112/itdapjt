<template>
    <v-container fill-height="fill-height">
        <v-layout align-center="align-center">
            <v-flex>
                <h3 class="display-3">Page Not Found</h3>
                <span class="subheading">Lorem ipsum dolor sit amet, pri veniam forensibus id.
                    Vis maluisset molestiae id, ad semper lobortis cum. At impetus detraxit
                    incorrupte usu, repudiare assueverit ex eum, ne nam essent vocent admodum.</span>
                <v-divider class="my-3"></v-divider>
                <div class="title mb-3">Check out our newest features!</div>
                <v-btn
                    large="large"
                    color="primary"
                    class="mx-0 mr-3"
                    router="router"
                    :to="{name: 'home'}">
                    home</v-btn>
                <v-btn large="large" color="primary" class="mx-0" @click="$router.go(-1)">Back</v-btn>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
    export default {
        name: "notfound",
        data() {
            return {
              image:'',
              check:false,
              formData:{
                    images:''
                }

            }
        },
        methods: {
            getImgSrc()
            {
                return this.imageResult
            }
           
        }
    }
</script>
<style>
    div#wrapper {
        margin-left: 500px;
    }
</style>