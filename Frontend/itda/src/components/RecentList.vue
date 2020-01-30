<template>
  <v-row class="recentlist">
	<v-col class="px-0">
		<h2>앗! 뜨거~ 요즘 최신공고 </h2>
		<v-carousel v-if="recentlist.length" height="auto" hide-delimiters>
			<v-carousel-item v-for="k in 2" :key="k">
				<v-container>
					<v-row v-for="j in 2" :key="j">
						<v-col v-for="i in 4" cols="6" lg="3" :key="i" white>
							<v-hover v-slot:default="{ hover }">
								<Recent :company="recentlist[8*(k-1)+4*(j-1)+(i-1)].company" :wanted="recentlist[6*(j-1)+(i-1)].wanted"/>
							</v-hover>
						</v-col>
					</v-row>
				</v-container>
			</v-carousel-item>
		</v-carousel>
	</v-col>
  </v-row>
</template>

<script>
import Recent from './Recent.vue';
import axios from 'axios'

export default {
	name: 'RecentList',
	components: {
		Recent,
	},
	
	data(){
		return{
			recentlist:[
			]
		}
	},
	methods:{
		getWantedList(){
			axios.get(`http://192.168.31.54:8197/itda/api/getWantedByRecent/`)
				.then(response=>{
					this.recentlist = response.data
				})
				.catch(()=>{})
		},
	},
	mounted(){
		this.getWantedList()
	}
}
</script>

<style>

</style>
