<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <el-container style="height: 600px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <div class="user-detail" v-if="article.user">
            <div>
              <el-avatar :size="40">
                <img :src="baseUrl + article.user.picture"/>
              </el-avatar>
            </div>
            <div>
              <a href="#"><span style="color: black">&nbsp;&nbsp;{{article.user.userName}}&nbsp;&nbsp;</span></a>
            </div>
            <div>
               <el-avatar :size="40">
                <img src="../../files/haven.png"/>
              </el-avatar>
            </div>
          </div>
        </el-aside>
      <el-container>
        <el-header style="text-align: right; font-size: 12px">

        </el-header>

        <el-main>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import mybar from "../mybar"
import {converObjToUrl,baseURL} from '../..//utils/utils'
export default {
  name: 'detail',
  data() {
    return {
      user: {},
      baseUrl: '',
      article: '',
      follow: false,
    }
  },
  methods: {
    //获取文章
    getArticle(id) {
      if(id) {
        this.$axios.get("/article/detail", {
          params : {
            id : id
          }
        }).then(response => {
          this.article = response.data.data
          console.log(this.article)
        })
      }
    },
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
    //获取当前文章
    this.getArticle(this.$route.params.id)
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.user-detail{
  display: flex;
  margin: 10%;
  align-items: center;
}
a{
  text-decoration: none;
  color: balck
}
</style>
