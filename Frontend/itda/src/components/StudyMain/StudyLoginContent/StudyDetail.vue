<template>
  <v-col class="studydetail" cols="6" md="3">
    <v-hover v-slot:default="{ hover }">
      <v-card light align-center height="200" :elevation="hover ? 16 : 2" class="mx-auto">
        <v-container v-if="study.stid">
          <v-row align="center">
            <v-col cols="9">
              {{study.stname}}
            </v-col>
            <v-col cols="3">
              {{study.pcnt}} / {{study.maxPcnt}}
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>
    <v-dialog v-model="overlayRead" scrollable max-width="500px" white>
        <v-card>
            <v-card-title>{{study.stname}}<v-spacer></v-spacer>{{study.pcnt}}/{{study.maxPcnt}}</v-card-title>
            <v-card-text>
                <p>스터디 종류 : {{category1[study.stype-1]}}</p>
                <p>스터디 주제 : {{study.typeName}}</p>
                <p>스터디 목적 : {{category2[study.sgroup-1]}}</p>
                <p>스터디 내용</p>
                <v-divider></v-divider>
                <p>{{study.content}}</p>
                <p v-for="people in person" :key="people.uid+people.uname">{{people.uname}}</p>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="red darken-1" text @click="del" v-if="isLogin&&ismember&study.captain==userInfo.user.uid">스터디 삭제</v-btn>
                <v-btn color="blue darken-1" text @click="enter" v-if="isLogin&&!ismember&&study.pcnt<study.maxPcnt">스터디 가입</v-btn>
                <v-btn color="blue darken-1" text @click="exit" v-if="isLogin&&ismember">스터디 탈퇴</v-btn>
                <v-btn color="black darken-1" text @click="close">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
export default {
    name:'studydetail',
    props:{
      study:{type:Object}
    }
}
</script>

<style>

</style>