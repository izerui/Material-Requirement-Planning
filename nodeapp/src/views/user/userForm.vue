<template>
  <el-dialog :visible.sync="dialogShow" :title="dialogTitle">
    <el-form :model="formItem" ref="form" label-width="80px">
      <el-form-item label="头像">
        <el-upload
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false">
          <avatar v-if="formItem.avatar" :username="formItem.userName" :src="formItem.avatar" :size="40"></avatar>
          <!--<img v-if="selItem.avatar" :src="selItem.avatar" class="avatar">-->
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="formItem.userName"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="formItem.account"></el-input>
      </el-form-item>
      <el-form-item label="管理员">
        <!--<el-input v-model="formItem.admin"></el-input>-->
        <el-switch v-model="formItem.admin" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="formItem.recordStatus" placeholder="请选择">
          <el-option label="正常" :value="1"></el-option>
          <el-option label="禁用" :value="0"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogShow = false">取 消</el-button>
      <el-button type="primary" @click="save()">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import Avatar from "vue-avatar/src/Avatar";

  export default {
    name: "userForm",
    components: { Avatar },
    props: {
      show: {
        type: Boolean,
        default: false
      },
      state: {
        type: String,
        default: 'info'
      },
      userCode: {
        type: String,
        required: false,
        default: ''
      }
    },
    data() {
      return {
        formItem: {
          avatar: '',
          userName: '',
          account: '',
          admin: 0,
          recordStatus: 0
        }
      }
    },
    computed: {
      dialogTitle() {
        switch (this.state) {
          case 'info':
            return '查看用户';
          case 'edit':
            return '编辑用户';
          case 'add':
            return '新增用户';
        }
      },
      dialogShow: {
        get() {
          return this.show;
        },
        set(val) {
          this.$emit('update:show', val);
        }
      }
    },
    watch: {
      dialogShow(newVal) {
        if (newVal) {
          this.$get('/rbac/get-user', { userCode: this.userCode }).then((data) => {
            this.formItem = data;
          });
        }
      }
    },
    methods: {
      save() {
      }
    }
  }
</script>

<style scoped>

</style>
