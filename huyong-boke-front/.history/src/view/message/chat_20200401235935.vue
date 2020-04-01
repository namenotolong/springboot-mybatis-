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
                <div class="inner" @click="checkoutUser(item)">
                  <div class="myflexBetween">
                    <div class="myflex">
                      <div>
                        <el-badge :value="item.unReadCount > 0 ? item.unReadCount : null" class="item">
                          <el-avatar :size="40">
                            <img :src="baseUrl + item.picture"/>
                          </el-avatar>
                        </el-badge>
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
                      <span class="grey-color" style="font-size: 1px">{{item.createTime}}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="right-container">
            <div v-show="focusChat" style="height: 100%">
              <div class="chat-header" v-if="focusChat">
                <div class="black-color">
                  <span>{{focusChat.userName}}&nbsp;</span>
                  <span>[离线]</span>
                </div>
              </div>
              <div class="chat-content" v-if="focusChat">
                <div v-for="item in chatRecord" :key="item.id">
                  <div class="message-container left-message">
                    <div v-if="item.toUserId == user.id">
                      <el-avatar :size="30">
                        <img :src="baseUrl + item.picture"/>
                      </el-avatar>
                    </div>
                    <div class="message">
                  <p>{{item.creatTime}}</p>

                      1111111111111111111111111111111111111111111111111111111111111111
                    </div>
                  </div>
                </div>
              </div>
              <div  class="input-content"  contenteditable="true" ref="box">
              </div>
              <div style="float: right;padding-right: 10px;padding-left: 10px">
                <span @click="send">发送</span>
              </div>
            </div>
            <div class="text"  v-show="!focusChat">
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
      loading: true,
      chats: [],
      focusChat: null,
      info: '',
      chatRecord: [],
    }
  },
  methods: {
    //发送消息
    send() {
      let a = this.$refs.box.innerText;
      if(a.trim() == '') {
        return
      }
    },
    //切换用户
    checkoutUser(item) {
      this.focusChat = item;
      this.$refs.box.innerText = '';
      this.getChatRecord();
    },
    setUser(obj) {
      this.user = obj
    },
    //获取聊天列表
    getChatList() {
      this.loading = true;
      this.$axios.get("/history/getUsers").then(response => {
        this.chats = response.data.data
        this.loading = false;
        var time2 = new Date().format("yyyy-MM-dd");
        this.chats.forEach(x => {
          if(x.createTime > time2) {
            x.createTime = new Date(x.createTime).format("HH:mm");
          } else {
            x.createTime = time2;
          }
        })
      })
    },
    //获取聊天记录
    getChatRecord() {
      this.$axios.get("/event/getRecord?id=" + this.focusChat.customer).then(response => {
        this.chatRecord = response.data.data;
        var time2 = new Date().format("yyyy-MM-dd");
        this.chatRecord.forEach(x => {
          if(x.createTime > time2) {
            x.createTime = new Date(x.createTime).format("HH:mm");
          }
        })
        console.log(this.chatRecord)
      })
    }
  },
  mounted() {
    Date.prototype.format=function(fmt) {
            var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
            };
            var week = {
            "0" : "/u65e5",
            "1" : "/u4e00",
            "2" : "/u4e8c",
            "3" : "/u4e09",
            "4" : "/u56db",
            "5" : "/u4e94",
            "6" : "/u516d"
            };
            if(/(y+)/.test(fmt)){
                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            }
            if(/(E+)/.test(fmt)){
                fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
            }
            for(var k in o){
                if(new RegExp("("+ k +")").test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                }
            }
            return fmt;
        }
    this.baseUrl = baseURL
    this.getChatList();
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.left-message{
  margin-left: 10px;
  margin-top: 10px;
}
.message-container{
  display: flex;
}
.message{
  margin-left: 10px;
  width: 10%;
}
.input-content{
  height: 25%;
  outline: none;
  width: 90%;
  float: left;
}
.chat-content{
  height: 65%;
  width: 100%;
  border-bottom: 1px #ebebeb solid;
}
.black-color{
  color: #3d3d3d;
  padding-left: 30px;
  padding-bottom: 10px;
  padding-top: 10px;
}
.chat-header{
  height: 10%;
  border-bottom: 1px #ebebeb solid
}
.inner{
  width: 90%;
  margin-left: 5%;
  margin-right: 5%;
  padding-top: 10px;
  padding-bottom: 10px;
}
.chat-container:hover{
  background-color: #2e2e2e;
}
.chat-container{
  height: 100%;
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
  overflow: scroll;
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
