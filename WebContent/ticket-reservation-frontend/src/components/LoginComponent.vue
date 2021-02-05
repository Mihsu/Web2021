<template>
<div>

  <p>{{user.username}}</p>
  <b-input required type="text" placeholder="Username" v-model="user.username" />
  <b-input required type="password" placeholder="password" v-model="user.password" />
  <b-button @click="login">LOGIN</b-button>

</div>
</template>

<script>
import api from "../backend-api";
export default {
  name: "LoginComponent",
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      errorMessage:""
    }
  },
  methods: {
    login(){
      if(!this.valid()){
        alert(this.errorMessage);
      }else{
      api.login(this.user)
      .then((response) => {
        if (response.data !== "NOT_FOUND") {
          let data = response.data;
          let pair = data.split(",");

          localStorage.setItem("username", pair[0]);
          localStorage.setItem("role", pair[1]);
          console.log(localStorage.getItem('role'));
          alert("Logged in successfully!");
          this.$router.push('/')
        }else{
          alert("Username or password is incorrect")
        }
      })
      .catch((err) =>
        console.log(err));
      }
    },
    valid() {
      if (this.user.username === "") {
        this.errorMessage = "Name field is empty!"
        return false
      } else if (this.user.password === "") {
        this.errorMessage = "Password field is empty!"
        return false
      }
      return true
    },
  }

}
</script>

<style scoped>

</style>
