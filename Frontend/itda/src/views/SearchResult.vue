<template>
  <div class="searchresult">
    <v-overlay :value="overlay">
        <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
    <v-container class="my-0">    
        <search-filter :options="options"/>
        <search-result-content :options="options" :allContent="allContent"/>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import SearchFilter from '../components/SearchResult/SearchFilter.vue'
import SearchResultContent from '../components/SearchResult/SearchResultContent.vue'

export default {
    name:'searchresult',
    components:{
        SearchFilter,
        SearchResultContent
    },
    data(){
        return{
            allContent:[],
            options:{
                type: 0,
                keyword: '',
            },
            overlay:false
        }
    },
    methods: {        
        getWantedList() {
            this.overlay = true
            axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getWantedAll')
                .then(response => {
                    this.allContent = response.data
                    this.overlay = false
                })
        },
        getKeyword(){
            if (this.$route.query.cont)
                this.options.keyword = this.$route.query.cont
            if (this.$route.query.type)
                this.options.type = this.$route.query.type
        }
    },
    mounted(){
        this.getWantedList()
    },
    watch:{
       $route:{
            deep:true,
            immediate:true,
            handler:'getKeyword'
        }, 
    }
}
</script>

<style>

</style>