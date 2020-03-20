import Vue from 'vue'
import Router from 'vue-router'
import myhome from '@/view/myhome'

Vue.use(Router)

export default new Router({
  mode: 'history',
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

    }
  ]
})
