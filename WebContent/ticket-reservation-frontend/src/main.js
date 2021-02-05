import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap';
import 'popper.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import BootstrapVue from "bootstrap-vue";

Vue.config.productionTip = false
Vue.use(BootstrapVue)

new Vue({
  router,
  render: function (h) { return h(App) }
}).$mount('#app')
