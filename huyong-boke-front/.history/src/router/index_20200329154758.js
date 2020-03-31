import Vue from 'vue'
import Router from 'vue-router'
import myhome from '@/view/myhome'
import write from '@/view/customer/write'
import detail from '@/view/customer/detail'
import ownHome from '@/view/ownpart/ownHome'

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
      path: '/customer/detail/:id',
      name: 'detail',
      component: detail,
      meta: {
        requiredAuth: false
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
      path: '/customer/write/:id',
      name: 'write',
      component: write,
      meta: {
        requiredAuth: true
      }
    },
    {
      path: '/own/ownHome/:id',
      name: 'ownHome',
      component: ownHome,
      meta: {
        requiredAuth: false
      }
    }
  ]
})
