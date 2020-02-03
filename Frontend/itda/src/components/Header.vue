<template>
  <header
    class="v-sheet v-sheet--tile theme--dark v-toolbar v-app-bar v-app-bar--clipped v-app-bar--fixed v-app-bar--is-scrolled primary"
    id="documentation-app-bar"
    data-booted="true"
    style="height: 64px; margin-top: 0px; transform: translateY(0px); left: 0px; right: 0px;"
  >
    <navbar/>
    <!-- <sidebar/> -->
  </header>
</template>

<script>
import Navbar from './Header/Navbar.vue'
// import Sidebar from './Header/SideBar.vue'
export default {
    name:"header",
    components:{
        Navbar,
        // Sidebar
    }
}
</script>

<style>

</style>