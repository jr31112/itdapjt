<template>
  <v-data-iterator :items="this.recruits" :page="page" hide-default-footer class="mx-auto">
    <v-row>
        <v-simple-table class="mx-auto" fixed-header>
            <thead class="px-auto">
                <tr>
                    <th class="text-center">회사명</th>
                    <th class="text-center">공고명</th>
                    <th class="text-center">기술스택</th>
                    <th class="text-center">스크랩해제</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="recruit in recruits.slice(10*(page-1), 10*(page-1) + 10)" :key="recruit.wanted.wid">
                    <td class="text-center">{{recruit.company.corpNm}}</td>
                    <td class="text-center">{{recruit.wanted.wantedTitle}}</td>
                    <td class="text-center" v-if="recruit.stacks"><span v-for="stack in recruit.stacks" :key="stack.sid">{{stack.tname}} </span></td>
                    <td class="text-center" v-else></td>
                    <td class="text-center"><v-icon color="yellow" @click.prevent="doScrap(recruit.wanted.wid)">star</v-icon></td>
                </tr>
            </tbody>
        </v-simple-table>
    </v-row>
    <v-row class="mt-2" align="center" justify="center">
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
    </v-row>
    
  </v-data-iterator>
</template>

<script>
    import axios from "axios"
    
    export default {
        name: "userscraplist",
        data() {
            return {
                idx:0,
                page:1,
                pagenums:[],
            }
        },
        props: {
            recruits: {type: Array}
        },
        watch:{
            recruits:{
                deep:true,
                immediate:true,
                handler(){
                    this.calPageindex(this.recruits)
                }
            },
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
            doScrap(wid){
                axios.post('http://192.168.31.54:8197/itda/api/scrapWanted/',{"wid":wid},{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
                    .then(()=>{this.$emit('update')})
            },
        },
        computed:{
            numberOfPages () {
                var wanted = this.recruits
                return Math.ceil(wanted.length / 10)
            },
        },
    }


</script>

<style></style>