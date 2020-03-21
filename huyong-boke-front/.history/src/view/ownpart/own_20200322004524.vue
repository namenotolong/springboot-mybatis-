<template>
  <div>
    <mybar ref="bar"></mybar>
    <div class="container">
      <div>
        <left ref="ch"></left>
      </div>
      <div class="right-container">
        <el-form ref="user" :rules="rules" :model="user" label-width="80px" class="form-data">
          <el-form-item>
            <div class="bar-picture">
              <div>
                <el-upload
                  id="fileInput"
                  class="avatar-uploader"
                  :action="doUpload"
                  :headers="headers"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload">
                  <div class="circular--landscape">
                    <img v-if="user.picture" :src="baseURL + user.picture" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </div>
                  <el-button size="small" type="success" v-if="!isUpload">更换头像</el-button>
                  <span v-else>上传中……</span>
                </el-upload>
              </div>
            </div>
          </el-form-item>
           <el-form-item label="昵称" prop="userName">
            <el-input v-model="user.userName" maxlength="20"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="user.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="简介">
            <el-input type="textarea" v-model="user.introduction" maxlength="30"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="user.age"></el-input>
          </el-form-item>
          <el-form-item label="学校">
            <el-input v-model="user.school" maxlength="30"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="user.address" maxlength="50"></el-input>
          </el-form-item>
          <el-form-item label="工作">
            <el-input v-model="user.work" maxlength="30"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.stop="onSubmit('user')">保存</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import left from "./left"
import mybar from "../mybar"
import {baseURL} from "../../utils/utils"
import {getStore} from "../../utils/utils"
export default {
  name : "own",
  data() {
    var validateUserName = (rule, value, callback) => {
      let reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
        if (value === '') {
          callback(new Error('请输入昵称'));
        } else if (reg.test(value)) {
          callback(new Error('用户名不能是邮箱格式'));
        } else if (this.tempName === this.user.userName) {
          callback();
        } else {
          this.$refs.bar.checkAccountUnique(value, 1).then(response => {
            if (!response.data.data) {
              callback('该昵称已被使用');
            } else {
              callback();
            }
          })
        }
      };
    return {
      doUpload:'/file/uploadBar',
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
      baseURL: '',
      isUpload: false,
      headers: {},
      rules: {
          userName: [
            { validator: validateUserName, trigger: 'blur' }
          ]
        },
      tempName: '',
    }
  },
  components : {
    'left': left,
    'mybar': mybar
  },
  methods : {
    onSubmit(user) {
      this.$refs[user].validate((valid) => {
          if (valid) {
            this.user.ops = 1
            this.$axios.post("/user/updateOrInsert", this.user).then(response => {
              this.$refs.bar.getUser();
              this.$message({
                message: '保存成功',
                type: 'success'
              });
            })
          } else {
            return false;
          }
      });
    },
    //子组件给父组件user复制
    setUser(obj) {
      this.user = obj;
    },
    //上传成功
    handleAvatarSuccess(res, file) {
      this.isUpload = false
      this.$refs.bar.getUser();
      this.$message({
                message: '上传成功',
                type: 'success'
      });
    },
    //上传之前检测文件格式
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
          return false;
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
          return false;
        }
        this.isUpload = true;
        return isJPG && isLt2M;
      }
  },
  mounted() {
    //获取服务器根地址
    this.baseURL = baseURL
    //设置上传接口
    this.doUpload = this.baseURL + this.doUpload
    let token = getStore("token")
    //为上传request增加token
    this.headers = {"authorization" : token}
  },
}
</script>
<style scoped>
.circular--landscape{
    display:inline-block;
    position:relative;
    width:50px;
    height:50px;
    overflow:hidden;
    border-radius:50%;
}

.circular--landscape img{
    width:auto;
    height:100%;
}
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
.bar-picture{
  display: flex;
  flex-direction: row;
  align-items: center;
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
    display: block;
  }
</style>
