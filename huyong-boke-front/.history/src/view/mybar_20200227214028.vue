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
        <ul class="nav-bar">
          <li>
            <a href="#">
              <span class="menu-text">
                <font style="vertical-align: inherit;">
                  发现
                </font>
              </span>
            </a>
          </li>
          <li>
            <a href="#">
              <span class="menu-text">
                <font style="vertical-align: inherit;">
                  关注
                </font>
              </span>
            </a>
          </li>
          <li>
            <a href="#">
              <span class="menu-text">
                <font style="vertical-align: inherit;">
                  消息
                </font>
              </span>
            </a>
          </li>
        </ul>

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
  .nav-bar{
    display: inline;
  }
  .nav-bar li{
    list-style: none;
    float: left;
  }
</style>
