<template>
  
  <v-data-iterator :items="searchresults" :page="page" hide-default-footer class="mx-auto">
    <v-row>
        <v-simple-table class="mx-auto" fixed-header>
            <thead class="px-auto">
                <tr>
                    <th class="text-center">번호</th>
                    <th class="text-center">이름</th>
                </tr>
            </thead>
            <tbody v-if="this.$route.params.type == 1 && this.searchresults.length">
                <tr v-for="part in searchresults.slice(10*(page-1), 10*(page-1) + 10)" :key="part.cid" @click="dataTr({id:part.wanted.wid, Nm:part.wanted.wantedTitle})">
                    <td class="text-center">{{part.wanted.wid}}</td>
                    <td class="text-center">{{part.wanted.wantedTitle}}</td>
                </tr>
            </tbody>
            <tbody v-if="this.$route.params.type == 2 && this.searchresults.length">
                <tr v-for="part in searchresults.slice(10*(page-1), 10*(page-1) + 10)" :key="part.cid" @click="dataTr({id:part.cid, Nm:part.corpNm})">
                    <td class="text-center">{{part.cid}}</td>
                    <td class="text-center">{{part.corpNm}}</td>
                </tr>    
            </tbody>
            
            <tbody v-if="this.$route.params.type == 3 && this.searchresults.length">
                <tr v-for="part in searchresults.slice(10*(page-1), 10*(page-1) + 10)" :key="part.cid" @click="dataTr({id:part.sid, Nm:part.tname})">
                    <td class="text-center">{{part.sid}}</td>
                    <td class="text-center">{{part.tname}}</td>
                </tr>
            </tbody>
        </v-simple-table>
    </v-row>
    <v-row  align="center" justify="center">
    <div class="text-center">
    <v-pagination
      v-model="page"
      color="rgba(0, 170, 179)"
      :length="4"
      prev-icon="mdi-menu-left"
      next-icon="mdi-menu-right"
    ></v-pagination>
     </div>
     </v-row>
    <!-- <v-row class="mt-2" align="center" justify="center">
        <v-btn v-if="this.idx*5 - 5 + this.page >= 1" color="blue lighten-1" class="mr-1" @click="doubleformerPage">
            <v-icon>arrow_back</v-icon>
        </v-btn>
        <v-btn v-if="this.page + this.idx*5 - 1 >= 1" color="blue lighten-1" class="mr-1" @click="formerPage">
            <v-icon>mdi-chevron-left</v-icon>
        </v-btn>
        <v-btn v-for="pgNm in this.pagenums" :key="pgNm" color="blue lighten-3" class="ml-1" @click="goPage(pgNm)">
            {{pgNm}}
        </v-btn>
        <v-btn v-if="this.page + this.idx*5 + 1 <= this.numberOfPages" color="blue lighten-1" class="ml-1" @click="nextPage">
            <v-icon>mdi-chevron-right</v-icon>
        </v-btn>
        <v-btn v-if="this.idx*5 + 5 + this.page <= this.numberOfPages" color="blue lighten-1" class="mr-1" @click="doublenextPage">
            <v-icon>arrow_forward</v-icon>
        </v-btn>
    </v-row> -->
  </v-data-iterator>
</template>

<script>
export default {
    name:'searchdatacontent',
    data(){
        return{
            idx:0,
            pagenums:[],
            page:1,
            searchresults:[],
            compNm: ""
        }
    },
    props:{
        options:{type:Object},
        all:{type:Array},
    },
    watch:{
        all:{
            deep:true,
            immediate:true,
            handler(){
                this.updateOptions()
                this.calPageindex()
                }
        },
        options:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        }
    },
    methods:{
        doublenextPage () {
        if (this.idx*5 + 5 + this.page <= this.numberOfPages) {
            this.idx += 1
            this.page = 1
            this.calPageindex()
            }
        },
        nextPage () {
        if (this.page + this.idx*5 + 1 <= this.numberOfPages) {
            this.page += 1
            if (this.page%5 == 1){
                this.page = 1
                this.idx += 1
                this.calPageindex()
                }
            }
        },
        doubleformerPage () {
            if (this.idx*5 - 5 + this.page >= 1) {
                this.idx -= 1
                this.page = 1
                this.calPageindex()
                }
        },
        formerPage () {
            if (this.page + this.idx*5 - 1 >= 1){
                this.page -= 1
                if (this.page%5 == 0){
                    this.page = 5
                    this.idx -= 1
                    this.calPageindex()
                }
            } 
        },
        goPage(num) {
            this.page = num
        },
        updateOptions(){
            const tmp = []
            for (var i=0; i<this.all.length;i++){
                if(this.$route.params.type == 1 && this.all[i].wanted.wantedTitle.toLowerCase().includes(this.options.keyword.toLowerCase()))
                    tmp.push(this.all[i])
                else if(this.$route.params.type == 2 && this.all[i].corpNm.toLowerCase().includes(this.options.keyword.toLowerCase()))
                    tmp.push(this.all[i])
                else if(this.$route.params.type == 3 && this.all[i].tname.toLowerCase().includes(this.options.keyword.toLowerCase()))
                    tmp.push(this.all[i])    
                }
            this.searchresults = tmp
        },
        dataTr(content){
            localStorage.setItem('select', JSON.stringify(content))
            window.close()
        },
        calPageindex (){
            var tmp = []
            for (var i=1; i<=5;i++ ){
                if (i + this.idx*5 <= this.numberOfPages)
                    tmp.push(i + this.idx*5)
                else
                    break
            }
            this.pagenums = tmp
        },
    },
    computed:{
        numberOfPages () {
            return Math.ceil(this.searchresults.length / 10)
        },
    },
}
</script>

<style>

</style>