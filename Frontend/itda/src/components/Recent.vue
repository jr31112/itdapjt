<template>
	<v-card @click="goDetailPage(wanted.wid)" light align-center height="200">
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
				<v-col class="text-left">{{ company.corpNm }}</v-col>
			</v-row>
			<v-row>
				<v-col class="pt-0 text-left grey--text">{{ wanted.wantedTitle }}</v-col>
			</v-row>
		</v-container>
	</v-card>
</template>
<script>
import router from '../router'

export default {
	name: 'Recent',
	props: {
		company:{type:Object},
		wanted:{type:Object}
	},
	methods:{
	  getImgUrl(img) {
	    return require('../assets/' + img)
	  },
	  goDetailPage(wid){
        	router.push({name:'recruitdetail',params:{id:wid}})
    	}
	}
}
</script>
<style>
#headline {
	font-size:0.9rem;
	font-weight: bold;
	margin-bottom: 0px;
	text-align: center;
}
</style>