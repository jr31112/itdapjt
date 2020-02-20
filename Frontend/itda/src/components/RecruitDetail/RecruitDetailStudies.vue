<template>
    <v-container>
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
                            <study-detail v-for="study in studies.slice(10*(page-1), 10*(page-1) + 10)" :key="study.stid" :study="study"/>
                        </tbody>
                    </v-simple-table>
                </v-row>
                <v-row class="mt-2" align="center" justify="center" >
                    <v-btn text="text" class="mr-1 " @click="formerPage">
                        <v-icon>mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-btn  v-for="pgNm in this.numberOfPages" :key="pgNm" text class="ml-1" @click="goPage(pgNm)">
                        {{pgNm}}
                    </v-btn>
                    <v-btn  text="text" class="ml-1" @click="nextPage">
                        <v-icon>mdi-chevron-right</v-icon>
                    </v-btn>
                </v-row>
            </v-data-iterator>
            <v-row v-else>
                <v-col style="text-align:center">
                    스터디가 존재하지 않아요!
                </v-col>
            </v-row>
        </v-row>
    </v-container>
</template>

<script>
import StudyDetail from "../StudyMain/StudyDefaultContent/StudyDetail.vue"

export default {
    name:"recruitdetailstudies",
    props:{
        studies:{type:Array}
    },
    components:{
        StudyDetail
    },
    data(){
        return{
            page:1,
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