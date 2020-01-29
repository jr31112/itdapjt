<template>
	<v-card @click="goDetailPage(wanted.wid)">
		<v-img v-if="company.logo" :src="company.logo" :alt="company.corpNm" height="120" :contain="true"></v-img>
		<v-img v-else :src="getImgUrl('noimg.png')" alt="noimg" height="120" :contain="true"></v-img>
		<v-card-title primary-title>
			<div>
				<div class="headline over">{{ company.corpNm }}</div>
				<div class="grey--text">{{ wanted.wantedTitle }}</div>
				<span class="grley--text">{{ wanted.EndDate }}</span>
			</div>
		</v-card-title>
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
.target {
	white-space: nowrap;
	text-overflow: ellipsis;
}
.over {
	text-align: center;
}
</style>