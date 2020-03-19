<template>
  <nav>
    <div class="nav-container">
      <div class="nav-left">
      <img src="../img/1.png" alt="my nav">
      </div>
    <div class="nav-middle">
      <div class="nav-text">
        <a href="#"><span>发现</span></a>
      </div>
      <div class="nav-text">
        <a href="#"><span>发现</span></a>
      </div>
      <div class="nav-text">
        <a href="#"><span>发现</span></a>
      </div>
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
  justify-content: space-between;
  flex: 1 1 auto
}
.nav-middle{
  display: flex;
  flex: 1 1 auto
}
.nav-right{
  flex: 1 4 auto
}
a{
  text-decoration: none
}
.font-text{

}
.nav-container{
  display: flex;
  flex-direction: row;
  height: 130px;
  width: 130px;
  //justify-content: space-between
}
</style>
