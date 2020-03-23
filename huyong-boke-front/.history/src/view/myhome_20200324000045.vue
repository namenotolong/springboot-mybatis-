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
            <ul class="">

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
