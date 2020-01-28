<template>
  <v-container class="detailjobs white py-0" >
      <v-row>
          <v-col v-for="i in jobs.length > len ? len: jobs.length" :key="i" cols="6" md="3">
            <detail-job-info :job="jobs[i-1]"/>
          </v-col>
      </v-row>
      <v-row v-if="boolload">
          <v-col>
            <v-btn color="info" dark v-on:click="loadMore"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>
          </v-col>
      </v-row>
  </v-container>
</template>

<script>
import DetailJobInfo from './DetailJobInfo.vue'

export default {
    name: 'detailjobs',
    props:{
        jobs:{type:Array}
    },
    components:{
        DetailJobInfo
    },
    data(){
        return{
            len: window.innerWidth>960?4:2,
            boolload:true
        }
    },
    methods:{
        findMax(){
            if (this.jobs.length <= this.len){
                this.len = this.jobs.length
                this.boolload = false
            }
        },
        loadMore() {
			this.len += window.innerWidth>960?4:2
			this.findMax()
		}
    },
    mounted(){
        this.findMax()
    }
}
</script>

<style>

</style>