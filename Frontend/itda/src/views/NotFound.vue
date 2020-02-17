<template>
    <v-container fill-height>
      <v-layout align-center>
        <v-flex>
          <h1 class="display-3">Page Not Found</h1>
          <span class="subheading">해당 페이지가 존재하지 않아요!!</span>
          <v-divider class="my-3"></v-divider>
          <div class="title mb-3">다른 페이지로 가실래요?</div>
          <v-btn large color="primary" class="mx-0 mr-3" 
            router="router" :to="{name: 'recruitmain'}">
            공고 메인 페이지로 돌아가기</v-btn>
          <v-btn large color="primary" class="mx-0"
          @click="$router.go(-1)"
          >이전 페이지로 돌아가기</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
</template>
<script>
export default {
  name:"notfound"
  ,
  components:
  {
  }
  ,
  methods: 
  {
     
  }
}

</script>
<style>
div#wrapper { margin-left: 500px ;}

</style>


