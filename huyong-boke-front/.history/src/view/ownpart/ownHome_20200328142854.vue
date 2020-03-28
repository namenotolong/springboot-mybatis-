<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import {mybar} from '../mybar'
export default {
  name : 'ownHome',
  data() {
    return {
      user: {},
      baseUrl: '',
    }
  },
  methods: {
    getUser() {
        let token = getStore("token")
        if(token) {
          this.$axios.get("/user/getUser").then(response => {
            this.user = response.data.data;
          })
        }
    },
  },
  mounted() {
    this.getUser();
    this.baseUrl = baseURL
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>

</style>
