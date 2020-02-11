<template>
  <v-row class="studylogincontent">
    <v-col>
      <v-carousel v-if="recentlist.length" height="auto" hide-delimiters>
        <v-carousel-item v-for="k in 1" :key="k">
          <v-container>
            <v-row v-for="j in 2" :key="j">
              <v-col v-for="i in 4" cols="12" sm="6" lg="3" :key="i" white>
                <recruit-recent-detail :company="recentlist[8*(k-1)+4*(j-1)+(i-1)].company" :wanted="recentlist[8*(k-1)+4*(j-1)+(i-1)].wanted" :stacks="recentlist[8*(k-1)+4*(j-1)+(i-1)].stacks" :scrap="recentlist[8*(k-1)+4*(j-1)+(i-1)].scrap" v-on:update="update"/>
              </v-col>
            </v-row>
          </v-container>
        </v-carousel-item>
		  </v-carousel>
    </v-col>
  </v-row>
</template>

<script>
export default {
    name:"studylogincontent"
}
</script>

<style>

</style>