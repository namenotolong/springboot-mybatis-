<template>
  <div class="nav-bar">
    <div class="nav-left float-left">
      <span>我的博客名称</span>
    </div>
    <div class="nav-middle float-left">
      <div class="float-left">
        <span>
          <font>
            发现
          </font>
        </span>
      </div>
      <div class="float-left">
        <span>
          <font>
            关注
          </font>
        </span>
      </div>
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
