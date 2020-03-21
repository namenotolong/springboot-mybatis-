<template>
  <div>
    <div class="container">
      <div class="left-container">
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div>
              <span>个人资料</span>
            </div>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <span>我的关注</span>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <span>我的博客</span>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <span>我的粉丝</span>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <span>黑名单</span>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <span>我的收藏</span>
          </div>
        </div>
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
.text{
  padding: 5%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-direction: row;
}
.container{
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 3%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: row;
}
.left-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: column;
}
.right-container{
  display: flex;
  flex-direction: row;
}
li{
  list-style-type:none;
  display: list-item;
}
.left-text:hover{
  background-color: #f0f0f0;
}
.left-text{
  font-size: 18;
  color: #333;
  width: 280px;
  display: flex;
  align-content: center;
}
</style>
