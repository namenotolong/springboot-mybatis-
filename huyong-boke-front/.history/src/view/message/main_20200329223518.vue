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
              <svg t="1585492419531" class="icon" viewBox="0 0 1025 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4365" width="200" height="200"><path d="M840.222 125.477H182.895c-65.963 0-119.487 53.58-119.487 119.605v391.41c0 65.903 53.523 119.425 119.487 119.425h336.45S658.526 869.21 677.408 883.969c18.883 14.691 26.511 11.376 26.26 0-0.188-11.384-0.128-128.052-0.128-128.052h136.682c66.091 0 119.548-53.522 119.548-119.426V245.082c0.001-66.026-53.457-119.605-119.548-119.605z m-520.709 384.15c-35.327 0-64.024-28.572-64.024-64.025 0-35.45 28.697-64.025 64.024-64.025s64.026 28.576 64.026 64.025c0 35.453-28.698 64.025-64.026 64.025z m192.078 0c-35.328 0-64.026-28.572-64.026-64.025 0-35.45 28.698-64.025 64.026-64.025 35.327 0 64.026 28.576 64.026 64.025 0 35.453-28.7 64.025-64.026 64.025z m192.077 0c-35.324 0-64.024-28.572-64.024-64.025 0-35.45 28.7-64.025 64.024-64.025 35.327 0 64.027 28.576 64.027 64.025 0 35.453-28.701 64.025-64.027 64.025z" p-id="4366" fill="#f4ea2a"></path></svg>
              <span>回复</span>
            </div>
          </div>
          <div class="text-container">
            <div class="text">
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
