<template>
  <div>
    <div class="filter-container">
      <el-button @click="add()">新增</el-button>
    </div>
    <el-table :data="userList">
      <el-table-column type="index" label="序号"></el-table-column>
      <el-table-column prop="avatar" label="头像">
        <template slot-scope="scope">
          <avatar :username="scope.row.userName" :src="scope.row.avatar" :size="40"></avatar>
        </template>
      </el-table-column>
      <el-table-column prop="account" label="手机号"></el-table-column>
      <el-table-column prop="userName" label="用户名"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="admin" label="管理员"></el-table-column>
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
  import Avatar from 'vue-avatar'
  import UserForm from './userForm'

  export default {
    name: 'index',
    components: {
      UserForm,
      Avatar
    },
    data () {
      return {
        userList: [],
        form: {
          show: false,
          state: 'info'
        }
      }
    },
    created () {
      this.listUsers()
    },
    computed: {},
    watch: {
      $route () {
        this.listUsers()
      }
    },
    methods: {
      async listUsers () {
        this.userList = await this.$get('/rbac/list-users')
      },
      edit (item) {
        this.form.state = 'edit'
        this.form.userCode = item.userCode
        this.form.show = true
      },
      add () {
        this.$message.info("djfdsjfdsf");
      }
    }

  }
</script>

<style scoped>
  .filter-container {
    padding-bottom: 10px;
  }
</style>
