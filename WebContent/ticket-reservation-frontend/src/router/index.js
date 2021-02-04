import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginComponent from "@/components/LoginComponent";
import RegisterComponent from "@/components/RegisterComponent";

Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Home',
    component: LoginComponent
  },
  {
    path: '/login',
    name: 'Login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: LoginComponent
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterComponent
  },

]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
