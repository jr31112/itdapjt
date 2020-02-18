<template>
  <v-row>
      <v-overlay :value="overlay">
        <v-progress-circular indeterminate size="64"></v-progress-circular>
      </v-overlay>
      <v-col>
          <v-tabs v-model="tab" background-color="primary" :right="true">
            <v-tab v-for="recruit in recruits" :key="recruit.tab">{{recruit.title}}</v-tab>
          </v-tabs>
          <v-tabs-items v-model="tab">
            <v-tab-item v-for="recruit in recruits" :key="recruit.tab">
                <v-simple-table class="mx-auto" fixed-header v-if="recruit.searchResult.length">
                    <thead class="px-auto">
                        <tr>
                            <th class="text-center">회사명</th>
                            <th class="text-center">공고명</th>
                            <th class="text-center">시작일</th>
                            <th class="text-center">마감일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="part in recruit.searchResult" :key="part.wanted.wid" @click="goDetailPage(part.wanted.wid)">
                            <td class="text-center">{{part.company.corpNm}}</td>
                            <td class="text-center">{{part.wanted.wantedTitle}}</td>
                            <td class="text-center">{{part.wanted.startDate}}</td>
                            <td class="text-center">{{part.wanted.endDate}}</td>
                        </tr>
                    </tbody>
                </v-simple-table>
                <div v-else>
                    데이터가 없어요...ㅠ
                </div>
            </v-tab-item>
          </v-tabs-items>
      </v-col>
  </v-row>
</template>

<script>
import router from '../../router'
export default {
    name:'searchresultcontent',
    props:{
        options:{type:Object, required:true},
        allContent:{type:Array, required:true},
    },
    data(){
        return{
            tab:0,
            page:{1:1, 2:1},
            recruits:[
                {tab:1, title:"진행중인 공고", searchResult:[]},
                {tab:2, title:"마감된 공고", searchResult:[]}
            ],
            overlay:false
        }
    },
    methods:{
        goDetailPage(wid){
            router.push({name:'recruitdetail',params:{id:wid}})
	    },
        updateOptions(){
            const presenttmp = []
            const expiredtmp = []
            this.overlay = true
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
                        if (flag || this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()) || this.allContent[i].wanted.wantedTitle.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            presenttmp.push(this.allContent[i])
                    }
                    else if (this.options.type == 1){
                        if (this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            presenttmp.push(this.allContent[i])
                    }
                    else if (this.options.type == 2){
                        if (this.allContent[i].wanted.wantedTitle.toLowerCase().includes(this.options.keyword.toLowerCase()))
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
                        if (flag || this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()) || this.allContent[i].wanted.wantedTitle.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            expiredtmp.push(this.allContent[i])
                    }
                    else if (this.options.type == 1){
                        if (this.allContent[i].company.corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            expiredtmp.push(this.allContent[i])
                    }
                    else if (this.options.type == 2){
                        if (this.allContent[i].wanted.wantedTitle.toLowerCase().includes(this.options.keyword.toLowerCase()))
                            presenttmp.push(this.allContent[i])
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
            this.recruits[0].searchResult = presenttmp
            this.recruits[1].searchResult = expiredtmp
            setTimeout(() => {this.overlay = false}, 1000)
        },
    },
    watch:{
        options:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        },
        allContent:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        },
    },
    mounted(){
        this.updateOptions()
    }
}
</script>

<style>

</style>