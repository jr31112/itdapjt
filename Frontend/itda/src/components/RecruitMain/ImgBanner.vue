<template>
  <div class="world">
      <section class="poster">
      <h1 class="poster-title">IT : DA</h1>
      <v-img :src="imgSrc" max-height= 50% max-width= 50% class="star1" />
      <v-img :src="imgSrc" max-height= 25% max-width= 25% class="star2"/>
      <v-img :src="imgSrc" max-height= 60% max-width= 60% class="star3"/>
      <v-img :src="imgSrc" max-height= 15% max-width= 15% class="star4"/>
     </section>
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
  * {
    margin: 0 auto;
    padding: 0;
   }
   img {
    max-width: 100%;
    height: auto;
   }

   .world {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100vw;
    height: 100vh;
    perspective: 40px}
    @keyframes poster-ani{
    0%{
    transform: rotateY(-20deg);
    }
    100%{
    transform: rotateY(20deg);
    }
   }
   .poster {
    position: relative;
    width: 200px;
    height: 250px;
    border: 2px solid black;
    border-radius: 10px;
    background: black;
    animation: poster-ani 1.2s infinite linear alternate;
    transform-style: preserve-3d;
   }
   .poster-title {
    padding: 0.5em 0;
    font-size: 2em;
    font-weight: bold;
    text-align: center;
    transform: translateZ(30px);
   }
   .marco {
    position: absolute;
    width: 40%;
   }
   .marco:nth-of-type(1) {
    width: 40%;
    left: 5%;
    bottom: 40%;
    transform: translateZ(100px);
   }
   .marco:nth-of-type(2) {
    width: 100%;
    left: 13%;
    bottom: 10%;
    transform: translateZ(50px);
   }
   .marco:nth-of-type(3) {
    width: 100%;
    left: 35%;
    bottom: 1%;
    transform: translateZ(80px);
   }

/* 
   <template>
  <div>
    <v-img :src="imgSrc"
           aspect-ratio="0.5" style="height:60vh; width:100%">
      <v-layout align-center justify-center row fill-height>
        <v-flex text-xs-center>
          <span class="text-shadow display-2 font-weight-light">
            <slot name="text"/>
          </span>
        </v-flex>
      </v-layout>
    </v-img>
  </div>
</template> */
</style>
