<template>
<div>
    <div class="button-group-header">
      <b-button v-if="isLoggedIn" @click="logOut"> Log out</b-button>
      <b-button v-if="isLoggedIn" @click="myProfileRoute">My Profile</b-button>
      <b-button v-if="!isLoggedIn" @click="loginRoute"> Login</b-button>
      <b-button v-if="!isLoggedIn" @click="registerRoute"> Register</b-button>
      <b-button v-if="isLoggedIn" @click="dashboardRoute">DASHBOARD</b-button>
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
    <!--          <img :src="getImgUrl()" alt="">-->
</div>
</template>

<script>
export default {
name: "ManifestationDetailedViewComponent",
  props:{
    manifestation:{}
  },
  data(){
    return{
      isLoggedIn:false
    }
  },
  mounted() {
    this.isLoggedIn = !!localStorage.getItem('username');
  },
  methods:{
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

    dashboardRoute(){
      if(localStorage.getItem('role') === "admin")
        this.$router.push('/admin-dashboard');
      else if (localStorage.getItem('role') === "customer"){
        this.$router.push('/customer-dashboard');
      }else if (localStorage.getItem('role') === "seller"){
        this.$router.push('/seller-dashboard');
      }
    },

  }
}
</script>

<style scoped>

</style>
