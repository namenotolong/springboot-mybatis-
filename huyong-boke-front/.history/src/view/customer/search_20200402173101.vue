<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
  </div>
</template>
<script>
import mybar from "../mybar"
import {converObjToUrl,baseURL} from '../../utils/utils'
export default {
  name: 'search',
  data() {
    return {
      baseUrl: '',
      user: {},
      type: 0,
      key: '',
    }
  },
  methods: {
    goArticle(id) {
      this.$router.push({path: `/customer/write/${id}`})
    },
    //跳转用户
    goOwn(id) {
      this.$router.push({path: `/own/ownHome/${id}`})
    },
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
    this.key = this.$route.params.key;
    this.type = this.$route.params.type;
    console.log(this.key)
    console.log(this.type)
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>

</style>
