<template>
    <v-row class="mx-auto">
        <v-data-iterator :items="studies" :page="page" hide-default-footer class="mx-auto" v-if="studies.length">
            <v-row>
                <v-simple-table class="mx-auto" fixed-header>
                    <thead class="px-auto">
                        <tr>
                            <th class="text-center">번호</th>
                            <th class="text-center" id="response">스터디 종류</th>
                            <th class="text-center">스터디 주제</th>
                            <th class="text-center" id="response">스터디 내용</th>
                            <th class="text-left" style="width:30vw">스터디 이름</th>
                            <th class="text-center">인원</th>
                        </tr>
                    </thead>
                    <tbody>
                        <study-detail v-for="study in studies.slice(10*(page-1), 10*(page-1) + 10)" :key="study.stid" :study="study" v-on:update="update"/>
                    </tbody>
                </v-simple-table>
                
            </v-row>
            <v-row class="mt-2" align="center" justify="center">
                <v-btn color="blue lighten-1" class="mr-1" @click="formerPage">
                    <v-icon>mdi-chevron-left</v-icon>
                </v-btn>
                <v-btn v-for="pgNm in this.numberOfPages" :key="pgNm" color="blue lighten-3" class="ml-1" @click="goPage(pgNm)">
                    {{pgNm}}
                </v-btn>
                <v-btn color="blue lighten-1" class="ml-1" @click="nextPage">
                    <v-icon>mdi-chevron-right</v-icon>
                </v-btn>
            </v-row>
        </v-data-iterator>
        <v-row v-else>
            <v-col style="text-align:center">
                찾는 데이터가 없어요...ㅠ
            </v-col>
        </v-row>
    </v-row>
</template>

<script>
import StudyDetail from './StudyDefaultContent/StudyDetail.vue'
export default {
    name:"studydefaultContent",
    props:{
        options:{type:Object},
        allstudy:{type:Array},
    },
    components:{
        StudyDetail,
    },
    data(){
        return{
            page:1,
            overlay:true,
            studies:[],
        }
    },
    watch:{
        allstudy:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        },
        options:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        },
    },
    methods:{
        update(){
            this.$emit('update')
        },
        updateOptions(){
            const tmp = []
            for (var i=0; i<this.allstudy.length;i++){
                if(this.options.category1 == 0){
                    if (this.options.category2 == 0){
                        if(this.allstudy[i].stname.includes(this.options.keyword)||this.allstudy[i].typeName.includes(this.options.keyword))
                            tmp.push(this.allstudy[i])
                    }
                    else{
                        if(this.allstudy[i].sgroup == this.options.category2 && (this.allstudy[i].stname.includes(this.options.keyword)||this.allstudy[i].typeName.includes(this.options.keyword)))
                            tmp.push(this.allstudy[i])
                    }
                }
                else{
                    if (this.options.category2 == 0){
                        if(this.allstudy[i].stype == this.options.category1 && (this.allstudy[i].stname.includes(this.options.keyword)||this.allstudy[i].typeName.includes(this.options.keyword)))
                            tmp.push(this.allstudy[i])
                    }
                    else{
                        if(this.allstudy[i].stype == this.options.category1 && this.allstudy[i].sgroup == this.options.category2 && this.allstudy[i].stname.includes(this.options.keyword))
                            tmp.push(this.allstudy[i])
                    }
                }
            }
            this.studies = tmp
            this.len = tmp.length
        },
        nextPage () {
        if (this.page + 1 <= this.numberOfPages) this.page += 1
        },
        formerPage () {
            if (this.page - 1 >= 1) this.page -= 1
        },
        goPage(num) {
            this.page = num
        },
    },
    computed: {
      numberOfPages () {
        return Math.ceil(this.studies.length / 10)
      },
    },
}
</script>

<style>
@media only screen and (max-width: 1030px){
    #response{
        display: none;
    }
}
</style>