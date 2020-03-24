<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div>
      <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
         <el-menu>
            <el-submenu :index="item.kindName" v-for="item in kinds" :key="item.id">
              <template slot="title"><i class="el-icon-message"></i>{{item.kindName}}</template>
              <el-menu-item :index="kind.kindName" v-for="kind in item.children" :key="kind.id">
                {{kind.kindName}}
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
      <el-container>
          <el-main>
            <el-table>
              <el-table-column width="140">
              </el-table-column>
            </el-table>
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
    const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
    return {
      kinds: [],
      articles: [],
      count: 0,
      user: {},
      pageNum: 1,
      kindId: null,
      baseUrl: '',
      //是否继续加载数据
      goon: true,
      tableData: Array(20).fill(item)
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
      if(this.goon) {
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
      alert(pageNum)
      alert(this.pageNum)
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
        } else{
          this.$message.success("客观~只有这么多了")
          this.goon = false;
        }
      })
    }
  },
  mounted() {
    this.getKinds();
    //this.getArticles(this.pageNum, 10);
    this.baseUrl = baseURL
  }
}
</script>
<style scoped>
 .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
