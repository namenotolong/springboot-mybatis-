<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
      <div class="user-container">
        <div>
          <el-avatar :size="30">
            <img :src="baseUrl + item.user.picture"/>
          </el-avatar>
        </div>
      </div>
      <div class="kind-container">

      </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'ownHome',
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
