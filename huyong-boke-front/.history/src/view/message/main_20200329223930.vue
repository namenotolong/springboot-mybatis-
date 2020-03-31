<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <div class="inner-container">
        <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px">
          <div class="text-container">
            <div class="text">
              <i class="el-icon-chat-dot-round"></i>
              <span>回复</span>
            </div>
          </div>
          <div class="text-container">
            <div class="text">
              <i class="el-icon-message"></i>
              <span>私信</span>
            </div>
          </div>
        </el-aside>
        <el-container>
          <el-main>
          </el-main>
        </el-container>
      </el-container>
      </div>
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
.svg-style{
  width: 30px;
  height: 30px;
}
.text{
  display: -moz-box;/*兼容Firefox*/
  display: -webkit-box;/*兼容FSafari、Chrome*/
  -moz-box-align: center;/*兼容Firefox*/
  -webkit-box-align: center;/*兼容FSafari、Chrome */
  -moz-box-pack: center;/*兼容Firefox*/
  -webkit-box-pack: center;/*兼容FSafari、Chrome */
  width: 100%;
  height: 50px;
  background-color: #f0f0f0;
}
.text-container{
  width: 100%;
  height: 50px
}
.out-container{
  width: 80%;
  margin-left: 10%
}
.inner-container{
  margin-top: 30px
}
</style>
