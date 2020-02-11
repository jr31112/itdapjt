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

</template>

<script>
export default {
    name:'searchdatacontent',
    data(){
        return{
            page:1,
            searchresults:[]
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
            handler:'updateOptions'
        },
        options:{
            deep:true,
            immediate:true,
            handler:'updateOptions'
        }
    },
    methods:{
        nextPage () {
        if (this.page + 1 <= this.numberOfPages) this.page += 1
        },
        formerPage () {
            if (this.page - 1 >= 1) this.page -= 1
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
        }
    },
    computed:{
        numberOfPages () {
            return Math.ceil(this.searchresults.length / 10)
        },
    }
}
</script>

<style>

</style>