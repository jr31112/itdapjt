 <template >
 <div class="d-flex d-sm-none">
 <v-app-bar-nav-icon @click.stop="drawer=!drawer" ><v-icon>menu</v-icon></v-app-bar-nav-icon>
 <!-- <v-list-item link>
             <v-avatar
                  size="30px"
                  class="mx-3"
             >
            <v-img :src="getImgUrl('jungwon.png')"
              />
            </v-avatar>
           <v-list-item-title>Jung Won</v-list-item-title>
</v-list-item> -->
</div>
 </template>    

 <script>
  export default {
    data: () => ({
      drawer: false,
    }),
    methods : {
 
        getImgUrl(img) {
                return require('../../assets/' + img)
        }
    }
  }
 </script>