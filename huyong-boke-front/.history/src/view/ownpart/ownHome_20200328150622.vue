<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
      <div class="user-container">
        <div class="user-left">
          <div>
            <el-avatar :size="30">
              <img :src="baseUrl + user.picture"/>
            </el-avatar>
          </div>
          <div>
            <span>{{user.userName}}</span>
          </div>
        </div>
        <div>
          <div v-if="user.id !== other.id">
            <el-button>私信</el-button>
            <el-button>关注</el-button>
            <el-button>取消关注</el-button>
          </div>
        </div>
      </div>
      <div class="kind-container">
        <el-menu default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1">文章</el-menu-item>
          <el-menu-item index="2">评论</el-menu-item>
          <el-menu-item index="3">关注</el-menu-item>
          <el-menu-item index="4">粉丝</el-menu-item>
        </el-menu>
        <div class="line"></div>
      </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'ownHome',
  data() {
    return {
      user: {},
      other: {},
      baseUrl: '',
    }
  },
  methods: {
    setUser(obj) {
      this.user = obj
    },
    handleSelect(key, keyPath) {
        console.log(key, keyPath);
    }
  },
  mounted() {
    this.baseUrl = baseURL
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.user-left{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.user-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.out-container{
  width: 50%;
  margin-left: 20%;
  margin-top: 30px
}
</style>
