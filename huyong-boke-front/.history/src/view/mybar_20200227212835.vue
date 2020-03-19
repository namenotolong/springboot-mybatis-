<template>
  <div>
    <nav>
      <!-- 左上方 -->
      <div class="width-limit">
        <a class="logo" href="">
          <img src="" alt='Nav Logo'>
        </a>
      </div>
      <div class="middle-limit">
        <ul>
          
        </ul>
        <span class="bar-span">发现</span>
        <span class="bar-span">关注</span>
        <span class="bar-span">消息</span>

      </div>
    </nav>
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

</style>
