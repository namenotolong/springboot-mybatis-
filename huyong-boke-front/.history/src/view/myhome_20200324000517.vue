<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <ul id="nav">
          <li><a href="#">首页</a></li>
          <li><a href="#">嘿嘿</a>
            <ul class="level1">
              <li><a href="#">来了</a></li>
              <li><a href="#">啊啊</a></li>
            </ul>
          </li>
          <li><a href="#">waht</a>
            <ul class="level1">
              <li><a href="#">嘻嘻</a></li>
              <li><a href="#">哈哈</a></li>
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
li{
  width: 100%;
  height : 30px;
  border: 1px solid red;
  list-style: none;
}
li a{
  display: block;
  text-decoration: none;
  height: 30px;
  line-height: 30px;
  width: 100px;
  text-align: center;
}
#nav > li{
  position: relative;
}
#nav > li 
.left{
  width: 20%;
}
.content{
  display: flex;
  margin-top: 1%;
}
</style>
