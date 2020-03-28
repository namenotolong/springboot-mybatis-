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
              <img :src="baseUrl + other.picture"/>
            </el-avatar>
          </div>
          <div>
            <span>{{other.userName}}</span>
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
    //获取当前浏览的用户信息
    getOther() {
      if(this.user.id) {
        if(this.user.id === this.$route.param.id) {
          this.other = this.user;
        }
      }
    },
    setUser(obj) {
      this.user = obj
      this.getOther();
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
.user-left > div{
  margin-right: 20px
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
