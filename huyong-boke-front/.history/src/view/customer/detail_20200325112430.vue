<template>

</template>
<script>
import {converObjToUrl,baseURL} from '../utils/utils'
export default {
  name: "detail",
  data() {
    return {
      user: {},
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
