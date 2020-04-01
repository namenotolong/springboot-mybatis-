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
            <div class="text" v-if="loading">
              <span class="center-color">正在获取联系人……</span>
            </div>
            <div v-else>
              <div v-for="item in chats" :key="item.id" class="chat-container">
                <div class="inner">
                  <div class="myflexBetween">
                    <div class="myflex">
                      <div>
                        <el-avatar :size="40">
                          <img :src="baseUrl + item.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 10px">
                        <div>
                          <span class="white-color">{{item.userName}}</span>
                        </div>
                        <div>
                          <span class="grey-color" style="font-size: 4px">[离线]</span>
                        </div>
                      </div>
                    </div>
                    <div>
                      <span class="grey-color" style="font-size: 1px">{{item.updateTime}}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="right-container" v-if="loading">
            <div class="text">
              <span class="center-color">您还未选中联系人，快去和好友聊天吧！</span>
            </div>
          </div>
          <div v-else>

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
      loading: true,
      chats: [],
      focusChat: []
    }
  },
  methods: {
    setUser(obj) {
      this.user = obj
    },
    //获取聊天列表
    getChatList() {
      this.loading = true;
      this.$axios.get("/event/getChatList").then(response => {
        this.chats = response.data.data
        console.log(this.chats)
        this.loading = false;
      })
    },
  },
  mounted() {
    this.baseUrl = baseURL
    this.getChatList();
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.chat{
  margin-left: 15px;
  margin-top: 15px;
  margin-bottom: 15px;
}
.chat-container:hover{
  background-color: #2e2e2e;
}
.chat-container{
  height: 100%
}
.grey-color{
  color: #999999
}
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
  background-color: #2e2e2e;
  border-bottom: 1px #666666 solid;
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
