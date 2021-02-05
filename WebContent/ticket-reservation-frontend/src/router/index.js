import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginComponent from "@/components/LoginComponent";
import RegisterComponent from "@/components/RegisterComponent";
import ProfileComponent from "@/components/ProfileComponent";
import HomeComponent from "@/components/HomeComponent";
import AdminDashboard from "@/components/AdminDashboard";
import SellerDashboard from "@/components/SellerDashboard";
import CustomerDashboard from "@/components/CustomerDashboard";


Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    name: 'Home',
    component: HomeComponent
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
  {
    path: '/profile',
    name: 'Profile',
    component: ProfileComponent
  },
  {
    path: '/admin-dashboard',
    name: 'Admin Dashboard',
    component: AdminDashboard
  },
  {
    path: '/seller-dashboard',
    name: 'Seller Dashboard',
    component: SellerDashboard
  },
  {
    path: '/customer-dashboard',
    name: 'Customer Dashboard',
    component: CustomerDashboard
  }

]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
