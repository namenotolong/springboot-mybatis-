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
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
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
        }
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
</style>
