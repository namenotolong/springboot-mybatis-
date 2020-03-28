<template>

</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
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
  }
}
</script>
<style scoped>

</style>
