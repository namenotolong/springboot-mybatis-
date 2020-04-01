import Vue from 'vue'
import Router from 'vue-router'
import myhome from '@/view/myhome'
import own from '@/view/ownpart/own'
import write from '@/view/customer/write'
import write1 from '@/view/write1'

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
    {
      path: '/write1',
      name: 'write1',
      component: write1,
      meta: {
        requiredAuth: true
      }
    },
  ]
})