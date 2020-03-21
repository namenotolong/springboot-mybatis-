<template>
  <div>
    <div class="container">
      <div class="center-container">
        <ul>
          <li>
            <a @click="go">
              <div class="text-div">
                <span class="left-text">
                  我的收藏
                </span>
              </div>
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
        </ul>
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
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 3%;
  display: flex;

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
  display: list-item;
}
li:hover{
  background-color: red;
}
.center-text{
  font-size: 20px;
  color: #606266;
  padding-bottom: 50px;
}
</style>
