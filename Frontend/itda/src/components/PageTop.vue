<template>
  <div class="pagetop" >
    <navbar/>
    <!-- <sidebar/> -->
  </div>
</template>

<script>
import Navbar from './PageHeader/Navbar.vue'
// import Sidebar from './Header/SideBar.vue'
export default {
    name:"pagetop",
    components:{
        Navbar,
        // Sidebar
    }
}
</script>

<style>

</style>