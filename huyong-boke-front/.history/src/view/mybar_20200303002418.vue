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
          <a href="#"><span>关注</span></a>
        </div>
        <div class="nav-text-contentt">
          <el-row class="block-col-2">
            <el-col :span="12">
              <span class="demonstration">hover 激活</span>
              <el-dropdown>
                <span class="el-dropdown-link">
                  下拉菜单<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-plus">黄金糕</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus">狮子头</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus-outline">螺蛳粉</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-check">双皮奶</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-check">蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
            <el-col :span="12">
              <span class="demonstration">click 激活</span>
              <el-dropdown trigger="click">
                <span class="el-dropdown-link">
                  下拉菜单<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-plus">黄金糕</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus">狮子头</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus-outline">螺蛳粉</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-check">双皮奶</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-check">蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>

        </div>
      </div>
      <div class="nav-blank">

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
.el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  .demonstration {
    display: block;
    color: #8492a6;
    font-size: 14px;
    margin-bottom: 20px;
  }
.nav-text-content :hover{
  color: red
}
.nav-text{
  display: flex;
  flex: 1 1 auto;
  justify-content: space-around;
  align-items: center
}
.nav-blank{
  flex: 1 1 auto
}
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
