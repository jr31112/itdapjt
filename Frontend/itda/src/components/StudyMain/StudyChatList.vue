<template>
    <div>

        <!-- <v-card class="pa-3 mb-1" pd-3="pd-3" v-for="list in listAdd" :key="list">   --
        </v-card> -->
    </div>
</template>
<script>
    export default {
        name: "StudyChatList",
        props: {
            listAdd: Array
        },
        //부모로부터 받아옴.
        created() {
            this.getal()
        },

        methods: {
            getal() {
             console.log(this.listAdd)  
            }
        }

    }
</script>