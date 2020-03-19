// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import AxiosConfig from './config/axiosConfig'
Vue.prototype.$axios = new AxiosConfig().axiosService
Vue.use(ElementUI);
router.beforeEach((to, from, next) => {
  console.log(11111111)
  if (to.matched.length != 0) {
    if (to.meta.requiredAuth) { // 判断该路由是否需要登录权限
      if (Boolean(localStorage.getItem("authorization"))) { // 通过vuex state获取当前的user是否存在
        next();
      } else {
        ElementUI.Message.error({message: '您还未登陆，您还未登陆请先登录'})
        next({
          path: '/'
        })
      }
    } else {
      next();
    }
  }
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  components: { App },
  template: '<App/>'
})
