<template>
  <div>
    <left></left>
    <div class="container">
      <left></left>
      <div class="right-container">
        <p>hello world</p>
      </div>
    </div>
  </div>
</template>
<script>
import left from "./left"
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
  flex-direction: row;
  flex: 1 4 auto;
}
.right-container{
  display: flex;
  flex-direction: row;
}
li{
  list-style-type:none;
  display: list-item;
}
</style>
