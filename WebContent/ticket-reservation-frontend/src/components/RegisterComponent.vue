<template>
<div>
    <div>
      <input type="text" placeholder="enter your username" v-model="user.username">
      <input type="text" placeholder="enter your first name" v-model="user.firstName">
      <input type="text" placeholder="enter your last name" v-model="user.lastName">
      <input type="password" placeholder="enter your password" v-model="user.password">
      <input type="password" placeholder="enter your password again" v-model="passwordAgain">
      <input type="date" v-model="this.user.birthDate" min="1900-01-01" max="2018-12-31">
      <select @change="onChangeGender($event)" name="gender">
        <option value="Male">Male</option>
        <option value="Female">Female</option>
      </select>
      <button @click="register()" >REGISTER</button>
    </div>

      <div class="alert alert-success collapse" role="alert">
        You have successfully registered
      </div>
      <div v-if="isError" role="alert">
        {{this.errorMessage}}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

</div>
</template>

<script>
import api from "../backend-api"
export default {
name: "RegisterComponent",
  data(){
    return {
      errorMessage: "",
      isError:false,
      user: {
        username: "",
        password: "",
        firstName: "",
        lastName: "",
        gender: "Male",
        birthDate:"2000-01-01",



      },
      passwordAgain: "",
        genders:["Male", "Female"],

    }
  },
  methods:{
    register(){
      if (this.valid() === true) {
        api.register(this.user);
      }else{
        this.isError = true;
      }
      console.log(this.user.birthDate);

    },
    valid() {
      if (this.user.username === "") {
        this.errorMessage = "Name field is empty!"
        return false
      } else if (this.user.firstName === "") {
        this.errorMessage = "First name field is empty!"
        return false
      } else if (this.user.lastName === "") {
        this.errorMessage = "Last name field is empty!"
        return false
      } else if (this.user.password === "") {
        this.errorMessage = "Password field is empty!"
        return false
      } else if (this.user.passwordAgain === "") {
        this.errorMessage = "Input your password again!"
        return false
      } else if (this.user.password !== this.passwordAgain) {
        this.errorMessage = "Passwords must match!"
        return false
      } else if (this.validPassword() === false) {
        this.errorMessage = "Password must contain more than 6 characters!"
        return false
      }
      return true
    },
    onChangeGender:function(event){
      this.user.gender = event.target.value;
    },
    validPassword: function() {
      return this.user.password.length >= 6;

    }

  },
  mounted() {
  }
}
</script>

<style scoped>

</style>
