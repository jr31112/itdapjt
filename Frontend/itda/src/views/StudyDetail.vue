<template>
  <div class="studydetail">
      {{study}}
      {{person}}
  </div>
</template>

<script>
import axios from 'axios'

export default {
    name:"stduydetail",
    data(){
        return{
            person:[],
            study:{}
        }
    },
    methods:{
        getStudy(){
            axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudy/'+this.$route.params.id)
                .then(response => {
                    this.study = response.data
                })
        },
        getPerson(){
            axios.get('https://i02b201.p.ssafy.io:8197/itda/api/getStudyGroup/'+this.$route.params.id)
              .then(response => {
                  this.person = response.data
          })
        },
    },
    mounted(){
        this.getStudy()
        this.getPerson()
    },
}
</script>

<style>

</style>