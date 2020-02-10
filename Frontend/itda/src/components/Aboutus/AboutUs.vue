<template>
  <div>
    <WidgetsBrand />
    <CCard>
      <CCardBody>
        <CRow>
          <CCol sm="5">
            <h4 id="traffic" class="card-title mb-0">Commit</h4>
            <div class="small text-muted">For Sub PJT 3</div>
          </CCol>
        </CRow>
        <MainChartExample style="height:300px;margin-top:40px;" />
      </CCardBody>
      <CCardFooter>
        <CRow class="text-center">
          <CCol md sm="12" class="mb-sm-2 mb-0">
            <div class="text-muted">{{tableItems[0].user.name}}</div>
            <CProgress class="progress-xs mt-2" :precision="1" color="success" :value="100" />
          </CCol>
          <CCol md sm="12" class="mb-sm-2 mb-0 d-md-down-none">
            <div class="text-muted">{{tableItems[1].user.name}}</div>
            <CProgress class="progress-xs mt-2" :precision="1" color="info" :value="100" />
          </CCol>
          <CCol md sm="12" class="mb-sm-2 mb-0">
            <div class="text-muted">{{tableItems[2].user.name}}</div>
            <CProgress class="progress-xs mt-2" :precision="1" color="danger" :value="100" />
          </CCol>
          <CCol md sm="12" class="mb-sm-2 mb-0">
            <div class="text-muted">{{tableItems[3].user.name}}</div>
            <CProgress class="progress-xs mt-2" :precision="1" color="warning" :value="100" />
          </CCol>
          <CCol md sm="12" class="mb-sm-2 mb-0 d-md-down-none">
            <div class="text-muted">{{tableItems[4].user.name}}</div>
            <CProgress class="progress-xs mt-2" :precision="1" :value="100" />
          </CCol>
        </CRow>
      </CCardFooter>
    </CCard>


<!-- 깃랩 프로젝트 commit  -->
    <CRow>
      <CCol md="12">
        <CCard>
          <CCardHeader>
            <CRow>
            <CCol sm="12">
                <CRow>
                  <CCol sm="12">
                    <CCallout color="warning">
                      <small class="text-muted">{{pjtRepo}}</small>
                      <br />
                      <strong class="h4">{{pjtName}}</strong>
                      &emsp;
                      <a :href="pjtLink" target="_blank">
                        <CIcon name="cib-gitlab" height="25"/>
                      </a>
                      &emsp;
                      <a :href="'https://jira.ssafy.com/projects/S02P13B201/'" target="_blank">
                        <CIcon name="cib-jira"  height="25"/>
                      </a>
                    </CCallout>
                  </CCol>
                </CRow>
              </CCol>
            </CRow>
          </CCardHeader>
          <CCardBody>
            <CDataTable
              class="mb-0 table-outline"
              hover
              :items="tableItems"
              :fields="tableFields"
              head-color="light"
              no-sorting
            >
              <td slot="avatar" class="text-center" slot-scope="{item}">
                <div class="c-avatar">
                  <img :src="item.avatar.url" class="c-avatar-img" alt />
                  <span class="c-avatar-status" :class="`bg-${item.avatar.status || 'secondary'}`"></span>
                </div>
              </td>
              <td slot="user" slot-scope="{item}">
                <div>{{item.user.name}}
                  | Email : {{item.user.email}}
                </div>
                <div class="small text-muted">
                  <span>
                    <template>{{item.user.roll}}</template>
                  </span>
                  | Team : {{item.user.team}}
                </div>
              </td>
              <td slot="usage" slot-scope="{item}">
                <div class="clearfix">
                  <div class="float-left">
                    <strong>{{item.usage.value}}%</strong>
                  </div>
                </div>
                <CProgress
                  class="progress-xs"
                  v-model="item.usage.value"
                  :color="color(item.usage.value)"
                />
              </td>
              <td slot="activity" slot-scope="{item}">
                <a :href="item.activity" target="_blank">
                  <CIcon name="cib-gitlab"/>
                </a>
              </td>
            </CDataTable>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import MainChartExample from "./charts/MainChartExample";
