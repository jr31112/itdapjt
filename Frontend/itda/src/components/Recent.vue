<template>
	<v-card>
		<div v-if="company.logo"><v-img :src="company.logo" :alt="company.corpNm" style="width: auto; height: 120px;"></v-img></div>
		<div v-else>공고 이미지가 없습니다.</div>
		<v-card-title primary-title>
			<div>
				<div class="headline over">{{ company.corpNm }}</div>
				<div class="grey--text">{{ wanted.wantedTitle }}</div>
				<span class="grey--text">{{ wanted.EndDate }}</span>
			</div>
		</v-card-title>
	</v-card>
</template>
<script>
export default {
	name: 'Recent',
	props: {
		company:{type:Object},
		wanted:{type:Object}
	},
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