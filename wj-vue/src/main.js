import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)

// 首先判断访问的路径是否需要登录，
// 如果需要，判断 store 里有没有存储 user 的信息，
// 如果存在，则放行，
// 否则跳转到登录页面，并存储访问的页面路径（以便在登录后跳转到访问页）
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
      if (store.state.user.username) {
        next()
      } else {
        next({
          path: 'login',
          query: {redirect: to.fullPath}
        })
      }
    } else {
      next()
    }
  }
)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})