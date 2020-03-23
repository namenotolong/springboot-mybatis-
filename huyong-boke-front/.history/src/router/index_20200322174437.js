import Vue from 'vue'
import Router from 'vue-router'
import myhome from '@/view/myhome'
import own from '@/view/ownpart/own'
import write from '@/view/customer/write'

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
      path: '/own',
      name: 'own',
      component: own,
      meta: {
        requiredAuth: true
      }
    },
    {
      path: '/customer/write',
      name: 'write',
      component: write,
      meta: {
        requiredAuth: true
      }
    },

  ]
})
