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
                <div v-for="item in some" :key="item.id">
                  {{item.userName}}
                </div>
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
      type: 0,
      key: '',
      pageNum : 1,
      some: []
    }
  },
  methods: {
    //get
    getData() {
      this.$axios.get("/article/search", {
        params : {
          key : this.$route.params.key,
          type : this.$route.params.type,
          pageSize : 10,
          pageNum : this.pageNum
        }
      }).then(response => {
        this.some = response.data.data
      })
    },
    //切换类型
    changeType(type) {
      this.type = type;
      let value = this.$route.params.key;
      this.$router.push({path: `/customer/search/${value}/${type}`})
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
    this.key = this.$route.params.key;
    this.type = this.$route.params.type;
    this.getData()
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
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
