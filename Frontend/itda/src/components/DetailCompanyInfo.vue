<template>
  <v-container class="detailcompanyinfo my-2 white">
    <v-row justify="center">
      <v-col cols="2" align-self="center">
        
          <v-card width="150" height="150">
            <v-img :src="company.logo" :alt="company.corpNm" width="150" aspect-ratio="1" :contain="true">
            </v-img>
          </v-card>
       
      </v-col>
      <v-col cols="10" justify="center">
        <h1>{{company.corpNm}}</h1>
        <br>
        <p>홈페이지 : <a :href="company.homePg">{{company.homePg}}</a></p>
        <p>기업주소 : {{company.corpAddr}}</p>
        <p>주요사업 : {{company.busiCont}}</p>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="3">
        <v-card class="mx-0">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title two-line>
                {{company.busiSize}}
              </v-list-item-title>
              <v-list-item-subtitle>기업형태</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="3">
        <v-card class="mx-0">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title two-line>
                {{company.totPsncnt}}
              </v-list-item-title>
              <v-list-item-subtitle>사원수</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </v-col>
      <v-col cols="12" md="3">
        <v-card class="mx-0">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title two-line>
                {{company.yrSalesAmt}}
              </v-list-item-title>
              <v-list-item-subtitle>매출</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
    name: "detailcompanyinfo",
    props:{
      company:{type:Object}
    }
}
</script>

<style>

</style>