<template>
<div>
<div v-if="!goHome">
    <div class="button-group-header">
      <b-button class="nav-button" v-if="isLoggedIn" @click="logOut"> Log out</b-button>
      <b-button class="nav-button" v-if="isLoggedIn" @click="myProfileRoute">My Profile</b-button>
      <b-button class="nav-button" v-if="!isLoggedIn" @click="loginRoute"> Login</b-button>
      <b-button class="nav-button" v-if="!isLoggedIn" @click="registerRoute"> Register</b-button>
      <b-button class="nav-button" v-if="isLoggedIn && notCustomer" @click="dashboardRoute">DASHBOARD</b-button>
      <b-button class="nav-button" @click="showHomeComponent">Home</b-button>
    </div>
    <b-form-group
        label="Name:"
    >
      <b-card>{{ this.manifestation.name }}</b-card>
    </b-form-group>

    <b-form-group
        label="Date:"
    >
      <b-card>{{ this.manifestation.date }}</b-card>
    </b-form-group>

    <b-form-group
        label="Price:"
    >
      <b-card>{{ this.manifestation.price }}</b-card>
    </b-form-group>
    <b-form-group
        label="Location:"
    >
      <b-card><i>City : </i>{{ this.manifestation.location.address.city }} , <i>Address : </i>  {{ this.manifestation.location.address.streetName}} {{this.manifestation.location.address.streetNumber }},
        {{this.manifestation.location.address.postalCode}}
      </b-card>
      <b-card> <i>Latitude :</i> {{this.manifestation.location.latitude}}, <i>Longitude : </i> {{this.manifestation.location.longitude}}

      </b-card>
    </b-form-group>
    <b-form-group
        label="Type:"
    >
      <b-card>{{ this.manifestation.type }}</b-card>
    </b-form-group>
    <b-form-group
        label="Status:"
    >
      <b-card>{{ this.manifestation.status }}</b-card>
    </b-form-group>

    <b-form-group
        label="Capacity:"
    >
      <b-card>{{ this.manifestation.capacity }}</b-card>
    </b-form-group>
      <img :src="fullPath" alt=""/>

</div>
  <div class="comments-view" v-for="comment in comments" :key="comment">
    <b-card
        :title="comment.customerUsername"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 20rem;"
        class="mb-2"
    >
      <b-card-text>
        <b-form-rating v-model="comment.rating" disabled stars="5"></b-form-rating>
        {{ comment.content }}
      </b-card-text>
      <b-button v-if="!comment.approved" @click="approveComment(comment.id)"> Approve</b-button>
    </b-card>
  </div>
 </div>
</template>

<script>
import api from '../backend-api'
export default {
name: "ManifestationDetailedViewComponent",
  components: {},
  props:{
    manifestation:{}
  },
  data(){
    return{
      isLoggedIn:false,
      comments:[],
      notCustomer:true,
      goHome: false,
      fullPath:""
    }
  },
  mounted() {
    this.isLoggedIn = !!localStorage.getItem('username');
    if(localStorage.getItem('role') ==="customer"){
      this.notCustomer = false;
    }
    this.loadComments();
    this.fullPath = require('../assets/'+this.manifestation.posterImagePath);
  },
  methods:{
    loadComments(){
      if(localStorage.getItem('role') === 'admin' || (localStorage.getItem('role') === 'seller')){

        api.getAllComments(this.manifestation.name).then(response =>{
          this.comments = response.data;
        })
      }else{
        api.getApprovedComments(this.manifestation.name).then(response => {

          this.comments = response.data;
        });
      }

    },
    logOut(){
      localStorage.clear();
      this.$router.push('/login')
    },
    registerRoute(){
      this.$router.push('/register');
    },
    loginRoute(){
      this.$router.push('/login');
    },
    myProfileRoute(){
      this.$router.push('/profile');
    },
    showHomeComponent(){
      this.$emit('goHome');
    },

    dashboardRoute(){
      if(localStorage.getItem('role') === "admin")
        this.$router.push('/admin-dashboard');
      else if (localStorage.getItem('role') === "customer"){
        this.$router.push('/customer-dashboard');
      }else if (localStorage.getItem('role') === "seller"){
        this.$router.push('/seller-dashboard');
      }
    },
    approveComment(commentId){
      api.approveComment(commentId);
      alert("Comment approved!");

    }


  }
}
</script>

<style scoped>
.nav-button {
  margin-right: 5px;
  border-radius: 7px;

}
</style>
