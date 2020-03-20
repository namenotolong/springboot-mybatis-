<template>
  <div>
    <div class="container">
      <div class="left-container">
        <ui>
          <li>
            
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
}
</script>
<style scoped>
.container{
  display: flex;
  align-content: center;
  justify-content: space-around;
}
.left-container{
  display: flex;
}
.right-container{
  display: flex;
  flex-direction: row;
}
</style>
