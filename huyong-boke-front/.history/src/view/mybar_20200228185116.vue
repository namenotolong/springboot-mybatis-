<template>
  <nav>
    <div class="nav-container">
      <div class="nav-left">
      <img src="" alt="my nav">
      </div>
    <div class="nav-middle">
      <div>
        <span>发现</span>
      </div>
      <div>
        <span>发现</span>
      </div>
      <div>
        <span>发现</span>
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
  justify-content: space-between
}
.nav-middle{
  display: flex;
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
