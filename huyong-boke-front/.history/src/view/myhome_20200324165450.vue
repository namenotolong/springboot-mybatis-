<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <ul id="nav">
          <li v-for="item in kinds" :key="item.id">
            <a href="#"> <i class="el-icon-caret-right"></i> {{item.kindName}}</a>
            <ul class="level1" v-if="item.children.length !== 0">
              <li v-for="kind in item.children" :key="kind.id"><a href="#">{{kind.kindName}}</a></li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="right">
        <div class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
          <div v-for="article in articles" :key="article.id">
            <div class="article">
              <div class="title">
                <h2>{{article.title}}</h2>
              </div>
              <div class="content">
                <p>{{article.content}}……</p>
              </div>
              <div class="foot">
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import mybar from "./mybar"
import {converObjToUrl} from '../utils/utils'
export default {
  name : "myhome",
  data() {
    return {
      kinds: [],
      articles: [],
      count: 0,
      user: {},
      pageNum: 1,
      kindId: null
    }
  },
  components:{
    'mybar': mybar
  },
  methods: {
    setUser(obj) {
      this.user = obj
    },
    load () {
        this.getArticles(this.pageNum, 10,this.kindId)
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
      this.$axios.get("/article/getArticles" + converObjToUrl(article)).then(response => {
        if(response.data.data.length > 0) {
          this.articles = this.articles.concat(response.data.data);
          this.pageNum += 1;
        } else{
          this.$message.success("客观~只有这么多了")
        }
      })
    }
  },
  mounted() {
    this.getKinds();
    this.getArticles(this.pageNum, 10);
  }
}
</script>
<style scoped>
li{
  width: 100%;
  height : 30px;
  list-style: none;
}
li a{
  display: block;
  text-decoration: none;
  height: 30px;
  line-height: 30px;
  width: 100px;
  color: black;
}
#nav > li{
  position: relative;
  width: 40%;
  text-align: center;
}
#nav > li a:hover{
  background-color: white;
  color: black;
}
/* #nav > li >ul.level1{
  position: absolute;
  top: 0px;
  left: 100px;
} */
#nav > li >ul.level1{
  display: none;
  margin-left: 50px;
  margin-top: -20%;
}
#nav > li:hover >ul.level1{
  display: block;
}
.left{
  width: 10%;
}
.right{
  margin-left: 10%;
  width: 50%;
}
.content{
  display: flex;
  margin-top: 1%;
  background-color: #f5f6f7;
}
</style>
