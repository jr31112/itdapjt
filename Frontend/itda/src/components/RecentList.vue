<template>
	<v-container align-content>
		<carousel :per-page="1" :paginationEnabled="false" v-if="recentlist.length">
			<slide v-for="j in 2" :key="j" xs6 >
				<v-row>
					<v-col v-for="i in 12" cols="6" lg="2" :key="i"><Recent :company="recentlist[6*(j-1)+(i-1)].company" :wanted="recentlist[6*(j-1)+(i-1)].wanted"/>
					</v-col>
				</v-row>
			</slide>
		</carousel>
	</v-container>
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
					console.log("hello" + this.recentlist)
				})
				.catch(()=>{})
		}	
	},
	mounted(){
		this.getRecentRecruit()
		alert(this.recentlist[0].company)
	}
};
</script>
<style>
.mw-700 {
	max-width: 700px;
	margin: auto;
}
</style>
