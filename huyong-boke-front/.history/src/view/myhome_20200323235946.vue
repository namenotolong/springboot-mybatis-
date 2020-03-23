<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="content">
      <div class="left">
        <ul id=>
          <ul>
            1
            <li>22</li>
            <li>22</li>
            <li>22</li>
            <li>22</li>
            <li>22</li>
          </ul>
          <li>1</li>
          <li>1</li>
          <li>1</li>
          <li>1</li>
        </ul>
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
  margin-top: 1%;
}
</style>
