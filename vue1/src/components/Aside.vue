<template>

  <el-menu :default-openeds="['1', '3']" style=" min-height: 100% ; overflow-x: hidden"
           background-color="#2F4050"
           text-color="#9CB4CC"
           active-text-color="yellow"
           :collapse-transition="false"
           :collapse=isCollapsed
           router
           @select="handleSelect"
  >
    <div style="height: 60px;line-height:60px;text-align: center">
      <img src="../assets/logo.png" style="width: 20px; position: relative ;top:5px;margin-right: 2px">
      <b style="color: white" v-show="logoShow">后台管理系统</b>
    </div>

    <el-submenu index="1" >
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title" >主页</span>
      </template>
      <el-menu-item index="/">
        <template slot="title">
          <i class="el-icon-house"></i>
          <span slot="title" class="text">主页</span>
        </template>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title" ><i class="el-icon-menu"></i>
      <span slot="title" class="text">后台管理</span></template>

      <el-menu-item index="/user">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span slot="title" class="text">用户管理</span>
        </template>
      </el-menu-item>
    </el-submenu>

  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapsed: Boolean,
    logoShow: Boolean
  },
  methods:{
    handleSelect(index){

    }

  }
}
</script>

<style scoped>




</style>
