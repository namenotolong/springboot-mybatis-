import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import myhome from '@/view/myhome'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'myhome',
      component: myhome
    },
    {
      path: '/myhome',
      name: 'myhome',
      component: myhome
    }
  ]
})
