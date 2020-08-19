// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'bootstrap'
import $ from 'jquery'
import util from 'util/util.js'
import Objects from '@/util/Objects.js'
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use($);
Vue.use(Objects);
Vue.prototype.$util = util;
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
