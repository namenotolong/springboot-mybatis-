<template>
  <div>
    <div class="container">
      <div class="left-container">
        <ui>
          <li>
            <a @click="go">
              <span class="left-text">
                  我的收藏
              </span>
            </a>
          </li>
          <li>
            <a @click="go">
              <span class="left-text">
                  个人资料
              </span>
            </a>
          </li>
          <li>
            <a @click="go">
              <span class="left-text">
                  黑名单
              </span>
            </a>
          </li>
        </ui>
      </div>
      <div class="right-container">

      </div>
    </div>
  </div>
</template>
<script>
export default {
  name : "own",
  data() {
    return {

    }
  },
  methods : {
    go(view) {

    }
  }
}
</script>
<style scoped>
.container{
  display: flex;
  align-content: center;
  justify-content: space-around;
  margin-top: 3%;
}
.left-container{
  display: flex;
}
.right-container{
  display: flex;
  flex-direction: row;
}
li{
  list-style-type:none;
  margin-top: 1%;
}
.left-text{
  font-size: 20px;
  color: #606266;
}
</style>
