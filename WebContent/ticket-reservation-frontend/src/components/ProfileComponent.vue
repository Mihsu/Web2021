<template>
<div>
  <b-input-group>
    <template #prepend>
      <b-input-group-text>Username</b-input-group-text>

    </template>
    <b-form-input disabled v-model="user.username"></b-form-input>
  </b-input-group>

  <b-input-group>
    <template #prepend>
      <b-input-group-text>First Name</b-input-group-text>

    </template>
    <b-form-input v-model="user.firstName"></b-form-input>
  </b-input-group>

  <b-input-group>
    <template #prepend>
      <b-input-group-text>Last Name</b-input-group-text>

    </template>
    <b-form-input v-model="user.lastName"></b-form-input>
  </b-input-group>

  <b-input-group>
    <template #prepend>
      <b-input-group-text disabled="">birthDate</b-input-group-text>

    </template>
    <b-form-input disabled v-model="user.birthDate"></b-form-input>
  </b-input-group>

  <b-input-group>
    <template #prepend>
      <b-input-group-text >Gender</b-input-group-text>

    </template>
    <b-form-input disabled v-model="user.gender"></b-form-input>
  </b-input-group>

  <div v-if="isCustomer" >
    <b-input-group>
      <template #prepend>
        <b-input-group-text >CollectedPoints</b-input-group-text>

      </template>
      <b-form-input disabled v-model="user.collectedPoints"></b-form-input>
    </b-input-group>
  </div>
  <b-input-group>
    <template #prepend>
      <b-input-group-text>MEMBERSHIP:</b-input-group-text>

    </template>
    <b-form-input disabled v-model="membership"></b-form-input>
  </b-input-group>
  <b-button @click="updateInfo" variant="outline-primary">Change Info</b-button>
</div>
</template>

<script>
import api from "../backend-api";
export default {
name: "ProfileComponent",
  data(){
    return {
      user: {
        username: "",
        firstName: "",
        lastName: "",
        gender: "",
        birthDate:"",
        collectedPoints:0
      },
      isCustomer: false,
      membership: "BRONZE"
    }
  },
  mounted() {
    let role = localStorage.getItem("role");
    let username = localStorage.getItem("username");

    console.log(role,username);
    api.getLoggedIn(username,role).then((response)=>{
      if(role === "customer"){
        this.user.username = response.data.username
        this.user.lastName = response.data.lastName
        this.user.birthDate = response.data.birthDate
        this.user.gender = response.data.gender
        this.user.firstName = response.data.firstName
        this.user.collectedPoints = response.data.collectedPoints
        this.isCustomer = true;
        if(this.user.collectedPoints >= 500 && this.user.collectedPoints < 1000){
          this.membership = "SILVER";
        }else if(this.user.collectedPoints >= 1000 && this.user.collectedPoints < 1500){
          this.membership = "GOLD";
        }else if(this.user.collectedPoints >= 1500){
          this.membership = "DIAMOND";
        }

      }else{
        this.user.username = response.data.username
        this.user.lastName = response.data.lastName
        this.user.birthDate = response.data.birthDate
        this.user.gender = response.data.gender
        this.user.firstName = response.data.firstName
      }
    })
    .catch(err => console.log(err))

  },
  methods: {
    updateInfo(){
      api.updateInfo(this.user, localStorage.getItem("role")).then(response =>{
        if(response.data === "OK"){
          alert("Successfully updated info!")
        }else{
          alert("There was an error.")
        }
      })
    }

  },

}

</script>

<style scoped>

</style>
