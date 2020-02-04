<template>
    <v-container>
        <p>지난 공고</p>   
        
        <ul>
            <li v-for="item in scraplist" :key="item">{{item}}</li>
        </ul>
    </v-container>
</template>

<script>
    export default {
        name: "userstacklist",
        data() {
            return {scraplist: []}
        },
        mounted() {
            this.setScrapList()
        },
        methods: {
            setScrapList(){
                this.scraplist.push("삼성")
                this.scraplist.push("현대")
                this.scraplist.push("sk")
                this.scraplist.push("LG")
                this.scraplist.push("롯데")
                this.scraplist.push("신한")
                this.scraplist.push("멀캠")
                this.scraplist.push("네이버")
                this.scraplist.push("카카오")
            }
        }

    }
</script>

<style></style>