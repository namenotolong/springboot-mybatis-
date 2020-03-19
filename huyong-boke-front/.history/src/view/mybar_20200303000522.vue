<template>
  <nav>
    <div class="nav-container">
      <div class="nav-left">
      <img src="../img/1.png" alt="my nav">
      </div>
    <div class="nav-middle">
      <div class="nav-text">
        <div class="nav-text-content">
        <a href="#"><span>发现</span></a>
        </div>
        <div class="nav-text-content">
          <a href="#"><span>发现</span></a>
        </div>
        <div class="nav-text-contentt">
          <a href="#"><span>发现</span></a>
        </div>
      </div>
      <div>

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
  flex: 4 1 auto
}
.nav-right{
  flex: 4 1 auto
}
a{
  text-decoration: none
}
.font-text{

}
.nav-container{
  display: flex;
  flex-direction: row;
  height: 70px;
  //justify-content: space-between
}
</style>
