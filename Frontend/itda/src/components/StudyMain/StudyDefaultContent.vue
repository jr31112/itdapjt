<template>
  <v-row class="studydefaultcontent">

  </v-row>
</template>

<script>
export default {
    name:"studydefaultContent",
    props:{
        options:{type:Object},
        allstudy:{type:Array}
    },
    data(){
        return{
            studies:[]
        }
    },
    watch:{
        options:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        }
    },
    methods:{
        updateOptions(){
            const tmp = []
            for (var i=0; i<this.allstudy.length;i++){
                if(this.options.category1 == 0){
                    if (this.options.category1 == 0){
                        console.log(this.allstudy[i].stname.includes(this.options.keyword))
                        // console.log(this.allstudy[i].stname.includes(this.options.keword))
                        if(this.allstudy[i].stname.includes(this.options.keyword)){
                            tmp.push(this.allstudy[i])
                        }
                    }
                }
            }
            this.studies = tmp
            console.log(tmp)
            console.log(this.studies)
        }
    }
}
</script>

<style>

</style>