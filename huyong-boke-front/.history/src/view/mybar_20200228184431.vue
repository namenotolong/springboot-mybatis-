<template>
  <nav>
    <div class="nav-container">
      <div class="nav-left">
      <img src="" alt="my nav">
      </div>
    <div class="nav-middle">
      <ul class="nav-ul">
        <li>
          <a href="#">
            <span>
              <font class="font-text">
                发现
              </font>
            </span>
          </a>
        </li>
        <li>
          <a href="#">
            <span >
              <font class="font-text">
                发现
              </font>
            </span>
          </a>
        </li>
        <li>
          <a href="#">
            <span>
              <font class="font-text">
                发现
              </font>
            </span>
          </a>
        </li>
      </ul>
    </div>
    <div class="nav-right">
       <el-button type="success">成功按钮</el-button>
    </div>
    </div>
  </nav>
</template>
<script>
export default {

}
</script>
<style lang="postcss" scoped>
.nav-left{
  display: flex;
}
.nav-middle{
  display: flex;
}
.nav-ul{
  display: flex;
  ustify-content: space-between
}
.nav-ul li{
  list-style: none;
  margin: 2%
}
a{
  text-decoration: none
}
.font-text{

}
.nav-container{
  display: flex;
  justify-content: space-between
}
</style>
