import Vue from 'vue'
import Router from 'vue-router'
import myhome from '@/view/myhome'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'myhome',
      component: myhome,
      meta: {
        requiredAuth: false
      }
    },
    {
      path: '/myhome',
      name: 'myhome',
      component: myhome,
      meta: {
        requiredAuth: false
      }
    }
  ],
  beforeEach((to, from, next) => {
    if (to.matched.length != 0) {
      if (to.meta.requiredAuth) { // 判断该路由是否需要登录权限
        if (Boolean(localStorage.getItem("token"))) { // 通过vuex state获取当前的user是否存在
          next();
        } else {
          alert('您还未登陆，您还未登陆请先登录')
          next({
            path: '/'
          })
        }
      } else {
        next();
      }
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  })
})
