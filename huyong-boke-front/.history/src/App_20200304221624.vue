<template>
  <div id="app">
    <mybar></mybar>
    <router-view/>
  </div>
</template>

<script>
import mybar from "./view/mybar"
export default {
  name: 'App',
  components : {
    mybar
  }
}
</script>
