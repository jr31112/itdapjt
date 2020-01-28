<template>
	
	<v-card >
		<v-img :src="logImgSrc" style="width: auto; height: 120px;"> </v-img>
		<v-card-title primary-title>
			<div>
				<div class="headline over">{{ corpNm }}</div>
				<div class="grey--text">{{ jname }}</div>
				<span class="grey--text">{{ EndDate }}</span>
			</div>
		</v-card-title>
	</v-card>
</template>
<script>
export default {
	name: 'Recent',
	props: {
		logImgSrc: String,
		corpNm: String,
		jname: String,
		EndDate: String,
	},
	data() {
		return {
			//
		};
	},
	methods: {
		getImgUrl(img) {
			return require('../assets/' + img);
		},
	},
};
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
