<template>
	<v-hover v-slot:default="{ hover }">
		<v-card light align-center height="260" :elevation="hover ? 16 : 2" class="mx-auto">
			<v-container>
				<v-row align="center" style="cursor:pointer">
					<v-col v-if="!isLogin" @click="goDetailPage(wanted.wid)">
						<v-img v-if="company.logo" :src="company.logo" :alt="company.corpNm" height="60" :contain="true"></v-img>
						<v-img v-else :src="getImgUrl('noimg.png')" alt="noimg" height="60" :contain="true"></v-img>
					</v-col>
					<v-col v-if="isLogin" cols="10" @click="goDetailPage(wanted.wid)">
						<v-img v-if="company.logo" :src="company.logo" :alt="company.corpNm" height="60" :contain="true"></v-img>
						<v-img v-else :src="getImgUrl('noimg.png')" alt="noimg" height="60" :contain="true"></v-img>
					</v-col>
					<v-col v-if="isLogin && scrap" class="px-0" cols="1"><v-icon color="yellow" @click.prevent="doScrap(wanted.wid)">star</v-icon></v-col>
					<v-col v-if="isLogin && !scrap" class="px-0" cols="1"><v-icon color="grey lighten-2" @click.prevent="doScrap(wanted.wid)">star_border</v-icon></v-col>
				</v-row>
				<v-divider></v-divider>
				<v-row style="cursor:pointer;" @click="goDetailPage(wanted.wid)">
					<v-col class="text-left pr-0" cols="9" id="headline">{{ company.corpNm }}</v-col>
					<v-col class="text-right pl-0" cols="3">{{ leftDate }}</v-col>
				</v-row>
				<v-row style="cursor:pointer;" @click="goDetailPage(wanted.wid)">
					<v-col class="pt-0 text-left grey--text" style="height:48px">{{ wanted.wantedTitle }}</v-col>
				</v-row>
				<v-row>
					<v-col class="pt-1" v-if="stacks.length"><span v-for="i in stacks.length > len ? len: stacks.length" :key="i" style="color:blue;cursor:pointer;" @click="goSearch(stacks[i-1].tname)">#{{stacks[i-1].tname}} </span></v-col>
				</v-row>
			</v-container>
		</v-card>
	</v-hover>
</template>
<script>
import { mapState } from 'vuex'
import axios from 'axios'
import router from '../../../router'

export default {
	name: 'recruitrecentdetail',
	props: {
		company:{type:Object},
		wanted:{type:Object},
		stacks:{type:Array},
		scrap:{type:Boolean}
	},
	data(){
		return{
			overlay:false,
			leftDate:'',
			len: 3
		}
	},
	methods:{
	  getImgUrl(img) {
	    return require('../../../assets/' + img)
	  },
	  goSearch(content)
	  {
	    router.push({name:"searchresult", query:{cont: content, type:3}})
	  },
	  goDetailPage(wid){
        router.push({name:'recruitdetail',params:{id:wid}})
	  },
	  doScrap(wid){
		  axios.post('https://i02b201.p.ssafy.io:8197/itda/api/scrapWanted/',{"wid":wid},{'headers' : {"jwt-auth-token": localStorage.getItem("access_token")}})
            .then(()=>{
				this.$emit('update')})
	  },
	},
	computed: {
            ...mapState(["isLogin"])
	},
	mounted(){
		if (this.wanted.endDate.slice(0,4) == "2037"){
			this.leftDate = "상시"
		}
		else{
			var endday = new Date(this.wanted.endDate)
			var today = new Date()
			var datediff = parseInt((endday-today)/(24*60*60*1000))
			if (datediff > 0)
				this.leftDate = "D-"+ datediff
			
			else
				if (datediff == 0)
					this.leftDate = "D-day"
				else
					this.leftDate = "마감"
		}
	}
}
</script>
<style>
#headline {
	font-weight: bold;
	margin-bottom: 0px;
	overflow: hidden;
  	text-overflow: ellipsis;
  	display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
}
</style>