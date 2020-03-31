<template>
  <div style="background-color: #f5f6f7">
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
        <div class="myflex">
          <div class="left-container">
            <div class="header">
              <div class="myflexBetween">
                <div class="myflex">
                  <div>
                    <el-avatar :size="50">
                      <img :src="baseUrl + user.picture"/>
                    </el-avatar>
                  </div>
                  <div style="margin-left: 10px">
                    <el-link><span class="white-color">胡勇</span></el-link>
                  </div>
                </div>
                <div style="margin-right: 10px;color: white">
                  <i class="el-icon-s-tools"></i>
                </div>
              </div>
            </div>
          </div>
          <div class="right-container">
            <div class="text">
              <span class="center-color">您还未选中联系人，快去和好友聊天吧！</span>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'chat',
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
  height: 450px;
}
.center-color{
  color: #d6dde2;
}
.white-color{
  color: #e1e1e1;
}
.header{
  margin-top: 5px;
  margin-left: 10px;
  border-bottom: 1px #2e2e2e solid;
}
.right-container{
  height: 550px;
  background-color: white;
  width: 65%;
}
.left-container{
  background-color: #3d3d3d;
  height: 550px;
  width: 28%;
}
.myflex{
  display: flex;
  align-items: center;
}
.myflexBetween{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.out-container{
  margin-top: 20px;
  width: 80%;
  margin-left: 10%;
  margin-right: 10%;
}
</style>
