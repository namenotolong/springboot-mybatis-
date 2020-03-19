<template>
  <nav>
    <div class="nav-container">
      <div class="nav-left">
      <img src="../img/1.png" alt="my nav" class="nav-img">
      </div>
    <div class="nav-middle">
      <div class="nav-text">
        <div class="">
          <div class="nav-text-content">
            <a href="#">
              <span class="menu-text">
              发现
              </span>
            </a>
          </div>
        </div>
        <div class="nav-text-content">
          <a href="#">
            <span class="menu-text">
            关注
            </span>
          </a>
        </div>
        <div class="nav-text-content">
          <el-row>
            <el-col class="menu-text">
              <el-dropdown>
                <span class="el-dropdown-link">
                  消息
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
        <div class="">
             <el-input
            placeholder="请输入内容" type="text"
            prefix-icon="el-icon-search"
            clearable
            v-model="searchContent"
            @focus="searchInputFocusMethod"
            @change="searchInputSubmitMethod"
            @blur="searchInputBlurMethod"
            v-bind:class="{focus : searchInputFocus}"
            >
          </el-input>
        </div>
      </div>
      <div class="nav-blank">
      </div>
    </div>
    <div class="nav-right">
       <div>
         <el-button type="success">成功按钮</el-button>
       </div>
    </div>
    </div>
  </nav>
</template>
<script>
export default {
  data() {
    return {
      searchContent : '',
      searchInputFocus : false,
    }
  },
  methods : {
    searchInputFocusMethod() {
      this.searchInputFocus = true;
    },
    searchInputBlurMethod() {
      this.searchInputFocus = false;
    },
    searchInputSubmitMethod(value) {
      if (value && value.trim().length > 0) {
        alert(value)
      }
    },
  }
}
</script>
<style lang="postcss" scoped>
.focus{
  width: 120%
}
.nav-text-content :hover{
  color: red;
  height: 100%;
}
.menu-text{
  font-size: 18px;
  color: #606266;
}
.el-dropdown-link{
  font-size: 18px;
}
.menu-text :hover{
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
  flex: 1 1 auto
}
.nav-middle{
  display: flex;
  flex: 4 1 auto
}
.nav-right{
  flex: 4 1 auto;
  flex-direction: row-reverse
}
a{
  text-decoration: none;
  color: black
}
.nav-container{
  display: flex;
  flex-direction: row;
  height: 80px;
  align-items: center;
   -webkit-box-shadow: #666 0px 0px 10px;
   -moz-box-shadow: #666 0px 0px 10px;
   box-shadow: #666 0px 0px 10px;
   background: white;
}
.nav-img{
  height: 80px
}
</style>
