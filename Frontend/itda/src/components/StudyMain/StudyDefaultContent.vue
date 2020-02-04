<template>
    <v-row class="mx-auto">
        <v-data-iterator :items="studies" :page="page" hide-default-footer class="mx-auto">
            <v-row>
                <v-simple-table class="mx-auto" fixed-header>
                    <thead class="px-auto">
                        <tr>
                            <th class="text-center">번호</th>
                            <th class="text-center">스터디 종류</th>
                            <th class="text-center">스터디 내용</th>
                            <th class="text-left" style="width:40vw">스터디 이름</th>
                            <th class="text-center">인원</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="study in studies.slice(10*(page-1), 10*(page-1) + 10)" :key="study.stid">
                            <td class="text-center">{{study.stid}}</td>
                            <td class="text-center">{{category1[study.stype-1]}}</td>
                            <td class="text-center">{{category2[study.sgroup-1]}}</td>
                            <td class="text-left">{{study.stname}}</td>
                            <td class="text-center">{{study.pcnt}}/{{study.maxPcnt}}</td>
                        </tr>
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
            category1:['공고 대비','기업 대비','기술 대비','기타 등등',],
            category2:['자소서 준비', '필기 준비', '면접 준비', '기타 등등',],
            page:1,
            studies:[]
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
        }
    },
    methods:{
        updateOptions(){
            const tmp = []
            for (var i=0; i<this.allstudy.length;i++){
                if(this.options.category1 == 0){
                    if (this.options.category2 == 0){
                        if(this.allstudy[i].stname.includes(this.options.keyword))
                            tmp.push(this.allstudy[i])
                    }
                    else{
                        if(this.allstudy[i].sgroup == this.options.category2 && this.allstudy[i].stname.includes(this.options.keyword))
                            tmp.push(this.allstudy[i])
                    }
                }
                else{
                    if (this.options.category2 == 0){
                        if(this.allstudy[i].stype == this.options.category1 && this.allstudy[i].stname.includes(this.options.keyword))
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

</style>