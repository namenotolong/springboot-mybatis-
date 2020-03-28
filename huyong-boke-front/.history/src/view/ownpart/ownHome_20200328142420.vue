<template>

</template>
<script>
export default {
  name : 'ownHome',
  data() {
    return {
      user: {},
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

  }
}
</script>
<style scoped>

</style>
