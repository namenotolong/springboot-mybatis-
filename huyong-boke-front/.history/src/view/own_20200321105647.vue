<template>
  <div>
    <div class="container">
      <div class="left-container">
        <div>
          <span>hello</span>
        </div>
        <div>
          <span>hello</span>
        </div>
        <div>
          <span>hello</span>
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
.left-text{
  font-size: 20px;
  color: #606266;
 
}
</style>
