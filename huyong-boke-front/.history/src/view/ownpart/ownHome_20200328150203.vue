<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
      <div class="user-container">
        <div>
          <el-avatar :size="30">
            <img :src="baseUrl + user.picture"/>
          </el-avatar>
          <span>{{user.userName}}</span>
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
.user-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.out-container{
  width: 50%;
  margin-left: 20%;
  margin-top: 10%
}
</style>
