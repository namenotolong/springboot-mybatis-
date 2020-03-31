<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <div class="inner-container">
        <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px">
          <div :class="{whoshow : index == 1}" @click="checkIndex(1)">
            <div class="text-container">
              <div class="text">
                <i class="el-icon-message"></i>
                <el-badge :value="replyCount == 0 ? null : replyCount" class="item">
                  <span>回复</span>
                </el-badge>
              </div>
            </div>
          </div>
          <div :class="{whoshow : index == 2}" @click="checkIndex(2)">
            <div class="text-container">
              <div class="text">
                <i class="el-icon-chat-dot-round"></i>
                <el-badge :value="messageCount == 0 ? null : messageCount" class="item">
                  <span>私信</span>
                </el-badge>
              </div>
            </div>
          </div>
          <div :class="{whoshow : index == 3}" @click="checkIndex(3)">
            <div class="text-container">
              <div class="text">
                <i class="el-icon-magic-stick"></i>
                <el-badge :value="followCount == 0 ? null : followCount" class="item">
                  <span>关注</span>
                </el-badge>
              </div>
            </div>
          </div>
          <div :class="{whoshow : index == 4}" @click="checkIndex(4)">
            <div class="text-container">
              <div class="text">
                <i class="el-icon-s-opportunity"></i>
                <el-badge :value="praiseCount == 0 ? null : praiseCount" class="item">
                  <span>赞</span>
                </el-badge>
              </div>
            </div>
          </div>
          <div :class="{whoshow : index == 5}" @click="checkIndex(5)">
            <div class="text-container">
              <div class="text">
                <i class="el-icon-setting"></i>
                <el-badge :value="systemCount == 0 ? null : systemCount" class="item">
                  <span>系统管理</span>
                </el-badge>
              </div>
            </div>
          </div>
        </el-aside>
        <el-container>
          <el-main>
            <div class="content-container">
              <div v-if="index == 1">
                <b>收到的回复</b>
                <div class="reply-container" v-for="(item, key) in replies" :key="item.id">
                  <div class="myflex">
                    <div>
                      <el-avatar :size="30">
                        <img :src="baseUrl + item.user.picture"/>
                      </el-avatar>
                    </div>
                  </div>
                  <div>
                    <div>
                      <span></span>
                    </div>
                  </div>
                </div>
              </div>
              <div v-if="index == 2">

              </div>
            </div>
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
      //初始定位回复
      index: 1,
      //回复
      replies: [],
      replyPageNum: 1,
      //follow
      follows: [],
      followPageNum: 1,
      //praise
      praises: [],
      praisePageNum: 1,
      //system
      systems: [],
      systemPageNum: 1,

      replyCount: 0,
      praiseCount: 0,
      followCount: 0,
      systemCount: 0,
      messageCount: 0,
    }
  },
  methods: {
    //清0未读消息数量
    setZero(type) {

    },
    //获取各项的未读消息数量
    getUnReadCounts() {
      this.$axios.get("/event/unReadCountMap").then(response => {
        this.messageCount = response.data.data.message;
        this.systemCount = response.data.data.system;
        this.praiseCount = response.data.data.praise;
        this.replyCount = response.data.data.reply;
        this.followCount = response.data.data.follow;
      })
    },
    //切换回复和私信
    checkIndex(index) {
      this.index = index
    },
    //获取回复
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
    this.getUnReadCounts();
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.reply-container{
  margin-left: 10px;
}
.myflex{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.content-container{
}
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
}
.text-container{
  width: 100%;
  height: 60px
}
.text-container:hover{
  background-color: #f0f0f0;
}
.whoshow{
  background-color: #f0f0f0;
}
.out-container{
  width: 80%;
  margin-left: 10%
}
.inner-container{
  margin-top: 30px
}
</style>
