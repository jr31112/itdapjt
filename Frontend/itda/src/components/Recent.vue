<template>
  <v-card>
    <v-img :src="logImgSrc" height="40px">
    </v-img>
    <v-card-title primary-title >
      <div>
        <div class="headline">{{corpNm}}</div>
        <span class="grey--text">{{jname}}</span>
		<span class="grey--text">{{EngDate}}</span>
      </div>
    </v-card-title>
  </v-card>
</template>

<script>
export default {
	name: 'Recent',
	props: {
		logImgSrc: {type: String},
		corpNm: {type: String},
		jname: {type: String},
		EngDate: {type: String}
	},
	data() {
		return {
			//
		}
	}
}
</script>
<style >
.target {  white-space: nowrap; text-overflow: ellipsis; }

</style>
