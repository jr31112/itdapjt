<template>
<div>
    <v-card @click.stop="overlay = !overlay">
      <v-card-text class="cardtext">
        <v-container class="px-0">
        <v-row>
            <v-col cols="8"><h4>{{job.jname}}</h4></v-col>
            <v-spacer></v-spacer>
            <v-col cols="4">신입</v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row><v-col v-html="job.jdetail"></v-col></v-row>
        </v-container>
      </v-card-text>
    </v-card>
    <v-dialog v-model="overlay" scrollable max-width="500px">
      <v-card>
        <v-card-title>{{job.jname}}<v-spacer></v-spacer><div style="color:green">{{job.jtype}}</div></v-card-title>
        <v-divider class="mb-3"></v-divider>
        <v-card-text style="height: 300px;">
          <div v-if="job.jdetail">
            <h4>직무상세</h4>
            <p class="ml-3" v-html="job.jdetail"></p>
          </div>
          <div v-if="job.to">
            <h4>채용인원</h4>
            <p class="ml-3">{{job.to}}명</p>  
          </div>
          <div v-if="job.place">
            <h4>근무지</h4>
            <p class="ml-3">{{job.place}}</p>
          </div>
          <div v-if="job.require">
            <h4>자격요건</h4>
            <p class="ml-3" v-html="job.require"></p>
          </div>
          <div v-if="job.extra">
            <h4>우대사항</h4>
            <p class="ml-3" v-html="job.extra"></p>
          </div>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="overlay = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</div>

</template>

<script>
export default {
    name:"detailjobinfo",
    props:{
        job:{type:Object}
    },
    data(){
        return{
            overlay: false
        }
    }
}
</script>

<style>

</style>