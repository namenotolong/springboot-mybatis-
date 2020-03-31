<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
      <div class="myflex">
        <div class="left-container">
          12123123
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
.left-container{
  background-color: #3d3d3d;
  height: 600px;
}
.myflex{
  display: flex;
  flex: 1 2 auto;
}
.out-container{
  margin-top: 30px;
  width: 80%;
  margin-left: 5%;
  margin-right: 5%;
}
</style>
