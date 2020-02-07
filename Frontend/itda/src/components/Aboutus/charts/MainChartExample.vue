<template>
  <CChartLine v-if="readData"
    :datasets="defaultDatasets"
    :options="defaultOptions"
    :labels="['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']"
  />
</template>

<script>
import { CChartLine } from "@coreui/vue-chartjs";
import { getColor } from "@coreui/utils/src";
import axios from "axios";

export default {
  name: "MainChartExample",
  components: {
    CChartLine
  },
  data() {
    return {
      readData:false,
      data1: [0, 0, 0, 0, 0, 0, 0],
      data2: [0, 0, 0, 0, 0, 0, 0],
      data3: [0, 0, 0, 0, 0, 0, 0],
      data4: [0, 0, 0, 0, 0, 0, 0],
      data5: [0, 0, 0, 0, 0, 0, 0]
    };
  },
  mounted() {
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
          } else if (data[i].author_email == email[1]) {
            this.data2[dayOfWeek] += 1;
          } else if (data[i].author_email == email[2]) {
            this.data3[dayOfWeek] += 1;
          } else if (data[i].author_email == email[3]) {
            this.data4[dayOfWeek] += 1;
          } else if (data[i].author_email == email[4]) {
            this.data5[dayOfWeek] += 1;
          }
        }
        this.readData = true;
      })
      .catch(() => {})
  },
  computed: {
    defaultDatasets() {
      const brandSuccess = getColor("success") || "#4dbd74";
      const brandInfo = getColor("info") || "#20a8d8";
      const brandDanger = getColor("danger") || "#f86c6b";
      const brandWarning = getColor("warning") || "#f9b115";
      const brandPrimary = getColor("primary") || "#321fdb";

      return [
        {
          label: "mingyu",
          backgroundColor: "transparent",
          borderColor: brandSuccess,
          pointHoverBackgroundColor: brandSuccess,
          borderWidth: 2,
          data: this.data1
        },
        {
          label: "Daerae_Kim",
          backgroundColor: "transparent",
          borderColor: brandInfo,
          pointHoverBackgroundColor: brandInfo,
          borderWidth: 2,
          data: this.data2
        },
        {
          label: "jr31112",
          backgroundColor: "transparent",
          borderColor: brandDanger,
          pointHoverBackgroundColor: brandDanger,
          borderWidth: 1,
          data: this.data3
        },
        {
          label: "kimjungwon",
          backgroundColor: "transparent",
          borderColor: brandWarning,
          pointHoverBackgroundColor: brandWarning,
          borderWidth: 1,
          data: this.data4
        },
        {
          label: "banggeut",
          backgroundColor: "transparent",
          borderColor: brandPrimary,
          pointHoverBackgroundColor: brandPrimary,
          borderWidth: 1,
          data: this.data5
        }
      ];
    },
    defaultOptions() {
      return {
        maintainAspectRatio: false,
        legend: {
          display: false
        },
        scales: {
          xAxes: [
            {
              gridLines: {
                drawOnChartArea: false
              }
            }
          ],
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
                maxTicksLimit: 5,
                stepSize: Math.ceil(20 / 5),
                max: 20
              },
              gridLines: {
                display: true
              }
            }
          ]
        },
        elements: {
          point: {
            radius: 0,
            hitRadius: 10,
            hoverRadius: 4,
            hoverBorderWidth: 3
          }
        }
      };
    }
  }
};
</script>
