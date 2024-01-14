<template>
  <div style="font-size: 10px;line-height: 60px; display: flex">
    <div style="flex:1;font-size:18px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="childCollapse"></span>
      <el-breadcrumb separator="/" style="display:inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/'" >首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{currentPathName}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style="width: 100px; cursor: pointer">
      <div style="display: inline-block">
        <span>{{user.img_url}}</span>
        <img :src="user.img_url" alt=""
             style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
        <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
<!--      <img :src="user.img_url">-->
<!--      <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
      <el-dropdown-menu slot="dropdown">
        <router-link to="/person" style="text-decoration: none">个人信息</router-link>
        <el-dropdown-item>
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>

</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass: String,
  },
  methods:{
    childCollapse() {
      this.$emit('collapse')
    },
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  },
  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },
  // watch: {
  //   currentPathName (newVal, oldVal) {
  //     console.log(newVal)
  //   }
  // },这个没有用，只是拿来观察的
  data() {
    return {
      user:localStorage.getItem("user")? JSON.parse(localStorage.getItem("user")) :{}
    }
  }
}
</script>

<style scoped>

</style>
