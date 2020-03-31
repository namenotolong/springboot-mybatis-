<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

        </el-aside>
        <el-container>
          <el-main>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'advice',
  data() {
    return {
      user: {},
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
.out-container{
  width: 60%;
  margin-left: 20%
}
</style>
