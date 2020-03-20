<template>
  <div>
    <div class="container">
      <div class="left-container">
        <ui>
          <li>
            <a @click="go">

            </a>
          </li>
          <li>
            个人资料
          </li>
          <li>
            黑名单
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
  data :
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
}
</style>
