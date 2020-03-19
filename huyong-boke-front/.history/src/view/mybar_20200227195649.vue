<template>
  <div>
    <nav>
      <!-- 左上方 -->
      <div class="width-limit">
        <a class="logo" href="">
          <img src="" alt='Nav Logo'>
        </a>
      </div>
      
      <div>

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
