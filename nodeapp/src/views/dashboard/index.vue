<template>
  <div class="dashboard-container">
    <el-button type="primary" @click="getSession">获取session</el-button>
    <el-form>
      <el-form-item label="name:">
        <el-input v-model="myName" placeholder="请输入名字"></el-input>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="getAction">GET</el-button>
    <el-button type="primary" @click="putAction">PUT</el-button>
    <el-button type="primary" @click="postAction">POST</el-button>
    <el-button type="primary" @click="deleteAction">DELETE</el-button>
    <el-button type="primary" @click="postBodyAction">POST_BODY</el-button>
    <el-button type="primary" @click="patchAction">PATCH</el-button>
    <el-button type="primary" @click="putBodyAction">PUT_BODY</el-button>
  </div>
</template>

<script>
  export default {
    name: 'dashboard',
    data() {
      return {
        myName: null
      }
    },
    methods: {
      async getAction() {
        const data = await this.$get("/context/test-param", this.params);
        this.$message.info(data);
      },
      async postAction() {
        const data = await this.$post("/context/test-param", this.params);
        this.$message.info(data);
      },
      async deleteAction() {
        const data = await this.$delete("/context/test-param", this.params);
        this.$message.info(data);
      },
      async postBodyAction() {
        const data = await this.$post("/context/post-body", this.params, true);
        this.$message.info(data);
      },
      async putAction() {
        const data = await this.$put("/context/test-param", this.params);
        this.$message.info(data);
      },
      async putBodyAction() {
        const data = await this.$put("/context/put-body", this.params, true);
        this.$message.info(data);
      },
      async patchAction() {
        const data = await this.$patch("/context/test-param", this.params);
        this.$message.info(data);
      },
      async getSession() {
        const sessionId = await this.$get("/context/session");
        this.$message.info(sessionId);
      }
    },
    created() {
    },
    computed: {
      params() {
        return { name: this.myName };
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }
  }
</style>
