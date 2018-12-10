<template>
  <div>
    <el-table :data="roleList">
      <el-table-column type="index" label="序号"></el-table-column>
      <el-table-column prop="roleName" label="角色"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="recordStatus" label="状态"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        roleList: []
      }
    },
    created() {
      this.listRoles();
    },
    watch: {
      $route() {
        this.$get("");
        this.listRoles();
      }
    },
    methods: {
      async listRoles() {
        this.roleList = await this.$get("/rbac/list-roles");
      }
    }


  }
</script>

<style scoped>

</style>
