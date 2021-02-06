import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import 'popper.js';

import BootstrapVue from "bootstrap-vue";


Vue.config.productionTip = false
Vue.use(BootstrapVue)

new Vue({
  router,
  render: function (h) { return h(App) }
}).$mount('#app')
