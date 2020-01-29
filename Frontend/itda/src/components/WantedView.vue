<template>
  <v-col class="wantedview" cols="12" md="6">
    <carousel :per-page="1" :paginationEnabled="false">
      <slide>
        <v-data-table :headers="headers" :items="desserts.slice(0, 5)" :hide-default-footer="true">
        </v-data-table>
      </slide>
      <slide>
        <v-data-table :headers="headers" :items="desserts.slice(5, 10)" :hide-default-footer="true">
        </v-data-table>
      </slide>
    </carousel>
  </v-col>
</template>

<script>
import { Carousel, Slide } from 'vue-carousel'
import axios from 'axios'

export default {
  name: "wantedview",
  components: {
    Carousel,
    Slide
  },
  methods:{
		getRecentRecruit(){
			axios.get(`http://192.168.31.54:8197/ssafyvue/api/getWantedByRecent/`)
				.then(response=>{
					this.wanted = response.data
				})
				.catch(()=>{})
        }
	},
	mounted(){
		this.getRecentRecruit()
	},
  data() {
    return {
      
    }
  }
}
</script>

<style>

</style>