import WidgetsBrand from "./WidgetsBrand";
import axios from 'axios'

export default {
  name: "Dashboard",
  components: {
    MainChartExample,
    WidgetsBrand
  },
  data() {
    return {
      selected: "Month",
      pjtName: "",
      pjtLink: "",
      pjtRepo: "",
      pjtReadme: "",
      tableItems: [
        {
          avatar: { url: "", status: "" },
          user: { name: "", email :"0713mingyu@naver.com", roll: "팀장", team: "Front-end" },
          usage: { value: 0 },
          activity: ""
        },
        {
          avatar: { url: "", status: "" },
          user: { name: "", email :"drk0830@naver.com", roll: "CTO", team: "Back-end" },
          usage: { value: 0 },
          activity: ""
        },{
          avatar: { url: "", status: "" },
          user: { name: "", email :"jr435@naver.com", roll: "배포", team: "Front-end" },
          usage: { value: 0 },
          activity: ""
        },{
          avatar: { url: "", status: "" },
          user: { name: "", email :"koo_m@naver.com", roll: "테스트", team: "Front-end" },
          usage: { value: 0 },
          activity: ""
        },
        {
          avatar: { url: "", status: "" },
          user: { name: "", email :"genie121110@gmail.com", roll: "데이터베이스", team: "Back-end" },
          usage: { value: 0 },
          activity: ""
        }
      ],
      tableFields: [
        { key: "avatar", label: "", _classes: "text-center" },
        { key: "user" },
        { key: "usage" },
        { key: "activity" }
      ]
    };
  },
  methods: {
    color(value) {
      let $color;
      if (value <= 10) {
        $color = "info";
      } else if (value > 10 && value <= 20) {
        $color = "warning";
      } else if (value > 20 && value <= 40) {
        $color = "success";
      } else if (value > 40 && value <= 100) {
        $color = "danger";
      }
      return $color;
    },
    getDataFromAPI(){
      let private_token = 'Ktzuz56Epv97yEezs463'
      axios
        .get('https://lab.ssafy.com/api/v4/projects/17324/members/?private_token=' + private_token)
        .then(response =>{
          for(var i = 0; i <response.data.length; i++){
            this.tableItems[i].avatar.url = response.data[i].avatar_url;
            this.tableItems[i].user.name = response.data[i].username;
            this.tableItems[i].activity = response.data[i].web_url;
          }
          axios
            .get('https://lab.ssafy.com/api/v4/projects/17324/repository/contributors/?private_token=' + private_token)
            .then(response =>{
              var totalCommit = 0;
              var commits = [0,0,0,0,0];
              for(var i = 0; i < response.data.length; i++){
                totalCommit += response.data[i].commits;
                for(var j = 0; j < this.tableItems.length; j++){
                  if(response.data[i].email == this.tableItems[j].user.email){
                    commits[j] = response.data[i].commits;
                  }
                }
              }
              for(var k = 0; k < commits.length; k++){
                this.tableItems[k].usage.value = Math.round((commits[k] / totalCommit) * 100);
              }
            })
            .catch(() => {})
        })
        .catch(() => {})
    },
    getPjtFromAPI(){
      let private_token = 'Ktzuz56Epv97yEezs463'
      axios
        .get('https://lab.ssafy.com/api/v4/projects/17324/?private_token=' + private_token)
        .then(response => {
          this.pjtName = response.data.name_with_namespace;
          this.pjtRepo = response.data.http_url_to_repo;
          this.pjtLink = response.data.web_url;
          this.pjtReadme = response.data.readme_url;
        })
    }
  },
  mounted(){
    this.getDataFromAPI()
    this.getPjtFromAPI()
  }
};
</script>
