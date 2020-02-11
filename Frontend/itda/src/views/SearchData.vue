<template>
  <div>
      <search-data-filter :options="options"/>
      <search-data-content :options="options" :all="searchresults"  />
      <!-- 자식으로 부터 받아와서 그걸 보내줘야한다.  -->
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
                axios.get('http://54.180.140.163/itda/api/getWantedAll')
                .then((response)=>{
                        this.searchresults = response.data
                    })
                .catch()
            }
            else if(type == 2){
                axios.get('http://54.180.140.163/itda/api/getAllCompanys')
                    .then((response)=>{
                            this.searchresults = response.data
                        })
                    .catch()
            }
            else if (type==3){
                axios.get('http://54.180.140.163/itda/api/getAllStacks')
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
        console.log(this.options.keyword)
        // localStorage.removeItem('pre')
    },
}
</script>

<style>

</style>