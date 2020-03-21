<template>
  <div>
      <div class="left-container">
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div class="text-right">
              <span @click="go('own')">个人资料</span>
            </div>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div class="text-right">
              <span>个人资料</span>
            </div>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div class="text-right">
              <span>个人资料</span>
            </div>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div class="text-right">
              <span>个人资料</span>
            </div>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div class="text-right">
              <span>个人资料</span>
            </div>
          </div>
        </div>
        <div class="left-text">
          <div class="text">
            <div class="text-icon">
              <li class="el-icon-user-solid"></li>
            </div>
            <div class="text-right">
              <span>个人资料</span>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>
<script>
export default {
  name : "left",
  data() {
    return {

    }
  },
  methods : {
    go(view) {
      this.$router.push({path : view})
    }
  }
}
</script>
<style scoped>
.text-right{
  margin-left: 10%;
  width: 100px;
}
.text{
  padding: 5%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-direction: row;
}
.left-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: column;
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
