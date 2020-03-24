<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <ul id="nav">
          <li><a href="#"> <i class="el-icon-caret-right"></i> 首页</a></li>
          <li><a href="#"> <i class="el-icon-caret-right"></i> 嘿嘿  </a>
            <ul class="level1">
              <li><a href="#">来了</a></li>
              <li><a href="#">啊啊</a></li>
            </ul>
          </li>
          <li><a href="#"> <i class="el-icon-caret-right"></i> waht</a>
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
  width: 40%;
  text-align: center;
  border-left: 5px solid #1c70b2;
  border-top: 2px solid #1c70b2;
}
#nav > li a:hover{
  background-color: white;
  color: black;
}
/* #nav > li >ul.level1{
  position: absolute;
  top: 0px;
  left: 100px;
} */
#nav > li >ul.level1{
  display: none;
  margin-left: 50px;
  margin-top: -20%;
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
  background-color: #f5f6f7;
}
</style>
