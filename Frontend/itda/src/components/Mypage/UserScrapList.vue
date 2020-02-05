<template>
    <v-container>
        <p>스크랩한 공고</p>
         <ul>
            <li v-for="item in currentRecruits" :key="item">{{item}}</li>
        </ul>
    </v-container>
</template>

<script>
export default {
    name:"userscraplist",
    data(){return{
        currentRecruits:[]
    }},
    mounted(){
        this.setRecruit()
    },
    methods:{
        setRecruit(){
            this.currentRecruits.push("정원")
            this.currentRecruits.push("인성")
            this.currentRecruits.push("대래")
            this.currentRecruits.push("진희")
        }
    }
    
}
</script>

<style>

</style>