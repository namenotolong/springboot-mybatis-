<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <ul id="nav">
          <li>首页</li>
          <li>关于我们
            <ul class="level1">
              <li>我们的故事</li>
              <li>我们的团队</li>
            </ul>
          </li>
          <li>服务
            <ul class="level1">
              <li>嘻嘻</li>
              <li>哈哈</li>
            </ul>
          </li>
        </ul>
      </div>
      <div>
      </div>
    </div>
  </div>
</template>
<script>
import mybar from "./mybar"
export default {
  name : "myhome",
  components:{
    'mybar': mybar
  }
}
</script>
<style scoped>

.left{
  width: 20%;
}
.content{
  display: flex;
  margin-top: 1%;
}
</style>
