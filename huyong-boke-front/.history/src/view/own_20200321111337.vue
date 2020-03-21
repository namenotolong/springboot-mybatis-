<template>
  <div>
    <div class="container">
      <div class="left-container">
        <div class="left-text">
          <div class="text">
            <span>个人资料</span>
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
