<template>
  <v-col class="wantedstack" cols="12" md="6">
    <h2 class="mb-3">기술스택</h2>
    <v-carousel
      v-if="stackList.length"
      hide-delimiters
      :show-arrows="false"
      cycle
      interval="2500"
      height="400"
    >
      <v-carousel-item v-for="j in 2" :key="j">
        <v-container white>
          <v-row
            id="wanted"
            v-for="i in 5"
            :key="i"
            style="height:78px;"
            @click.prevent="goDetailPage(stackList[5*(j-1)+i-1].wanted.wid)"
          >
            <v-col cols="3">
              <v-img
                v-if="stackList[5*(j-1)+i-1].company.logo"
                :src="stackList[5*(j-1)+i-1].company.logo"
                :alt="stackList[5*(j-1)+i-1].company.corpNm"
                :contain="true"
                max-width="150"
                aspect-ratio="2.67"
              ></v-img>
              <v-img
                v-else
                :src="getImgUrl('noimg.png')"
                alt="noimg"
                max-width="150"
                aspect-ratio="2.67"
                :contain="true"
              ></v-img>
            </v-col>
            <v-col cols="9">
              <v-row id="corpNm">{{stackList[5*(j-1)+i-1].company.corpNm}}</v-row>
              <v-row id="wantedTitle">{{stackList[5*(j-1)+i-1].wanted.wantedTitle}}</v-row>
            </v-col>
          </v-row>
        </v-container>
      </v-carousel-item>
    </v-carousel>
    <v-carousel
      v-else
      hide-delimiters
      :show-arrows="false"
      height="400"
    >
      <v-carousel-item>
        <v-container class="d-flex align-center" style="height:400px" white @click="goMypage()">
          <v-row justify="center">
            <v-col class="text-center">
              원하는 기술스택을 입력해주세요!!
            </v-col>
          </v-row>
        </v-container>
      </v-carousel-item>
    </v-carousel>
  </v-col>
</template>

<script>
import router from '../../../router'

export default {
    name: "recruitstacklist",
    props:{
      stackList:{type:Array}
    },
    methods: {
      getImgUrl(img) {
        return require("../../../assets/" + img);
      },
      goDetailPage(wid) {
        router.push({ name: "recruitdetail", params: { id: wid } });
      },
      goMypage(){
      router.push({name:"mypage",params:{id:1}})
      },
  },
}
</script>

<style>
#corpNm{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
#wantedTitle{
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
#wanted:hover{
  background-color: #eeeeee;
}
</style>