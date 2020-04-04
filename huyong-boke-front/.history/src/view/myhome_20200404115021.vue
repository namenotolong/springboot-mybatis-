<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div>
      <el-container style="height: 600px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: #3d3d3d">
         <el-menu background-color="#3d3d3d"
      text-color="#fff"
      active-text-color="#ffd04b">
           <el-menu-item style="color: #303133" @click="chooseKind">
              <template slot="title"><span style="color: #fff"><i class="el-icon-monitor" style="color: #909399"></i><span>查看所有</span></span></template>
            </el-menu-item>
            <el-menu-item style="color: #303133" @click="showMyFollows">
              <template slot="title"><span style="color: #fff"><i class="el-icon-magic-stick" style="color: #909399"></i><span>我的关注</span></span></template>
            </el-menu-item>
            <el-submenu :index="item.kindName" v-for="item in kinds" :key="item.id">
              <template slot="title"><i :class=item.bar></i>{{item.kindName}}</template>
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
                <div class="content" >
                  <div class="title" @click="goView(item.id)">
                    <h4>{{item.title}}</h4>
                  </div>
                  <div class="detail">
                    <div class="user myflex">
                      <div @click="goOwn(item.user.id)">
                        <el-avatar :size="30">
                          <img :src="baseUrl + item.user.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 15px;width: 300px" @click="goOwn(item.user.id)">
                        <span style="font-size: 50%">胡勇巴巴变</span>
                      </div>
                    </div>
                    <div class="myflex">
                      <div>
                        <svg style="width: 20px;height:20px" t="1585199866320" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3565" width="200" height="200"><path d="M157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z m145.994667-70.773334a32 32 0 1 1 40.917333-49.205333A159.189333 159.189333 0 0 0 512 672c37.888 0 73.674667-13.173333 102.186667-36.885333a32 32 0 0 1 40.917333 49.216A223.178667 223.178667 0 0 1 512 736a223.178667 223.178667 0 0 1-143.136-51.690667z" p-id="3566" fill="#8a8a8a"></path></svg>
                        <span>{{item.topicCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585136810011" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4741" width="200" height="200"><path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24zM551.786667 756.032l170.976 106.24c2.624 1.621333 5.717333 2.122667 8.650666 1.408 6.410667-1.557333 10.56-8.426667 8.928-15.424l-46.485333-198.24a77.141333 77.141333 0 0 1 24.277333-75.733333L870.293333 441.706667c2.485333-2.165333 4.053333-5.312 4.330667-8.746667 0.565333-7.136-4.490667-13.173333-10.976-13.696l-199.712-16.288a75.989333 75.989333 0 0 1-64.064-47.168l-76.938667-188.16a12.309333 12.309333 0 0 0-6.538666-6.741333c-5.898667-2.496-12.725333 0.373333-15.328 6.741333l-76.949334 188.16a75.989333 75.989333 0 0 1-64.064 47.168l-199.701333 16.288a11.68 11.68 0 0 0-7.978667 4.181333 13.226667 13.226667 0 0 0 1.333334 18.261334l152.16 132.586666a77.141333 77.141333 0 0 1 24.277333 75.733334l-46.485333 198.229333a13.333333 13.333333 0 0 0 1.514666 9.877333c3.488 5.792 10.581333 7.530667 16.064 4.128l170.986667-106.229333a75.296 75.296 0 0 1 79.562667 0z" p-id="4742" fill="#8a8a8a"></path></svg>
                        <span>{{item.storeCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585196396690" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1502" width="200" height="200"><path d="M512 256a416.853333 416.853333 0 0 1 376.32 234.666667A416.853333 416.853333 0 0 1 512 725.333333a416.853333 416.853333 0 0 1-376.32-234.666666A416.853333 416.853333 0 0 1 512 256m0-85.333333C298.666667 170.666667 116.48 303.36 42.666667 490.666667 116.48 677.973333 298.666667 810.666667 512 810.666667s395.52-132.693333 469.333333-320C907.52 303.36 725.333333 170.666667 512 170.666667z m0 213.333333a106.666667 106.666667 0 0 1 0 213.333333 106.666667 106.666667 0 0 1 0-213.333333m0-85.333333c-105.813333 0-192 86.186667-192 192S406.186667 682.666667 512 682.666667s192-86.186667 192-192S617.813333 298.666667 512 298.666667z" fill="#8a8a8a" p-id="1503"></path></svg>
                        <span>{{item.visitCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585199681019" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3235" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="3236" fill="#8a8a8a"></path></svg>
                        <span>{{item.praiseCount}}&nbsp;&nbsp;</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
             <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="loading && goon">加载中...</p>
              <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="!goon">没有更多了...</p>
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
    //跳转用户
    goOwn(id) {
      this.$router.push({path: `/own/ownHome/${id}`})
    },
    //跳转路由
    goView(id) {
      this.$router.push({path: `/customer/detail/${id}`})
    },
    //变更分类
    chooseKind(id) {
      this.pageNum = 1;
      this.goon = true;
      this.loading = true;
      this.articles = [];
      if(id) {
        this.kindId = id;
        this.getArticles(this.pageNum, 10, this.kindId)
      } else {
        this.getArticles(this.pageNum, 10)
      }
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
    //查看我的关注的文章
    showMyFollows() {
      if(!this.user.id) {
        this.$message.error("您还未登录请先登录！")
        this.$refs.bar.logindialog = true
        return;
      }
      this.articles = []
      this.goon = true;
      this.loading = true;
      this.pageNum = 1;
      this.$axios.get("/article/getFollowsArticles").then(response => {
        this.articles = response.data.data
        this.goon = false;
        this.loading = false;
      })
    },
    //获取所有文章
    getArticles(pageNum, pageSize,kindId) {
      let article = {};
      if(kindId > 0){
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
        } else{
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
  cursor:pointer;
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
  color: #8a8a8a;
  cursor:pointer;
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
