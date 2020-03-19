<template>
  <div>
    <nav>
      <div class="width-limit">
        <a class="logo" href="">
          <img src="" alt=''>
        </a>
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
