<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <div class="out-container">
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
</style>
