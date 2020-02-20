<template>
  <v-container>
    <v-card >
      <v-form ref="form" @submit.prevent="submit">
        <v-card-title align:center>
          User Info
          <v-spacer></v-spacer>
          <router-link :to="{name:'mypage'}">
            <v-btn class="mb-0" text>
              <v-icon>backspace</v-icon>
            </v-btn>
          </router-link>
        </v-card-title>
        <v-row>
          <v-col cols="3">
            <v-card max-height="180" max-width="200" class="mx-auto ml-5">
              <v-img
                height="180"
                width="200"
                v-if="this.userInfo.user.uimg"
                :src="this.userInfo.user.uimg"
                dark="dark"
              ></v-img>
              <v-img
                height="180"
                width="200"
                v-else
                src="../assets/noimg.png"
                dark="dark"
                :contain="true"
              ></v-img>
            </v-card>
          </v-col>
          <v-col cols="7">
            <v-card-subtitle>User Name</v-card-subtitle>
            <v-card-text>
              <v-text-field v-model="userInfo.user.uname" label="Name"></v-text-field>
            </v-card-text>
            <input
              type="file"
              ref="uimg"
              accept="image/png, image/jpeg, image/bmp"
              placeholder="Input Image"
              prepend-icon="mdi-camera"
              label="My Image"
              @change="onChange()"
            />
          </v-col>
        </v-row>
        
        <v-card-subtitle>User email</v-card-subtitle>
        
        <v-row>
        <v-col cols="10">
        <v-card-text>
        <v-text-field :value="userInfo.user.email" readonly label="수정이 불가능한 항목입니다." color="red"></v-text-field>
        </v-card-text>
        </v-col>
        </v-row>
        <v-card-subtitle v-if="local_Chk">Password</v-card-subtitle>
        <v-card-text v-if="local_Chk">
          <v-row>
            <v-col cols="5">
              <v-text-field
                v-model="password_rg"
                :value="password_rg"
                type="password"
                label="password*"
              ></v-text-field>
            </v-col>
            <v-col cols="5">
              <v-text-field
                v-model="password_rg1"
                :value="password_rg1"
                type="password"
                label="password check*"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-subtitle>User Major</v-card-subtitle>
        <v-card-text>
          <v-text-field v-model="userInfo.user.major" label="Major"></v-text-field>
        </v-card-text>

        <v-card-title>
          Tech Stack
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" scrollable="scrollable" max-width="700px">
            <template v-slot:activator="{ on }">
              <v-btn
                class="mx-3"
                fab="fab"
                dark="dark"
                color="gray"
                width="30px"
                height="30px"
                v-on="on"
              >
                <v-icon dark="dark">mdi-plus</v-icon>
              </v-btn>
              <v-icon dark="dark">mdi-plus</v-icon>
            </template>
            <v-card width="500px">
              <v-card-title>Select Stack</v-card-title>
              <v-divider></v-divider>
              <v-card-text style="height: 500px;">
                <v-row v-for="i in 9" :key="i">
                  <v-col class="py-0 pr-0" v-for="j in i==9?1:5" :key="j">
                    
                      <v-row>
                        <div class="pa-0"><v-checkbox class="mt-0" v-model="stacklist[5*(i-1) + j-1].value"></v-checkbox></div>
                        <div class="pa-0">
                            <v-img class="mx-auto" height="20" width="20" :src="getImgUrl(5*(i-1) + j)" aspect-ratio="1" contain></v-img>
                            <p class="text-center">{{stacklist[5*(i-1) + j-1].tname}}</p>
                        </div>
                        
                      </v-row>
                  </v-col>
                </v-row>
              </v-card-text>
              <v-divider></v-divider>
              <v-spacer></v-spacer>
              <v-card-actions class="mb-3">
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text="text" @click="goSaveStack">Save</v-btn>
                <v-btn color="blue darken-1" text="text" @click="dialog = false">Close</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-card-title>
        <v-card-text :rules="[v => !!v || 'Stack을 입력해주세요']">
          <v-btn
            class="ma-2"
            v-for="i in userInfo.mystacks.length"
            :key="i"
            @click="removeStack(i-1, userInfo.mystacks[i-1].sid)"
          >{{userInfo.mystacks[i-1].tname}}</v-btn>
        </v-card-text>
        <v-spacer></v-spacer>
        <v-row justify="center">
          <v-btn class="ma-3" color="black darken-1" text="text" @click="goSave">Submit</v-btn>
        </v-row>
      </v-form>
    </v-card>

    <v-divider></v-divider>
    <v-card>
      <v-card-title>My study Info</v-card-title>
      <v-card-text></v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";
