<template>
  <div>
    <b-button @click="homeRoute">Home</b-button>

  <div class="login-container">

    <div class="login">
      <h1>Log in</h1>
      <b-input required type="text" placeholder="Username" v-model="user.username" />
      <b-input required type="password" placeholder="Password" v-model="user.password" />
      <b-button variant="primary" @click="login">Log in</b-button>
    </div>
  </div>
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
    homeRoute(){
      this.$router.push('/');
    }
  }

}
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: 200px;


}
.login {
  background-color: #3F4B8D;
  height: 300px;
  width: 400px;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  padding: 50px 20px 50px 20px;
  color: white;
  border-radius: 20px;

}

</style>
