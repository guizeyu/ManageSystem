<template>
  <div style="width: 500px;margin: 100px auto">
    <el-form label-width="70px" size="small">
      <el-form-item label="用户名">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.telephone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.request.get("/user/username/" + this.user.username).then(res => {
          if (res.code === '200') {
            this.form = res.data
          }
        }
    )
  },
  methods: {
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("修改成功")
        } else {
          this.$message.error("修改失败")
        }
      })
    }
  }


}
</script>

<style scoped>

</style>
