<template>
  <div class="studymain">
      <v-container class="my-0" white>
<<<<<<< HEAD
          <h1>스터디 전체 보기</h1>
          <study-filter :options="this.options" style="max-width:930px"/>
          <study-default-content :options="this.options" :allstudy="this.studies"/>
          <v-row>
                <v-btn class="ml-auto" @click.stop="overlay = !overlay">스터디 새로등록</v-btn>
                <v-dialog v-model="overlay" scrollable max-width="500px">
                    <v-card>
                        <v-card-title>스터디 등록</v-card-title>
                        <v-card-text>
                            <v-form ref="form" v-model="valid">
                                <v-text-field v-model="formData.stname" :rules="[v => !!v || '스터디 이름을 입력해주세요']" label="스터디명" required></v-text-field>
                                <v-text-field v-model.number="formData.maxPcnt" type="number" :rules="[v => !!v || '인원수를 입력해주세요']" label="인원수" required></v-text-field>
                                <v-select v-model="formData.stype" :items="formCategory.category1" :rules="[v => !!v || '스터디 종류를 선택해주세요']" label="스터디 종류" required></v-select>
                                <v-select v-model="formData.sgroup" :items="formCategory.category2" :rules="[v => !!v || '스터디 내용를 선택해주세요']" label="스터디 내용" required></v-select>
                                <v-textarea v-model="formData.content" solo :rules="[v => !!v || '스터디 정보를 입력해주세요']" label="스터디 정보를 입력해주세요"></v-textarea>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="success" text :disabled="!valid" @click="validate">submit</v-btn>
                            <v-btn color="blue darken-1" text @click="reset">Reset</v-btn>
                            <v-btn color="blue darken-1" text @click="overlay = false">Close</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
      </v-container>
 </div>
=======
          <study-filter :options="this.options"/>
          <study-default-content :options="this.options" :allstudy="this.studies"/>
      </v-container>
  </div>
>>>>>>> 9a3e780168f60a1f7fd6dce47ceac0baeb72133d
</template>

<script>
import StudyFilter from '../components/StudyMain/StudyFilter'
import StudyDefaultContent from '../components/StudyMain/StudyDefaultContent'
<<<<<<< HEAD
import axios from 'axios'
=======

>>>>>>> 9a3e780168f60a1f7fd6dce47ceac0baeb72133d

export default {
    name:"studymain",
    components:{
        StudyFilter,
        StudyDefaultContent,
    },
<<<<<<< HEAD
    methods:{
        getStudies(){
            axios.get('http://192.168.31.54:8197/itda/api/getAllStudy')
            .then(response=>{
                this.studies = response.data
            })
        },
        validate () {
        if (this.$refs.form.validate()) {
            this.snackbar = true
            axios.post('http://192.168.31.54:8197/itda/api/createStudy', this.formData, {'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
            .then(()=>{
                alert('스터디를 생성하였습니다.')
                this.$refs.form.reset()
                this.overlay = false
                this.$emit("child")
                this.getStudies()
                // this.formData = {
                //     stname:'',
                //     content:'',
                //     maxPcnt:null,
                //     stype:null,
                //     sgroup:null,
                //     typeFk:1,
                //     typeName:'쿠팡',}
            })
            .catch()
            }
        },
        reset () {
            this.$refs.form.reset()
        },
    },
=======
>>>>>>> 9a3e780168f60a1f7fd6dce47ceac0baeb72133d
    data(){
        return{
            options:{
                category1 : 0,
                category2 : 0,
<<<<<<< HEAD
                keyword : "",
            },
            valid:false,
            formData:{
                stname:'',
                content:'',
                maxPcnt:null,
                stype:null,
                sgroup:null,
                typeFk:1,
                typeName:'쿠팡',
            },
            formCategory:{
                category1:[
                    {text: '공고 대비', value: 1},
                    {text: '기업 대비', value: 2},
                    {text: '기술 대비', value: 3},
                    {text: '기타 등등', value: 4},
                    ],
                category2:[
                    {text: '자소서 준비', value: 1},
                    {text: '필기 준비', value: 2},
                    {text: '면접 준비', value: 3},
                    {text: '기타 등등', value: 4},
                    ]
            },
            overlay:false,
            studies:[
            ],
        }
    },
    mounted(){
        this.getStudies()
    }
=======
                keyword: ""
            },
            studies:[
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
>>>>>>> 9a3e780168f60a1f7fd6dce47ceac0baeb72133d
}
</script>

<style>
<<<<<<< HEAD
#notOverlayBtn {
    width: 100%;
  }
=======
>>>>>>> 9a3e780168f60a1f7fd6dce47ceac0baeb72133d

</style>