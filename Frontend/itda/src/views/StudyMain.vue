<template>
  <div class="studymain">
      <v-container class="my-0" white >
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
                                <v-btn v-if="formData.stype" @click="openPopup(formData.stype)">스터디 주제 입력</v-btn>
                                <v-select v-model="formData.sgroup" :items="formCategory.category2" :rules="[v => !!v || '스터디 내용를 선택해주세요']" label="스터디 내용" required></v-select>
                                <v-textarea v-model="formData.content" solo :rules="[v => !!v || '스터디 정보를 입력해주세요']" label="스터디 정보를 입력해주세요"></v-textarea>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="success" text :disabled="!valid" @click="validate">submit</v-btn>
                            <v-btn color="blue darken-1" text @click="reset">Reset</v-btn>
                            <v-btn color="blue darken-1" text @click="close">Close</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
          </v-row>
      </v-container>
      
      <v-container class="my-0" white v-if="isLogin">
          <study-login-content/>
      </v-container>
      <v-container class="my-0" white>
          <v-row><h1>스터디 전체 보기</h1></v-row>
          <study-filter :options="this.options" style="max-width:930px"/>
          <study-default-content :options="this.options" :allstudy="this.studies" :islogin="isLogin" v-on:update="update"/>
                
      </v-container>
 </div>
</template>

<script>
import StudyLoginContent from '../components/StudyMain/StudyLoginContent.vue'
import StudyFilter from '../components/StudyMain/StudyFilter.vue'
import StudyDefaultContent from '../components/StudyMain/StudyDefaultContent.vue'
import axios from 'axios'
import { mapState } from 'vuex'

export default {
    name:"studymain",
    components:{
        StudyLoginContent,
        StudyFilter,
        StudyDefaultContent,
    },
    methods:{
        getStudies(){
            axios.get('http://192.168.31.54:8197/itda/api/getAllStudy')
            .then(response=>{
                this.studies = response.data
            })
        },
        update(){
            this.getStudies()
        },
        validate () {
        var select = JSON.parse(localStorage.getItem('select'))
        if (!select){
            alert('스터디 주제를 확인해주세요')
        }
        else{
            this.formData.typeFk = select.id
            this.formData.typeName = select.Nm
            if (this.$refs.form.validate()) {
                this.snackbar = true
                axios.post('http://192.168.31.54:8197/itda/api/createStudy', this.formData, {'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
                .then(()=>{
                    alert('스터디를 생성하였습니다.')
                    this.$refs.form.reset()
                    this.overlay = false
                    localStorage.removeItem('select')
                    this.$emit("child")
                    this.getStudies()
                })
                .catch()
                }
            }
        },
        reset () {
            this.$refs.form.reset()
            localStorage.removeItem('select')
        },
        close() {
            this.formData = {stname:'',content:'',maxPcnt:null,stype:null,sgroup:null,typeFk:null,typeName:''},
            this.$refs.form.reset()
            localStorage.removeItem('select')
            this.overlay = false
        },
        openPopup(type){
            let routeData = this.$router.resolve({name: 'searchdata', params: {type: type}})
            window.open(routeData.href, '_blank', 'width=500,height=700,')
        },
    },
    data(){
        return{
            options:{
                category1 : 0,
                category2 : 0,
                keyword : "",
            },
            valid:false,
            formData:{
                stname:'',
                content:'',
                maxPcnt:null,
                stype:null,
                sgroup:null,
                typeFk:null,
                typeName:'',
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
    },
    computed: {
        ...mapState(["isLogin"])
    },
}
</script>

<style>

</style>