<template>
  <v-row>
      <v-col>
          <v-tabs v-model="tab" background-color="primary" :right="true">
            <v-tab v-for="recruit in recruits" :key="recruit.tab">{{recruit.title}}</v-tab>
          </v-tabs>
          <v-tabs-items v-model="tab">
            <v-tab-item v-for="recruit in recruits" :key="recruit.tab">
                <v-simple-table class="mx-auto" fixed-header>
                    <thead class="px-auto">
                        <tr>
                            <th class="text-center">회사명</th>
                            <th class="text-center">공고명</th>
                            <th class="text-center">시작일</th>
                            <th class="text-center">마감일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="part in recruit.searchResult" :key="part.wanted.cid">
                            <td class="text-center">{{part.company.corpNm}}</td>
                            <td class="text-center">{{part.wanted.wantedTitle}}</td>
                            <td class="text-center">{{part.wanted.startDate}}</td>
                            <td class="text-center">{{part.wanted.endDate}}</td>
                        </tr>
                    </tbody>
                </v-simple-table>
                <!-- <infinite-loading @infinite="infiniteHandler"/> -->
            </v-tab-item>
          </v-tabs-items>
      </v-col>
  </v-row>
</template>

<script>
// import InfiniteLoading from 'vue-infinite-loading'

export default {
    name:'searchresultcontent',
    components:{
        // InfiniteLoading,
    },
    props:{
        options:{type:Object, required:true},
        allContent:{type:Array, required:true},
    },
    data(){
        return{
            tab:1,
            page:{1:1, 2:1},
            recruits:[
                {tab:1, title:"진행중인 공고", searchResult:[]},
                {tab:2, title:"마감된 공고", searchResults:[]}
            ],
            savedata:{
                1:[],
                2:[]
            }
        }
    },
    methods:{
        updateOptions(){
            const presenttmp = []
            const expiredtmp = []
            var today = new Date()
            for (var i=0; i<this.allContent.length;i++){
                var endday = new Date(this.allContent[i].wanted.endDate)
                var datediff = parseInt((endday-today)/(24*60*60*1000))
                if (datediff >= 0){
                    if (this.options.type == 0){
                        flag = false
                        for (j=0; j<this.allContent[i].stacks.length;j++){
                            if (this.allContent[i].stacks[j].tname.toLowerCase().includes(this.options.keyword.toLowerCase())){
                                flag = true
                                break
                            }
                        }
                        if (flag || this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            presenttmp.push(this.allContent[i])
                    }
                    else if (this.options.type == 1){
                        if (this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            presenttmp.push(this.allContent[i])
                    }
                    else{
                        flag = false
                        for (j=0; j<this.allContent[i].stacks.length;j++){
                            if (this.allContent[i].stacks[j].tname.toLowerCase().includes(this.options.keyword.toLowerCase())){
                                flag = true
                                break
                            }
                        }
                        if (flag)
                            presenttmp.push(this.allContent[i])
                    }
                }
                    
                else if(this.$route.params.type == 2 && this.all[i].corpNm.toLowerCase().includes(this.options.keyword.toLowerCase())){
                    if (this.options.type == 0){
                        flag = false
                        for (var j=0; j<this.allContent[i].stacks.length;j++){
                            if (this.allContent[i].stacks[j].tname.toLowerCase().includes(this.options.keyword.toLowerCase())){
                                flag = true
                                break
                            }
                        }
                        if (flag || this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            expiredtmp.push(this.allContent[i])
                    }
                    else if (this.options.type == 1){
                        if (this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            expiredtmp.push(this.allContent[i])
                    }
                    else{
                        var flag = false
                        for (j=0; j<this.allContent[i].stacks.length;j++){
                            if (this.allContent[i].stacks[j].tname.toLowerCase().includes(this.options.keyword.toLowerCase())){
                                flag = true
                                break
                            }
                        }
                        if (flag)
                            expiredtmp.push(this.allContent[i])
                    }
                }
            }
            this.savedata[1] = presenttmp
            this.savedata[2] = expiredtmp
        }
    },
    watch:{
        options:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        }
    },
    mounted(){
        this.updateOptions()
    }
}
</script>

<style>

</style>