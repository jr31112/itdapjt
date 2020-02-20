<template>
  <v-col class="wantedscrap" cols="12" md="6">
    <div class="main-box">
      <div class="main-box-h-div">
        <h5 class="mb-0 main-box-h p-1"><b>{{this.uname}}</b>님 맞춤공고</h5>
      </div>
      <span class="main-box-desc">
        <v-icon>hashtag</v-icon>
        기술스택에 맞는 공고를 추천해드립니다.</span>
      <v-carousel
        v-if="scrapList.length"
        hide-delimiters
        :show-arrows="false"
        cycle
        interval="2500"
        height="400"
      >
        <v-carousel-item v-for="j in scrapList.length>5?2:scrapList.length>0?1:0" :key="j">
          <v-container style="height:400px" white>
            <v-row
              class="main-list"
              id="wanted"
              v-for="i in scrapList.length>=5*j?5:scrapList.length-5*(j-1)"
              :key="i"
              style="height:78px;"
              @click.prevent="goDetailPage(scrapList[5*(j-1)+i-1].wanted.wid)"
            >
              <v-col cols="3">
                <v-img
                  v-if="scrapList[5*(j-1)+i-1].company.logo"
                  :src="scrapList[5*(j-1)+i-1].company.logo"
                  :alt="scrapList[5*(j-1)+i-1].company.corpNm"
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
                <v-row id="corpNm">{{scrapList[5*(j-1)+i-1].company.corpNm}}</v-row>
                <v-row id="wantedTitle">{{scrapList[5*(j-1)+i-1].wanted.wantedTitle}}</v-row>
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
          <v-container class="d-flex align-center" style="height:400px" white>
            <v-row justify="center">
              <v-col class="text-center">
                스크랩된 정보가 없어요!!
              </v-col>
            </v-row>
          </v-container>
        </v-carousel-item>
      </v-carousel>
    </div>
  </v-col>
</template>

<script>
import router from '../../../router'

export default {
  name: "recruitscraplist",
  props:{
    scrapList:{type:Array}
  },
  data(){
    return{
      uname : localStorage.getItem("uname"),
    }
  },
  methods: {
    getImgUrl(img) {
      return require("../../../assets/" + img);
    },
    goDetailPage(wid) {
      router.push({ name: "recruitdetail", params: { id: wid } });
    }
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
.main-box-desc {
  position: absolute;
  top: 40px;
  right: 40px;
  color: #525252;
}
</style>