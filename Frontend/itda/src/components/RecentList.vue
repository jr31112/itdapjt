<template>
  <v-row class="recentlist">
	<v-col class="px-0">
		<carousel :per-page="1" :paginationEnabled="false" :scrollPerPage="true" v-if="recentlist.length">
		<slide v-for="k in 2" :key="k">
			<v-container>
			<v-row v-for="j in 2" :key="j">
				<v-col v-for="i in 6" cols="6" lg="2" :key="i">
				<Recent :company="recentlist[6*(j-1)+(i-1)].company" :wanted="recentlist[6*(j-1)+(i-1)].wanted"/>
				</v-col>
			</v-row>
			</v-container>
		</slide>
		</carousel>
	</v-col>
  </v-row>
</template>

<script>
import { Carousel, Slide } from 'vue-carousel';
import Recent from './Recent.vue';
import axios from 'axios'

export default {
	name: 'RecentList',
	components: {
		Recent,
		Carousel,
		Slide,
	},
	data(){
		return{
			recentlist:[
			]
		}
	},
	methods:{
		getRecentRecruit(){
			axios.get(`http://192.168.31.54:8197/itda/api/getWantedByRecent/`)
				.then(response=>{
					this.recentlist = response.data
				})
				.catch(()=>{})
		},
	},
	mounted(){
		this.getRecentRecruit()
	}
}
</script>

<style>

</style>