<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <
      </div>
      <div>

      </div>
    </div>
  </div>
</template>
<script>
import mybar from "./mybar"
export default {
  name : "myhome",
  components:{
    'mybar': mybar
  }
}
</script>
<style scoped>
.left{
  width: 20%;
}
.content{
  display: flex;
  margin-top: 10%;
}
</style>
