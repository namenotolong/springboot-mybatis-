<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <el-container style="height: 600px; border: 1px solid #eee">
        <el-aside width="15%" style="background-color: rgb(238, 241, 246)">
          <div class="out-container">
            <div class="user-detail" v-if="article.user">
            <div>
              <el-avatar :size="40">
                <img :src="baseUrl + article.user.picture"/>
              </el-avatar>
            </div>
            <div>
              <a href="#"><span style="color: black">&nbsp;&nbsp;{{article.user.userName}}&nbsp;&nbsp;</span></a>
            </div>
          </div>
          <div class="user-detail">
            <div class="fans">
              <span>粉丝：{{fansCount}}</span>
            </div>
            <div class="fans" v-if="article">
              <span>浏览：{{article.user.visitCount}}</span>
            </div>
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
      fansCount: 0,
    }
  },
  methods: {
    //获取粉丝数目
    getFansCount() {
      this.$axios.get("/relation/getFansCount", {
        params : {
          id : this.article.userId
        }
      }).then(response => {
        this.fansCount = response.data.data;
      })
    },
    //判断是否关注这篇文章的用户
    checkFan() {
      this.getArticle(this.$route.params.id).then(response => {
          this.article = response.data.data
          let params = {
              id : this.article.userId
            }
          this.$axios.get("/relation/checkFan" + converObjToUrl(params)).then(response => {
            this.follow = response.data.data
            this.getFansCount()
          })
      })
    },
    //更改关注状态
    modifyFollow() {
      this.follow = !this.follow;
      let ops = 1;
      if(this.follow) {
        ops = 0;
        this.fansCount++
      } else{
        this.fansCount--
      }
      this.$axios.get("/relation/follow", {
        params : {
          id : this.article.userId,
          ops : ops
        }
      })
    },
    //获取文章
    async getArticle(id) {
      if(id) {
        return await this.$axios.get("/article/detail", {
          params : {
            id : id
          }
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
    //this.getArticle(this.$route.params.id)
    //获取关注状态
    this.checkFan();
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.out-container{
  margin: 10%;
}
.red{
  background-color: red;
}
.black{
  background-color: black;
}
.user-detail{
  display: flex;
  align-items: center;
  justify-content: center;
}
a{
  text-decoration: none;
  color: balck
}
</style>
