<template>
  <div style="background-color: #f5f6f7">
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
        <div class="myflex">
          <div class="left-container">
            <div class="header">
              <div class="myflexAround">
                <div class="myflex">
                  <div>
                    <el-avatar :size="50">
                      <img :src="baseUrl + user.picture"/>
                    </el-avatar>
                  </div>
                  <div style="margin-left: 10px">
                    <el-link><span style="color: #e1e1e1">胡勇</span></el-link>
                  </div>
                </div>
                <div>
                  hello world
                </div>
              </div>
            </div>
          </div>
          <div class="right-container">

          </div>
        </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'chat',
  data() {
    return {
      user: {},
      baseUrl: '',
    }
  },
  methods: {
    setUser(obj) {
      this.user = obj
    },

  },
  mounted() {
    this.baseUrl = baseURL
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.header{
  margin-top: 5px;
  margin-left: -10px;
  border-bottom: 1px #2e2e2e solid;
}
.right-container{
  height: 550px;
  background-color: white;
  width: 65%;
}
.left-container{
  background-color: #3d3d3d;
  height: 550px;
  width: 28%;
}
.myflex{
  display: flex;
  align-items: center;
}
.myflexAround{
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.out-container{
  margin-top: 20px;
  width: 80%;
  margin-left: 10%;
  margin-right: 10%;
}
</style>
