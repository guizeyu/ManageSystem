<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="输入名称" suffix-icon="el-icon-search" class="mr5"
                v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="输入邮箱" suffix-icon="el-icon-message" class="mr5"
                v-model="email"></el-input>
      <el-input style="width: 200px" placeholder="输入地址" suffix-icon="el-icon-position" class="mr5"
                v-model="address"></el-input>
      <el-button class="ml5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml5" type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          style="padding-left: 10px;padding-right: 10px"
          confirm-button-text='确定'
          cancel-button-text='我再想想吧'
          icon="el-icon-info"
          icon-color="red"
          title="确定删除吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-delete"></i></el-button>
      </el-popconfirm>
      <el-upload action="http://localhost:8081/user/import"
                 :show-file-list="false"
                 accept="xlsx"
                 :on-success="handleExcelImportSuccess"
                  style="display: inline-block">
      <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml5">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headColor"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="email" label="邮箱">
      </el-table-column>
      <el-table-column prop="telephone" label="电话">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
      <el-table-column v-slot="scope">
        <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
        <el-popconfirm
            style="padding-left: 10px"
            confirm-button-text='确定'
            cancel-button-text='我再想想吧'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除吗？"
            @confirm="handleDelete(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除 <i class="el-icon-delete"></i></el-button>
        </el-popconfirm>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2,5,10,15,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户名称" :visible.sync="dialogFormVisible" width="30%">
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

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      tableData: [],
      total: null,
      pageNum: 1,
      pageSize: 10,
      username: "",
      email: "",
      address: "",
      form:{},
      dialogFormVisible: false,
      multipleSelection:[],
      headColor: "headColor"
    }
  },
  created() {
    //请求分页查询数据
    this.load();
  },
  methods:{
    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(
          res => {
            console.log("111");
            console.log(res)
            this.tableData = res.records;
            this.total = res.total;
          }
      )
      // fetch("http://localhost:8081/user/page?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&username=" + this.username
      // ).then(res => res.json()).then(res => {
      //       console.log(res);
      //       this.tableData = res.data;
      //       this.total = res.total;
      //     }
      // )
    },
    save(){
      this.request.post("/user",this.form).then(res =>{
        if (res){
          this.$message.success("修改成功")
          this.dialogFormVisible=false
          this.load()
        }else{
          this.$message.error("修改失败")
          this.dialogFormVisible=false
        }
      })
    },
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row){
      this.form=row;
      this.dialogFormVisible=true
    },
    handleDelete(id){
      this.request.delete("/user/"+id).then(res =>{
        if (res){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
          this.dialogFormVisible=false
        }
      })
    },
    delBatch(){
      let ids=this.multipleSelection.map(val=> val.id)
      this.request.post("/user/del/batch",ids).then(res =>{
        if (res){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSelectionChange(val){
      this.multipleSelection=val;

    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum);
      this.pageNum = pageNum;
      this.load();
    },
    exp(){
      window.open("http://localhost:8081/user/export")
    },
    handleExcelImportSuccess(){
      this.$message.success("导入成功")
      this.load()
    }

  }


}
</script>

<style scoped>

</style>
