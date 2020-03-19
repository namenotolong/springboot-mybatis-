<template>
  <p>hello world</p>
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
  .middle-limit{
    margin-left: 5%
  }
  .float-left{
    float: left;
  }
  .nav-bar{
    display: inline;
  }
  .nav-bar li{
    list-style: none;
    float: left;
    margin: 2%
  }
  a{
    text-decoration: none
  }
  font{
    color: black;
  }
  .menu-text{

  }
</style>
