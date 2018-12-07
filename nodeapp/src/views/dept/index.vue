<template>
  <div style="margin: 10px">
    <el-button type="primary">新增</el-button>
    <el-button>新增</el-button>
    <el-table :data="dataList">
      <el-table-column type="index" label="序号"></el-table-column>
      <el-table-column prop="account" label="部门名称"></el-table-column>
      <el-table-column prop="recordStatus" label="状态"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="edit(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <user-form :show.sync="form.show" :user-code="form.userCode" :state="form.state"></user-form>
  </div>
</template>

<script>
  import Avatar from 'vue-avatar';
  import UserForm from "../user/userForm";

  export default {
    name: "index",
    components: {
      UserForm,
      Avatar
    },
    data() {
      return {
        dataList: [],
        form: {
          show: false,
          state: 'info'
        }
      }
    },
    created() {
      this.listData();
    },
    computed: {
    },
    watch: {
      $route() {
        this.listData();
      }
    },
    methods: {
      async listData() {
        this.dataList = await this.$get("/rbac/list-depts");
      },
      edit(item) {
        this.form.state = 'edit';
        this.form.userCode = item.userCode;
        this.form.show = true;
      }
    }


  }
</script>

<style scoped>

</style>
