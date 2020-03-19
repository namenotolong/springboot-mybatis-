<template>
  <div class="nav-bar">
    <div class="nav-left">
      <span>我的博客名称</span>
    </div>
    <div class="nav-middle">
      <div>
        <span>
          <font>
            
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
</style>
