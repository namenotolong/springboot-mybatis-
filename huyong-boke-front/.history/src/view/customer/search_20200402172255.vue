<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
  </div>
</template>
<script>
import mybar from "../mybar"
import {converObjToUrl,baseURL} from '../..//utils/utils'
export default {
  name: 'search',
  data() {
    return {
      baseUrl: '',
      user: {},
    }
  }
}
</script>
<style lang="sass" scoped>

</style>
