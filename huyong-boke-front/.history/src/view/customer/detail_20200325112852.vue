<template>
  <div>
    <el-container style="height: 500px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

        </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">

        </el-header>

        <el-main>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import {converObjToUrl,baseURL} from '../..//utils/utils'
export default {
  name: 'detail',
  data() {
    return {
      user: {},
      baseUrl: '',
    }
  },
  methods: {
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
  },
}
</script>
<style scoped>

</style>
