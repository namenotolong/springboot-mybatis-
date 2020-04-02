<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <div class="inner-container">
        <el-container style="height: 600px; border: 1px solid #eee">
            <el-aside width="200px">
              <div :class="{whoshow : type == 1}" @click="changeType(1)">
                <div class="text-container">
                  <div class="text">
                    <i class="el-icon-message"></i>
                    <span>文章</span>
                  </div>
                </div>
              </div>
              <div :class="{whoshow : type == 2}" @click="changeType(2)">
                <div class="text-container">
                  <div class="text">
                    <i class="el-icon-chat-dot-round"></i>
                    <span>用户</span>
                  </div>
                </div>
              </div>
            </el-aside>
            <el-container>
              <el-main>
                <div v-for="item in some.data" :key="item.id">
                  <div class="myflex" @click="goOwn(item.id)">
                    <div>
                      <el-avatar :size="40">
                        <img :src="baseUrl + item.picture"/>
                      </el-avatar>
                    </div>
                    <div>
                      <b style="color: black">{{item.userName}}</b>
                    </div>
                    <div>
                      <i v-if="item.gender == 0" class="el-icon-male"></i>
                      <i v-else class="el-icon-female"></i>
                    </div>
                    <div>
                      <span>{{item.school}}</span>
                    </div>
                    <div>
                      <span>{{item.work}}</span>
                    </div>
                  </div>
                </div>
                <el-pagination
                  background
                  @current-change="changePage"
                  layout="prev, pager, next"
                  :total="some.count">
                </el-pagination>
              </el-main>
            </el-container>
        </el-container>
      </div>
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
      type: 2,
      key: '',
      pageNum : 1,
      some: []
    }
  },
  methods: {
    //翻页
    changePage(pageNum) {
      alert(pageNum)
    },
    //get
    getData() {
      this.$axios.get("/article/search", {
        params : {
          key : this.key,
          type : this.type,
          pageSize : 10,
          pageNum : this.pageNum
        }
      }).then(response => {
        this.some = response.data.data
        console.log(this.some)
      })
    },
    //切换类型
    changeType(type) {
      this.type = type;
      this.getData();
    },
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
    this.key =  this.$route.query.key;
    this.getData()
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.myflex{
  display: flex;
  align-items: center;
  border-bottom: 1px #f0f0f0 solid;
}
.myflex > div{
  margin-right :30px;
  padding-top: 30px;
  padding-bottom: 10px;
  color: #969696
}
.myflex:hover{
  background-color: lightyellow;
}
.inner-container{
  margin-top: 30px
}
.whoshow{
  background-color: #f0f0f0;
}
.text-container{
  width: 100%;
  height: 60px
}
.text-container:hover{
  background-color: #f0f0f0;
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
.out-container{
  width: 80%;
  margin-left: 10%
}
</style>
