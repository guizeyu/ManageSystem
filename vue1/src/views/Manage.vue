<template>
  <el-container style="min-height: 100vh">
    <el-aside width="sideWith+'px'" style="background-color: rgb(238, 241, 246);">
      <Aside :isCollapsed="isCollapsed" :logoShow="logoShow"/>
    </el-aside>
    <el-container>
      <el-header style=" border-bottom: 1px solid; ">
        <Header :collapseBtnClass="collapseBtnClass" @collapse="collapse"/>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>


</template>

<script>


//import request from "@/utils/request";
//代替后用this.request



import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
  data() {
    return {

      msg: "桂泽宇",
      collapseBtnClass: "el-icon-s-fold",
      isCollapsed: false,
      sideWith: 200,
      logoShow: true,

    };
  },
  components:{
    Aside,
    Header
  },

  methods: {
    collapse() {
      this.isCollapsed = !this.isCollapsed;
      if (this.isCollapsed) {
        this.sideWith = 200;
        this.collapseBtnClass = "el-icon-s-unfold";
        this.logoShow = false;
      } else {
        this.sideWith = 64;
        this.collapseBtnClass = "el-icon-s-fold";
        this.logoShow = true;
      }
    },

  }
}
</script>

<style>
.headColor {
  color: #42b983 !important;
}
</style>
