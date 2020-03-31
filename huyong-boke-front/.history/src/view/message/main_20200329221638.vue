<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
export default {
  name : 'main',
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
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>

</style>
