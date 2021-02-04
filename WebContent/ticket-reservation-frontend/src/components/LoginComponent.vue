<template>
<div>

  <p>{{user.username}}</p>
  <input type="text" placeholder="Username" v-model="user.username" />
  <input type="password" placeholder="password" v-model="user.password" />
  <button @click="login">LOGIN</button>

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
      }
    }
  },
  methods: {
    login(){
      api.login(this.user)
      .then((response) =>
          console.log(response))
      .catch((err) =>
        console.log(err));
    },
  }

}
</script>

<style scoped>

</style>
