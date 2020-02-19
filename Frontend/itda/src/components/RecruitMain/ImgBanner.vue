<template>
  <div>
    <v-img :src="imgSrc" id="desk" aspect-ratio="2.0" style="height:60vh; width:100%"></v-img>
    <v-img :src="imgSrc" id="mobile" :contain="true" aspect-ratio="2" max-height="60vh"></v-img>
  </div>
</template>



<script>
export default {
	name: 'imgBanner',
	props: {
		imgSrc: {type: String},
		text: {type: String}
	},
	methods: {
  }
  ,
}
</script>
<style>
  .text-shadow {
    text-shadow: 0 0 15px rgb(255,255,255);
  }
  #desk {
    display: block;
  }
  #mobile {
    display: none;
  }
  @media only screen and (max-width: 600px) {
    #desk {
      display: none;
    }
    #mobile {
      display: block;
    }
  }
</style>