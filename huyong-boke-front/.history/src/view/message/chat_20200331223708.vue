<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <div class="out-container">
      <div>
        <div class="left-container">

        </div>
        <div class="right-container">
          
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'chat',
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
