<template>
  <div>
    <mybar></mybar>
    <div>
      <el-autocomplete
  v-model="state"
  :fetch-suggestions="querySearchAsync"
  placeholder="请输入内容"
  @select="handleSelect"
></el-autocomplete>
    </div>
  </div>
</template>
<script>
import mybar from "./mybar"
export default {
  name : "myhome",
  components : {
    mybar
  }
}
</script>
<style scoped>

</style>
