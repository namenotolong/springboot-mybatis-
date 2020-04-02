<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div>
      <el-container style="height: 600px; border: 1px solid #eee">
          <el-aside width="200px">
            <div :class="{whoshow : type == 1}" @click="checkIndex(1)">
              <div class="text-container">
                <div class="text">
                  <i class="el-icon-message"></i>
                  <el-badge :value="replyCount == 0 ? null : replyCount" class="item">
                    <span>回复</span>
                  </el-badge>
                </div>
              </div>
            </div>
            <div :class="{whoshow : type == 2}" @click="checkIndex(2)">
              <div class="text-container">
                <div class="text">
                  <i class="el-icon-chat-dot-round"></i>
                  <el-badge :value="messageCount == 0 ? null : messageCount" class="item">
                    <span>私信</span>
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
                  <div style="margin-left: 10px;margin-top: 30px" v-if="loading">
                    <span>加载中……</span>
                  </div>
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
                            <el-link><span class="date-style" @click="item.status = 2">回复</span></el-link>
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
                      <div class="myflex" v-if="item.status == 2">
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
                <div v-if="index == 3">
                  <b>新的粉丝</b>
                  <div style="margin-left: 10px;margin-top: 30px" v-if="loading">
                    <span>加载中……</span>
                  </div>
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
                  <div style="margin-left: 10px;margin-top: 30px" v-if="loading">
                    <span>加载中……</span>
                  </div>
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
                        <span v-else-if="item.articleId">赞了你的文章</span>
                        <span v-else>给你点了一个赞赞！</span>
                      </div>
                      <div v-if="item.topicId">
                        <el-link type="primary" @click="goOwn(user.id)"><b class="user">{{user.userName}}</b></el-link><span>:&nbsp;{{item.replyContent}}</span>
                      </div>
                      <div v-if="item.articleId">
                        <span><span class="date-style">博文：</span><b><el-link type="info" @click="goView(item.articleId)">{{item.title}}</el-link></b></span>
                      </div>
                    </div>
                  </div>
                  <div v-else>
                    <h2>暂无</h2>
                  </div>
                </div>
                <div v-if="index == 5">
                  <b>系统消息</b>
                  <div style="margin-left: 10px;margin-top: 30px" v-if="loading">
                    <span>加载中……</span>
                  </div>
                  <div v-if="systems.count">
                    <div class="reply-container" v-for="item in systems.data" :key="item.id">
                      <div class="myflex-around">
                        <div>
                          <span>{{item.content}}</span>
                        </div>
                        <div>
                            <span class="date-style">{{item.updateTime}}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div v-else>
                    <h2>暂无</h2>
                  </div>
                </div>
              </div>
              <div v-if="replies.data" style="margin-top: 30px">
                <el-pagination
                  @small="curArr.maxPage < 7"
                  background
                  @current-change="changePage"
                  layout="prev, pager, next"
                  :total="curArr.maxPage">
                </el-pagination>
              </div>
            </el-main>
          </el-container>
        </el-container>
    </div>
  </div>
</template>
<script>
import mybar from "../mybar"
import {converObjToUrl,baseURL} from '../../utils/utils'
export default {
  name: 'search',
  data() {
    return {
      baseUrl: '',
      user: {},
      type: 0,
      key: '',
    }
  },
  methods: {
    goArticle(id) {
      this.$router.push({path: `/customer/write/${id}`})
    },
    //跳转用户
    goOwn(id) {
      this.$router.push({path: `/own/ownHome/${id}`})
    },
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
    this.key = this.$route.params.key;
    this.type = this.$route.params.type;
    console.log(this.key)
    console.log(this.type)
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>

</style>
