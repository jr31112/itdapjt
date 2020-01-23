    <template>
    <v-container>
        <carousel :per-page="1" ma-0="ma-0">
            <slide v-for="j in 1" :key="j">
                <v-row dense="dense">
                    <v-col v-for="i in 12" cols="6" lg="2" :key="i">
                        <v-card>
                            <v-img :src="getImgUrl('123.jpg')" height="100px"></v-img>
                        </v-card>
                        <!-- <Recent class="ma" :logImgSrc="fe.Img" :corpNm="fe.corpNm"
                        :jname="fe.jname" :EndDate="EndDate.toString()" > </Recent> -->
                        <!-- <Recent class="ma" :date="recents[i - 1+((j-1)*10)].created_at.toString()"
                        :title="recents[i - 1+((j-1)*10)].title" :body="recents[i - 1+((j-1)*10)].body"
                        :imgSrc="recents[i - 1+((j-1)*10)].img"> </Recent> -->
                    </v-col>
                </v-row>
            </slide>
        </carousel>
    </v-container>
</template>
<script>
//    import Recent from '@/components/Recent'
    // import FirebaseService from '@/services/FirebaseService'

    export default {
        name: 'RecentList',

        // props: {     limits: {         type: Number,         default: 12     },
        // loadMore: {         type: Boolean,         default: false     } }, data() {
        // return {recents: []}  } ,
        components: {
  //          Recent
        },
        methods:{
            getImgUrl(img) {
                return require('../assets/' + img)
            }
        }
        // mounted() {     this.getPortfolios() }, 	methods: 	{ 		     {          async
        // getPortfolios() {              			this.recents = await
        // FirebaseService.getPortfolios() 	  },
        //
        // loadMorePortfolios() 	  {              this.limits = this.limits + 10;
        // if (this.limits >= this.recents.length) {                  this.loadMore =
        // false;              } 	  }     }
    }
</script>
<style>
    .mw-700 {
        max-width: 700px;
        margin: auto;
    }
</style>