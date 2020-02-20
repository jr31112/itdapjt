<template>
  <v-row class="recruitrecentlist">
	<v-col class="px-0">
		<v-carousel v-if="recentlist.length" height="auto" hide-delimiters >
			<span><b>따끈따끈</b> 최신공고</span>
			<v-carousel-item v-for="k in 3" :key="k">
				<v-container>
					<v-row v-for="j in 2" :key="j">
						<v-col v-for="i in 4" cols="12" sm="6" lg="3" :key="i" white>
							<recruit-recent-detail :company="recentlist[8*(k-1)+4*(j-1)+(i-1)].company" :wanted="recentlist[8*(k-1)+4*(j-1)+(i-1)].wanted" :stacks="recentlist[8*(k-1)+4*(j-1)+(i-1)].stacks" :scrap="recentlist[8*(k-1)+4*(j-1)+(i-1)].scrap" v-on:update="update"/>
						</v-col>
					</v-row>
				</v-container>
			</v-carousel-item>
		</v-carousel>
	</v-col>
  </v-row>
</template>

<script>
import { mapState } from 'vuex'
import RecruitRecentDetail from './RecruitRecentList/RecruitRecentDetail.vue'
import axios from 'axios'

export default {
	name: 'recruitrecentlist',
	components: {
		RecruitRecentDetail,
	},
	
	data(){
		return{
			recentlist:[
			]
		}
	},
	methods:{
		update(){
			this.$emit('update')
			this.getWantedList()
        },
		getWantedList(){
			var config = {}
			if(localStorage.getItem("access_token") !=null){
				config = {
					headers:
						{
							'jwt-auth-token' : localStorage.getItem("access_token")
						}
				}	
			}
			axios.get(`https://i02b201.p.ssafy.io:8197/itda/api/getWantedByRecent/`,config)
				.then(response=>{
					this.recentlist = response.data
				})
				.catch(()=>{})}
	},
	computed: {
            ...mapState(["isLogin"])
	},
	mounted(){
		this.getWantedList()
	},
	watch:{
		isLogin:{
                deep:true,
                immediate:true,
                handler:'getWantedList'
            }
	}
}
</script>

<style>

</style>
