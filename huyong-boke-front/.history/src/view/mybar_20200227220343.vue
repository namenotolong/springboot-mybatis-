<template>
  <div class="nav-bar">
    <div class="nav-left float-left">
      <span>我的博客名称</span>
    </div>
    <div class="nav-middle float-left">
      <a href="#">
        <span>
          <font>
            发现
          </font>
        </span>
      </a>
      <a href="#">
        <span>
          <font>
            关注
          </font>
        </span>
      </a>
    </div>

  </div>
</template>
<script>
export default {
  data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
}
</script>
<style lang="postcss" scoped>
  .nav-text{
    margin-right: 2%
  }
  .nav-middle{
    margin-left: 5%
  }
  a{
    text-decoration: none
  }
  font{
    color: black;
  }
  .menu-text{

  }
  .float-left{
    float: left;
  }
</style>
