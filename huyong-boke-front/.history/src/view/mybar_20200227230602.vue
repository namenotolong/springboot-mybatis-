<template>
  <nav>
    <div>
      <div class="nav-left">
      <img src="" alt="my nav">
      </div>
    <div class="nav-middle">
      <ul class="nav-ul">
        <li>
          <a href="#">
            <span>
              发现
            </span>
          </a>
        </li>
        <li>
          <a href="#">
            <span>
              关注
            </span>
          </a>
        </li>
        <li>
          <a href="#">
            <span>
              消息
            </span>
          </a>
        </li>
      </ul>
    </div>
    </div>
  </nav>
</template>
<script>
export default {

}
</script>
<style lang="postcss" scoped>
.nav-left{
  display: inline;
}
.nav-middle{
  display: inline;
  float: left;
}
.nav-ul{
  display: inline;
}
.nav-ul li{
  list-style: none;
  float: left;
}
a{
  text-decoration: none
}
</style>
