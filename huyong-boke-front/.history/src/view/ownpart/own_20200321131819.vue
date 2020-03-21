<template>
  <div>
    <div class="container">
      <div>
        <left></left>
      </div>
      <div class="right-container">
        <el-form ref="form" :model="form" label-width="80px" class="form-data">

          <el-form-item>
            <img src='../../img/2.png'>
            <el-button type="sucess">更换头像</el-button>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.gender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="简介">
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
          <el-form-item label="学校">
            <el-input v-model="form.school"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="工作">
            <el-input v-model="form.work"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">保存</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import left from "./left"
export default {
  name : "own",
  data() {
    return {
      form: {
          name: '',
          desc: '',
          age: '',
          gender: '',
          school: '',
          work: '',
          address: '',
          picture: ''
        }
    }
  },
  components : {
    left
  },
  methods : {
    onSubmit() {
        console.log('submit!');
      }
  }
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
