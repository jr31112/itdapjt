<template>
  <div class="studymain">
      <v-container class="my-0" white>
          <study-filter :options="this.options"/>
          <study-default-content :options="this.options" :allstudy="this.studies"/>
      </v-container>
  </div>
</template>

<script>
import StudyFilter from '../components/StudyMain/StudyFilter'
import StudyDefaultContent from '../components/StudyMain/StudyDefaultContent'


export default {
    name:"studymain",
    components:{
        StudyFilter,
        StudyDefaultContent,
    },
    data(){
        return{
            options:{
                category1 : 0,
                category2 : 0,
                keyword: ""
            },
            studies:[
                // stid int primary key auto_increment,
                // stname varchar(100) not null,
                // maxPcnt int not null,
                // pcnt int not null default 1,
                // stype int not null,
                // typeName varchar(300),(공고명)
                // sgroup int not null,
                // content text not null,
                {stid:1, stname:"스터디1", maxPcnt:5, pcnt:1, stype:1, sgroup:4, context:"내용1", },
                {stid:2, stname:"스터디2", maxPcnt:8, pcnt:2, stype:2, sgroup:3, context:"내용2", },
                {stid:3, stname:"스터디3", maxPcnt:3, pcnt:1, stype:3, sgroup:2, context:"내용3", },
                {stid:4, stname:"스터디4", maxPcnt:2, pcnt:1, stype:4, sgroup:1, context:"내용4", },
                {stid:5, stname:"스터디5", maxPcnt:5, pcnt:3, stype:2, sgroup:4, context:"내용5", },
                {stid:6, stname:"스터디6", maxPcnt:5, pcnt:5, stype:3, sgroup:3, context:"내용6", },
                {stid:7, stname:"스터디7", maxPcnt:5, pcnt:4, stype:4, sgroup:2, context:"내용7", },
                {stid:8, stname:"스터디8", maxPcnt:6, pcnt:3, stype:1, sgroup:1, context:"내용8", },
                {stid:9, stname:"스터디9", maxPcnt:2, pcnt:1, stype:1, sgroup:2, context:"내용9", },
                {stid:10, stname:"스터디10", maxPcnt:3, pcnt:2, stype:2, sgroup:3, context:"내용10", },
                {stid:11, stname:"스터디11", maxPcnt:6, pcnt:5, stype:3, sgroup:4, context:"내용11", },
                {stid:12, stname:"스터디12", maxPcnt:5, pcnt:2, stype:4, sgroup:4, context:"내용12", },
                {stid:13, stname:"스터디13", maxPcnt:5, pcnt:4, stype:2, sgroup:3, context:"내용13", },
                {stid:14, stname:"스터디14", maxPcnt:6, pcnt:2, stype:1, sgroup:2, context:"내용14", },
                {stid:15, stname:"스터디15", maxPcnt:7, pcnt:6, stype:1, sgroup:1, context:"내용15", },
            ],
        }
    },
}
</script>

<style>

</style>