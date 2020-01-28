<template>
	<v-container align-content >
		<carousel :per-page="1" :paginationEnabled="false">
			<slide v-for="j in 2" :key="j" xs6 >
				<v-row>
					<v-col v-for="i in 12" cols="6" lg="2" :key="i">
						<Recent
							v-bind:corpNm="recents[i - 1 + (j - 1) * 12].corpNm"
							v-bind:jname="recents[i - 1 + (j - 1) * 12].jname"
							v-bind:EndDate="recents[i - 1 + (j - 1) * 12].EndDate"
							v-bind:logImgSrc="
								getImgUrl(recents[i - 1 + (j - 1) * 12].logImgSrc)
							"
						>
						</Recent>
						<!-- <v-card>
                            <v-img :src="getImgUrl('123.jpg')" height="100px"></v-img>
                        </v-card>  -->
						<!-- <Recent logImgSrc:recents[i].  logImgSrc corpNm:recents[i].corpNm 
                        jname:recents[i].jname EndDate:recents[i].EndDate></Recent>  -->
						<!-- <Recent v-bind:corpNm="recents[i].corpNm" 
                            v-bind:jname="recents[i].jname" v-bind:EndDate="recents[i].EndDate"></Recent>      -->

						<!-- <Recent :date="recents[i - 1+((j-1)*10)].EndDate"
                        :title="recents[i - 1+((j-1)*10)].jname" :body="recents[i - 1+((j-1)*10)].corpNm"
                        :imgSrc="recents[i - 1+((j-1)*10)].logImgSrc"> 
                        </Recent> -->
					</v-col>
				</v-row>
			</slide>
		</carousel>
	</v-container>
</template>
<script>
import { Carousel, Slide } from 'vue-carousel';
import Recent from '@/components/Recent';
// import FirebaseService from '@/services/FirebaseService'
export default {
	name: 'RecentList',
	// props: {     limits: {         type: Number,         default: 12     },
	// loadMore: {         type: Boolean,         default: false     } }, data() {
	// return {recents: []}  } ,
	components: {
		Recent,
		Carousel,
		Slide,
	},
	methods: {
		getImgUrl(img) {
			return require('../assets/' + img);
		},
	},
	data() {
		return {
			recents: [
				{
					logImgSrc: '123.jpg',
					corpNm: '1',
					jname: 'KB국민은행',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '2',
					jname: 'KB국민은행1',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '3',
					jname: 'KB국민은행3',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '4',
					jname: 'KB국민은행4',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '5',
					jname: 'KB국민은행5',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '6',
					jname: 'KB국민은행6',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '7',
					jname: 'KB국민은행7',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '8',
					jname: 'KB국민은행8',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '9',
					jname: 'KB국민은행9',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '10',
					jname: 'KB국민은행10',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '11',
					jname: 'KB국민은행19',
					EndDate: '2020-02-24',
				},
				{
					logImgSrc: '123.jpg',
					corpNm: '12',
					jname: 'KB국민은행12',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행1',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행2',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행3',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행4',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행5',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행6',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행7',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행8',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행9',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행10',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행11',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
				{
					jname: 'NH농협은행12',
					logImgSrc: '123.jpg',
					corpNm: '1',
					EndDate: '2020-02-24',
				},
			],
		};
	},
};
</script>
<style>
.mw-700 {
	max-width: 700px;
	margin: auto;
}
</style>
