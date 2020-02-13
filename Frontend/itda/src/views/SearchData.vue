<template>
  <div>
      <search-data-filter :options="options"/>
      <search-data-content :options="options" :all="searchresults"/>
  </div>
</template>

<script>
import axios from 'axios'
import SearchDataContent from "../components/SearchData/SearchDataContent.vue"
import SearchDataFilter from "../components/SearchData/SearchDataFilter.vue"

export default {
    name:"searchdata",
    components:{
        SearchDataContent,
        SearchDataFilter
    },
    data(){
        return{
            options:{
                keyword : null,
            },
            searchresults:[],
        }
    },
    methods:{
       
        getData(){
            var type = this.$route.params.type
            if (type == 1){
                axios.get('http://192.168.31.54:8197/itda/api/getWantedAll')
                .then((response)=>{
                        this.searchresults = response.data
                    })
                .catch()
            }
            else if(type == 2){
                axios.get('http://192.168.31.54:8197/itda/api/getAllCompanys')
                    .then((response)=>{
                            this.searchresults = response.data
                        })
                    .catch()
            }
            else if (type==3){
                axios.get('http://192.168.31.54:8197/itda/api/getAllStacks')
                    .then((response)=>{
                            this.searchresults = response.data
                        })
                    .catch()
            }
        },
        
    },
    mounted(){
        this.getData()
        this.options.keyword = localStorage.getItem('pre')
    },
}
</script>

<style>

</style>