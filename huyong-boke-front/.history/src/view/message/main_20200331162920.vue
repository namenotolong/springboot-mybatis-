<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <div class="inner-container">
        <el-container style="height: 600px; border: 1px solid #eee">
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
                <b>收到的评论与回复</b>
                <div v-if="replies.count">
                  <div class="reply-container" v-for="item in replies.data" :key="item.id">
                    <div class="myflex-between">
                      <div class="myflex">
                        <div>
                          <el-avatar :size="30">
                            <img :src="baseUrl + item.picture"/>
                          </el-avatar>
                        </div>
                        <div style="margin-left: 10px">
                          <div>
                            <el-link type="primary" @click="goOwn(item.fromUserId)"><b class="user">{{item.userName}}</b></el-link>
                          </div>
                          <div>
                            <span class="date-style">{{item.updateTime}}</span>
                          </div>
                        </div>
                      </div>
                      <div>
                          <el-link><span class="date-style" @click="item.status = 1">回复</span></el-link>
                      </div>
                    </div>
                    <div>
                      <a><span>{{item.content}}</span></a>
                    </div>
                    <div v-if="item.topicId">
                    <el-link type="primary" @click="goOwn(user.id)"><b class="user">{{user.userName}}</b></el-link><span>:&nbsp;{{item.replyContent}}</span>
                    </div>
                    <div>
                      <span><span class="date-style">博文：</span><b><el-link type="info" @click="goView(item.articleId)">{{item.title}}</el-link></b></span>
                    </div>
                    <div class="myflex" v-if="item.status">
                      <div style="width: 200%">
                            <el-input
                            style="width: 600px"
                            type="text"
                            :rows="2"
                            placeholder="请输入内容"
                            v-model="item.willReply">
                            </el-input>
                      </div>
                      <div>
                        <el-button @click="reply(item)" round type="success" :disabled="!item.willReply || item.willReply.trim().length === 0">回复</el-button>
                      </div>
                      <div>
                        <el-button round type="info" @click="item.status = 0">取消</el-button>
                      </div>
                    </div>
                  </div>
                </div>
                <div v-else>
                  <h2>暂无</h2>
                </div>
              </div>
              <div v-if="index == 2">
              </div>
              <div v-if="index == 3">
                <b>新的粉丝</b>
                <div v-if="follows.count">
                  <div class="reply-container" v-for="item in follows.data" :key="item.id">
                    <div class="myflex-between">
                      <div class="myflex">
                        <div>
                          <el-avatar :size="30">
                            <img :src="baseUrl + item.picture"/>
                          </el-avatar>
                        </div>
                        <div style="margin-left: 10px">
                          <div>
                            <el-link type="primary" @click="goOwn(item.fromUserId)"><b class="user">{{item.userName}}</b></el-link>
                          </div>
                          <div>
                            <span class="date-style">{{item.updateTime}}</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div v-else>
                  <h2>暂无</h2>
                </div>
              </div>
              <div v-if="index == 4">
                <b>收到的赞</b>
                <div v-if="praises.count">
                  <div class="reply-container" v-for="item in praises.data" :key="item.id">
                    <div class="myflex-between">
                      <div class="myflex">
                        <div>
                          <el-avatar :size="30">
                            <img :src="baseUrl + item.picture"/>
                          </el-avatar>
                        </div>
                        <div style="margin-left: 10px">
                          <div>
                            <el-link type="primary" @click="goOwn(item.fromUserId)"><b class="user">{{item.userName}}</b></el-link>
                          </div>
                          <div>
                            <span class="date-style">{{item.updateTime}}</span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div>
                      <span v-if="item.topicId">赞了你的评论</span>
                    </div>
                    <div v-if="item.topicId">
                    <el-link type="primary" @click="goOwn(user.id)"><b class="user">{{user.userName}}</b></el-link><span>:&nbsp;{{item.replyContent}}</span>
                    </div>
                    <div>
                      <span><span class="date-style">博文：</span><b><el-link type="info" @click="goView(item.articleId)">{{item.title}}</el-link></b></span>
                    </div>
                    <div class="myflex" v-if="item.status">
                      <div style="width: 200%">
                            <el-input
                            style="width: 600px"
                            type="text"
                            :rows="2"
                            placeholder="请输入内容"
                            v-model="item.willReply">
                            </el-input>
                      </div>
                      <div>
                        <el-button @click="reply(item)" round type="success" :disabled="!item.willReply || item.willReply.trim().length === 0">回复</el-button>
                      </div>
                      <div>
                        <el-button round type="info" @click="item.status = 0">取消</el-button>
                      </div>
                    </div>
                  </div>
                </div>
                <div v-else>
                  <h2>暂无</h2>
                </div>
              </div>
              <div v-if="index == 5">
                <b>系统消息</b>
                <div v-if="systems.count">

                </div>
                <div v-else>
                  <h2>暂无</h2>
                </div>
              </div>
            </div>
            <div v-if="replies.data" style="margin-top: 30px">
              <el-pagination
                @small="replies.maxPage < 7"
                background
                @current-change="changePage"
                layout="prev, pager, next"
                :total="replies.maxPage">
              </el-pagination>
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
      pageNum: 1,
      //回复
      replies: [],
      //follow
      follows: [],
      //praise
      praises: [],
      //system
      systems: [],
      replyCount: 0,
      praiseCount: 0,
      followCount: 0,
      systemCount: 0,
      messageCount: 0,
    }
  },
  methods: {
    changePage(page) {
      this.pageNum = page;
      this.getEvents(this.index - 1, page)
    },
    //回复
    reply(topic){
      let obj = {};
      obj.type = 1
      obj.articleId = topic.articleId
      obj.topicId = topic.rootId;
      obj.parentId = topic.topicId;
      obj.content = topic.willReply;
      obj.toUserId = topic.fromUserId;
      topic.willReply = '';
      topic.status = 0;
      this.$axios.post("/topic/publishTopic", obj).then(response => {
        this.$message.success("回复成功！")
      })
    },
    //跳转文章
    goView(id) {
      this.$router.push({path: `/customer/detail/${id}`})
    },
    //跳转用户
    goOwn(id) {
      this.$router.push({path: `/own/ownHome/${id}`})
    },
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
      this.getEvents(index - 1, this.pageNum)
    },
    //获取事件
    getEvents(type, pageNum) {
      this.$axios.get("/event/getEvents", {
        params : {
          type : type,
          pageSize : 10,
          pageNum : pageNum
        }
      }).then(response => {
        if(type == 0) {
          this.replies = response.data.data
        } else if(type == 2) {
          this.follows = response.data.data
        } else if(type == 3) {
          this.praises = response.data.data
        } else if(type == 4) {
          this.systems = response.data.data
        }
      })
    },
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
    this.getUnReadCounts();
    //初始获取回复的内容
    this.getEvents(0, 1)
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.user:hover{
  color: red
}
.date-style{
  color: #969696;
  font-size: 6px;
}
.reply-container{
  margin-left: 10px;
  margin-top: 20px;
  margin-bottom: 10px
}
.reply-container > div{
  margin-top: 10px;margin-bottom: 10px
}
.myflex{
  display: flex;
  align-items: center;
}
.myflex-between{
  display: flex;
  align-items: center;
  justify-content: space-between;
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