import router from "../router";
import { mapState } from "vuex";

export default {
  data() {
    return {
      local_Chk: false,
      userInfo: {
        user: {
          auth: 1,
          cid: null,
          email: null,
          major: null,
          pw: null,
          uimg: null,
          uname: null
        },
        mystacks: []
      },
      selectImg: "",
      password_rg: "",
      password_rg1: "",
      dialog: false,

    };
  },
  created() {
    this.getAllData();
  },
  methods: {
    goSave() {
      if (this.password_rg != "") {
        if (this.password_rg != this.password_rg1) {
          alert("비밀번호를 확인해주세요!");
          return;
        }
      }
      if (this.userInfo.user.uname == "") {
        alert("이름을 입력해주세요!");
        return;
      }
      let params = {
        user: {
          email: this.userInfo.user.email,
          uname: this.userInfo.user.uname,
          major: this.userInfo.user.major,
          pw: this.password_rg
        },
        isSocial: localStorage.getItem("social"),
        mystacks: this.userInfo.mystacks
      };
      axios
        .put("https://i02b201.p.ssafy.io:8197/itda/api/updateUser", params, {
          headers: { "jwt-auth-token": localStorage.getItem("access_token") }
        })
        .then(response => {
          alert(response.data.msg);
          if (response.data.state == "success") {
            router.push({ name: "mypage" }).catch(() => {});
          }
        });
    },
    removeStack(idx, sid) {
      this.userInfo.mystacks.splice(idx, 1);
      this.stacklist[sid - 1].value = false;
    },
    goSaveStack() {
      this.dialog = false;
      var tmp = [];
      for (var i = 0; i < 41; i++) {
        if (this.stacklist[i].value) {
          tmp.push({
            sid: this.stacklist[i].sid,
            tname: this.stacklist[i].tname
          });
        }
      }
      this.userInfo.mystacks = tmp;
    },
    getAllData() {
      if (localStorage.getItem("social") != "social") {
        this.local_Chk = true;
      } else {
        this.local_Chk = false;
      }
      axios
        .get(`https://i02b201.p.ssafy.io:8197/itda/api/getUser/`, {
          headers: {
            "jwt-auth-token": localStorage.getItem("access_token")
          }
        })
        .then(response => {
          if (response.data.state == "success") {
            this.userInfo = response.data;

            var tmp_stacks = response.data.mystacks;
            for (var i = 0; i < tmp_stacks.length; i++) {
              this.stacklist[tmp_stacks[i].sid - 1].value = true;
            }
          }
        })
        .catch(() => {});
    },
    onChange() {
      if (confirm("사진을 업로드하시겠습니까?") === true) {
        //확인

        console.log("확인");
        this.selectImg = this.$refs.uimg.files[0];
        var formdata = new FormData();
        formdata.append("file", this.selectImg);
        const config = {
          headers: {
            "Content-Type": "multipart/form-data",
            "jwt-auth-token": localStorage.getItem("access_token")
          }
        };
        axios
          .post(
            "https://i02b201.p.ssafy.io:8197/itda/api/uploadImg",
            formdata,
            config
          )
          .then(response => {
            this.imageResult = response.data.fileDownloadUri;
            this.userInfo.user.uimg = this.imageResult;
          });
      } else {
        //취소
        this.file = null;
        return false;
      }
    }
  },
  computed: {
    ...mapState(["stacklist"]),
    getImgUrl() {
      return (img)=>{
        return require("../assets/stacks/" + img + '.png')
      }
    },

  }
};
</script>

<style>
#center{
  position:relative;
  top: 20px;
}
</style>