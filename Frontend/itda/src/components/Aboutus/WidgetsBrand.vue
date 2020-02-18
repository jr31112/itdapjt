<template>
  <CRow>
    <template v-if="readData">
      <CCol md="1"></CCol>
      <!-- mingyu -->
      <CCol md="2" sm="4">
        <CWidgetBrand
          color="white"
          :right-header="totalData1"
          right-footer="Commits"
          :left-header="projects1"
          left-footer="Projects"
        >
          <img src="../../assets/mingyu.png" height="120" class="my-4" alt />
          <CChartLineSimple
            class="c-chart-brand"
            background-color="rgba(255,255,255,.1)"
            :data-points="data1"
            label="Commits"
            labels="weeks"
          />
        </CWidgetBrand>
      </CCol>
      <!-- daerae -->
      <CCol md="2" sm="4">
        <CWidgetBrand
          color="white"
          :right-header="totalData2"
          right-footer="Commits"
          :left-header="projects2"
          left-footer="Projects"
        >
          <img src="../../assets/daerae.png" height="120" class="my-4" alt />

          <CChartLineSimple
            class="c-chart-brand"
            background-color="rgba(255,255,255,.1)"
            :data-points="data2"
            label="Commits"
            labels="weeks"
          />
        </CWidgetBrand>
      </CCol>
      <!-- insung -->
      <CCol md="2" sm="4">
        <CWidgetBrand
          color="white"
          :right-header="totalData3"
          right-footer="Commits"
          :left-header="projects3"
          left-footer="Projects"
        >
          <img src="../../assets/insung.png" height="120" class="my-4" alt />

          <CChartLineSimple
            class="c-chart-brand"
            background-color="rgba(255,255,255,.1)"
            :data-points="data3"
            label="Commits"
            labels="weeks"
          />
        </CWidgetBrand>
      </CCol>
      <!-- jungwon -->
      <CCol md="2" sm="4">
        <CWidgetBrand
          color="white"
          :right-header="totalData4"
          right-footer="Commits"
          :left-header="projects4"
          left-footer="Projects"
        >
          <img src="../../assets/jungwon.png" height="120" class="my-4" alt />

          <CChartLineSimple
            class="c-chart-brand"
            background-color="rgba(255,255,255,.1)"
            :data-points="data4"
            label="Commits"
            labels="weeks"
          />
        </CWidgetBrand>
      </CCol>
      <!-- jinhee -->
      <CCol md="2" sm="4">
        <CWidgetBrand
          :right-header="totalData5"
          right-footer="Commits"
          :left-header="projects5"
          left-footer="Projects"
          color="white"
        >
          <img src="../../assets/jinhee.jpg" height="120" class="my-4" alt />

          <CChartLineSimple
            class="c-chart-brand"
            background-color="rgba(255,255,255,.1)"
            :data-points="data5"
            label="Commits"
            labels="weeks"
          />
        </CWidgetBrand>
      </CCol>
      <CCol md="1"></CCol>
    </template>
  </CRow>
</template>

<script>
import { CChartLineSimple } from "./charts/index.js";
import axios from "axios";

export default {
  name: "WidgetsBrand",
  components: {
    CChartLineSimple
  },
  props: {
    noCharts: Boolean
  },
  data() {
    return {
      data1: [0, 0, 0, 0, 0, 0, 0],
      totalData1: 0,
      projects1: 0,
      data2: [0, 0, 0, 0, 0, 0, 0],
      totalData2: 0,
      projects2: 0,
      data3: [0, 0, 0, 0, 0, 0, 0],
      totalData3: 0,
      projects3: 0,
      data4: [0, 0, 0, 0, 0, 0, 0],
      totalData4: 0,
      projects4: 0,
      data5: [0, 0, 0, 0, 0, 0, 0],
      totalData5: 0,
      projects5: 0,
      readData: false,
      imgUrl: "../../assets/"
    };
  },
  created() {
    let private_token = "Ktzuz56Epv97yEezs463";
    let email = [
      "0713mingyu@naver.com",
      "drk0830@naver.com",
      "jr435@naver.com",
      "koo_m@naver.com",
      "genie121110@gmail.com"
    ];
    axios
      .get(
        "https://lab.ssafy.com/api/v4/projects/17324/repository/commits/?per_page=100&private_token=" +
          private_token
      )
      .then(response => {
        let data = response.data;
        let sorting = "created_at";
        data.sort(function(a, b) {
          var dateA = new Date(a[sorting]).getTime();
          var dateB = new Date(b[sorting]).getTime();
          return dateA > dateB ? 1 : -1;
        });
        for (var i = 0; i < data.length; i++) {
          var dayOfWeek = new Date(
            data[i].created_at.substring(0, 10)
          ).getDay();
          if (data[i].author_email == email[0]) {
            this.data1[dayOfWeek] += 1;
            this.totalData1 += 1;
          } else if (data[i].author_email == email[1]) {
            this.data2[dayOfWeek] += 1;
            this.totalData2 += 1;
          } else if (data[i].author_email == email[2]) {
            this.data3[dayOfWeek] += 1;
            this.totalData3 += 1;
          } else if (data[i].author_email == email[3]) {
            this.data4[dayOfWeek] += 1;
            this.totalData4 += 1;
          } else if (data[i].author_email == email[4]) {
            this.data5[dayOfWeek] += 1;
            this.totalData5 += 1;
          }
        }
        this.totalData1 +="";
        this.totalData2 +="";
        this.totalData3 +="";
        this.totalData4 +="";
        this.totalData5 +="";
        axios
          .get(
            "https://lab.ssafy.com/api/v4/users/mingyu/projects?private_token=" +
              private_token
          )
          .then(response => {
            this.projects1 = response.data.length+"";
            axios
              .get(
                "https://lab.ssafy.com/api/v4/users/daerae_kim/projects?private_token=" +
                  private_token
              )
              .then(response => {
                this.projects2 = response.data.length+"";
                axios
                  .get(
                    "https://lab.ssafy.com/api/v4/users/jr31112/projects?private_token=" +
                      private_token
                  )
                  .then(response => {
                    this.projects3 = response.data.length+"";
                    axios
                      .get(
                        "https://lab.ssafy.com/api/v4/users/kimjungwon/projects?private_token=" +
                          private_token
                      )
                      .then(response => {
                        this.projects4 = response.data.length+"";
                        axios
                          .get(
                            "https://lab.ssafy.com/api/v4/users/banggeut/projects?private_token=" +
                              private_token
                          )
                          .then(response => {
                            this.projects5 = response.data.length+"";
                            this.readData = true;
                          });
                      });
                  });
              });
          });
      })
      .catch(() => {});
  }
};
</script>

<style scoped>
.c-chart-brand {
  position: absolute;
  width: 100%;
  height: 100px;
}
</style>
