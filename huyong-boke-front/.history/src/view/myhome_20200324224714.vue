<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div>
      <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu>
            <el-submenu :index="kind.id" v-for="kind in kinds" :key="kind.id">
              <template slot="title"><i class="el-icon-message"></i>{{kind}}</template>
              <el-menu-item-group>
                <template slot="title">分组一</template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="分组2">
                <el-menu-item index="1-3">选项3</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="1-4">
                <template slot="title">选项4</template>
                <el-menu-item index="1-4-1">选项4-1</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-menu"></i>导航二</template>
              <el-menu-item-group>
                <template slot="title">分组一</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="分组2">
                <el-menu-item index="2-3">选项3</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="2-4">
                <template slot="title">选项4</template>
                <el-menu-item index="2-4-1">选项4-1</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-setting"></i>导航三</template>
              <el-menu-item-group>
                <template slot="title">分组一</template>
                <el-menu-item index="3-1">选项1</el-menu-item>
                <el-menu-item index="3-2">选项2</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="分组2">
                <el-menu-item index="3-3">选项3</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="3-4">
                <template slot="title">选项4</template>
                <el-menu-item index="3-4-1">选项4-1</el-menu-item>
              </el-submenu>
            </el-submenu>
          </el-menu>
        </el-aside>
      <el-container>
          <el-header style="text-align: right; font-size: 12px">
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 15px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>查看</el-dropdown-item>
                <el-dropdown-item>新增</el-dropdown-item>
                <el-dropdown-item>删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <span>王小虎</span>
          </el-header>
          <el-main>
            <el-table :data="tableData">
              <el-table-column prop="date" label="日期" width="140">
              </el-table-column>
              <el-table-column prop="name" label="姓名" width="120">
              </el-table-column>
              <el-table-column prop="address" label="地址">
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
