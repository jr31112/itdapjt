<template>
	<v-hover v-slot:default="{ hover }">
		<v-card @click="goDetailPage(wanted.wid)" light align-center height="230" :elevation="hover ? 16 : 2" class="mx-auto">
			<v-container>
				<v-row align="center">
					<v-col cols="10">
						<v-img v-if="company.logo" :src="company.logo" :alt="company.corpNm" height="60" :contain="true"></v-img>
						<v-img v-else :src="getImgUrl('noimg.png')" alt="noimg" height="60" :contain="true"></v-img>
					</v-col>
					<v-col class="px-0" cols="1"><v-icon color="yellow">star</v-icon></v-col>
				</v-row>
				<v-divider></v-divider>
				<v-row>
					<v-col class="text-left" cols="9" id="headline">{{ company.corpNm }}</v-col>
					<v-col class="text-right" cols="3">{{ leftDate }}</v-col>
				</v-row>
				<v-row>
					<v-col class="pt-0 text-left grey--text" style="height:48px">{{ wanted.wantedTitle }}</v-col>
				</v-row>
				<v-row>
					<v-col class="pt-1" v-if="stacks.length"><span v-for="i in stacks.length > len ? len: stacks.length" :key="i" style="color:blue">#{{stacks[i-1].tname}} </span></v-col>
				</v-row>
			</v-container>
		</v-card>
	</v-hover>
</template>
<script>
import router from '../router'

export default {
	name: 'Recent',
	props: {
		company:{type:Object},
		wanted:{type:Object},
		stacks:{type:Array}
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
	    return require('../assets/' + img)
	  },
	  goDetailPage(wid){
        router.push({name:'recruitdetail',params:{id:wid}})
	  },
	},
	mounted(){
		if (this.wanted.endDate.slice(0,4) == "2037"){
			this.leftDate = "상시"
		}
		else{
			var endday = new Date(this.wanted.endDate)
			var today = new Date()
			var datediff = parseInt((endday-today)/(24*60*60*1000))
			if (datediff >= 0)
				this.leftDate = "D-"+ datediff
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