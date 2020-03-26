<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div>
      <el-container style="height: 600px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
         <el-menu>
            <el-submenu :index="item.kindName" v-for="item in kinds" :key="item.id">
              <template slot="title"><i class="el-icon-monitor"></i>{{item.kindName}}</template>
              <el-menu-item @click="chooseKind(kind.id)" :index="kind.kindName" v-for="kind in item.children" :key="kind.id">
                <span>{{kind.kindName}}</span>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
      <el-container>
          <el-main>
            <div class="infinite-list-wrapper main-content" style="overflow:auto" >
            <div
              class="list"
              v-infinite-scroll="load"
              infinite-scroll-disabled="disabled">
               <div v-for="item in articles" :key="item.id" class="segment">
                <div class="content" @click="goView(item.id)">
                  <div class="title">
                    <h4>{{item.title}}</h4>
                  </div>
                  <!-- <div class="desc">
                    <div v-if="item.content.length < 30">
                      <div class="myflex">
                        <div v-html="item.content">
                        </div>
                      </div>
                    </div>
                    <div v-else>
                      <div v-html="item.content">
                        </div>
                    </div>
                  </div> -->
                  <div class="detail">
                    <div class="user myflex">
                      <div>
                        <el-avatar :size="30">
                          <img :src="baseUrl + item.user.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 15%">
                        <span>{{item.user.userName}}</span>
                      </div>
                    </div>
                    <div class="myflex">
                      <div>
                        <i class="el-icon-view"></i>
                        <span>{{item.readCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 10%;height:10%" t="1585199681019" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3235" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="3236" fill="#8a8a8a"></path></svg>
                        <span>{{item.topicCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div style="width: 20%;height:20%">
                        <svg style="width: 10%;height:10%" t="1585199681019" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3235" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="3236" fill="#8a8a8a"></path></svg>
                        <span>{{item.topicCount}}&nbsp;&nbsp;</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <p v-if="loading && goon">加载中...</p>
            </div>

          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>
<script>
import mybar from "./mybar"
import {converObjToUrl,baseURL} from '../utils/utils'
export default {
  name : "myhome",
  data() {
    return {
      kinds: [],
      articles: [],
      user: {},
      pageNum: 1,
      kindId: null,
      baseUrl: '',
      //是否继续加载数据
      goon: true,
      loading: false
    }
  },
  components:{
    'mybar': mybar
  },
  methods: {
    //跳转路由
    goView(id) {
      this.$router.push({path: `/customer/detail/${id}`})
    },
    //变更分类
    chooseKind(id) {
      this.pageNum = 1;
      this.kindId = id;
      this.goon = true;
      this.loading = true;
      this.articles = [];
      this.getArticles(this.pageNum, 10, this.kindId)
    },
    setUser(obj) {
      this.user = obj
    },
    //加载
    load () {
      if(this.goon) {
        this.loading = true;
        this.getArticles(this.pageNum, 10,this.kindId)
      }
    },
    //获取所有分类
    getKinds() {
      this.$axios.get("/kind/getKinds").then(response => {
        this.kinds = response.data.data
      })
    },
    //获取所有文章
    getArticles(pageNum, pageSize,kindId) {
      let article = {};
      if(kindId){
        article.kindId = kindId;
      }
      if(pageNum){
        article.pageNum = pageNum;
      }
      if(pageSize){
        article.pageSize = pageSize;
      }
      this.pageNum += 1;
      this.$axios.get("/article/getArticles" + converObjToUrl(article)).then(response => {
        if(response.data.data.length > 0) {
          this.articles = this.articles.concat(response.data.data);
          this.loading = false
          console.log(this.articles)
        } else{
          this.$message.success("客观~只有这么多了")
          this.goon = false;
        }
      })
    }
  },
  mounted() {
    this.getKinds();
    this.getArticles(this.pageNum, 10, this.kindId);
    this.baseUrl = baseURL
  },
  computed: {
      disabled () {
        return this.loading || !this.goon
      }
    },
}
</script>
<style scoped>
.title{
  font-size: 18px;
  font-weight: 700;
  line-height: 1.5;
}
.desc{
    margin-bottom: 4px;
    color: #8a8a8a;
    font-size: 14px;
    line-height: 24px;
}
.segment{
  border-bottom: 1px solid gray;
}
.segment:hover{
  background-color: #fafafa;
}
.segment:hover .content .title{
  color: red;
}
.main-content{
  width: 70%;
}
.detail{
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #8a8a8a
}
.detail .user:hover{
  color: black
}
  .content{
    margin: 1%;
  }
 .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
  .myflex{
    display: flex;
    align-items: center;
  }
</style>
