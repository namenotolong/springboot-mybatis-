<template>
  <div>
    <mybar ref="bar"></mybar>
    <div class="container">
      <div>
        <left ref="ch"></left>
      </div>
      <div class="right-container">
        <el-form ref="form" :model="user" label-width="80px" class="form-data">
          <el-form-item>
            <img :src="'http://localhost:8081' + user.picture">
            <el-upload
  class="avatar-uploader"
  action="https://jsonplaceholder.typicode.com/posts/"
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload">
  <img v-if="imageUrl" :src="imageUrl" class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="user.userName"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="user.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="简介">
            <el-input type="textarea" v-model="user.introduction"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="user.age"></el-input>
          </el-form-item>
          <el-form-item label="学校">
            <el-input v-model="user.school"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="user.address"></el-input>
          </el-form-item>
          <el-form-item label="工作">
            <el-input v-model="user.work"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.stop="onSubmit">保存</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import left from "./left"
import mybar from "../mybar"
export default {
  name : "own",
  data() {
    return {
      user: {
          userName: '',
          introduction: '',
          age: '',
          sex: '',
          school: '',
          work: '',
          address: '',
          picture: '',
          visitCount: '',
        },
      imageUrl: ''
    }
  },
  components : {
    'left': left,
    'mybar': mybar
  },
  methods : {
    onSubmit() {
        console.log('submit!');
      },
    setUser(obj) {
      this.user = obj;
    },
     handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
  },
  mounted() {
  },
}
</script>
<style scoped>
.form-data{
  width: 500px;
}
.container{
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 3%;
  display: flex;
  flex-direction: row;
  flex: 1 4 auto;
}
.right-container{
  display: flex;
  flex-direction: row;
}
li{
  list-style-type:none;
  display: list-item;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
