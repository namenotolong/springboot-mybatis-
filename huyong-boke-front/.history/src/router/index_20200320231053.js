import Vue from 'vue'
import Router from 'vue-router'
import myhome from '@/view/myhome'
import own from '@/view/own'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'myhome',
      component: myhome,
    },
    {
      path: '/own',
      name: 'own',
      component: own,
    }
  ]
})
