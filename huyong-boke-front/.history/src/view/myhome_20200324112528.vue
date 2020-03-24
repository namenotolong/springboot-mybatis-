<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <ul id="nav">
          <li><span><i class="el-icon-bottom-right" style="color: #83abc6"></i>博客分类</span></li>
          <li><a href="#">首页  <i class="el-icon-caret-right"></i> </a></li>
          <li><a href="#">嘿嘿 <i class="el-icon-caret-right"></i> </a>
            <ul class="level1">
              <li><a href="#">来了</a></li>
              <li><a href="#">啊啊</a></li>
            </ul>
          </li>
          <li><a href="#">waht <i class="el-icon-caret-right"></i> </a>
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
  list-style: none;
}
li a{
  display: block;
  text-decoration: none;
  height: 30px;
  line-height: 30px;
  width: 100px;
  color: black;
}
#nav > li{
  position: relative;
  background-color: #f5f6f7;;
  border: #83abc6 solid 1px;
  border-left: #83abc6 solid 5px;
  border-top: #83abc6 solid 2px;
  width: 40%;
  text-align: center;
}
/* #nav > li >ul.level1{
  position: absolute;
  top: 0px;
  left: 100px;
} */
#nav > li >ul.level1{
  display: none;
}
#nav > li:hover >ul.level1{
  display: block;
}
.left{
  width: 20%;
}
.content{
  display: flex;
  margin-top: 1%;
}
</style>
