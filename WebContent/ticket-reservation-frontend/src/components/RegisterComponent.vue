<template>
<div class="register-container">
    <div class="register">
      <h1>Register</h1>
      <b-form class="register-form" @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group label="Username:"
        >
          <b-form-input
              v-model="user.username"
              type="text"
              placeholder="Enter username"
              required
          ></b-form-input>
        </b-form-group>


        <b-form-group label="Your Name:" >
          <b-form-input
              v-model="user.firstName"
              type="text"
              placeholder="Enter your first name"
              required
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Your Last Name:" >
          <b-form-input
              v-model="user.lastName"
              type="text"
              placeholder="Enter your last name"
              required
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Your Password:">
          <b-form-input
              v-model="user.password"
              type="password"
              placeholder="Enter your password"
              required
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Confirm Your Password:" >
          <b-form-input
              v-model="passwordAgain"
              type="password"
              placeholder="Enter your password"
              required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Input Your birth date:" >
          <b-form-input
              v-model="user.birthDate"
              type="date"
              required
          ></b-form-input>
        </b-form-group>

        <b-form-group class="gender-select" label="Gender:" >
          <b-form-select
              id="input-3"
              v-model="user.gender"
              :options="genders"
              required
          ></b-form-select>
        </b-form-group>
        <div class="button-group-submit">
          <b-button class="submit-button" type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </div>

      </b-form>
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
        gender: "MALE",
        birthDate:"2000-01-01",

      },
      passwordAgain: "",
      genders:["MALE", "FEMALE"],
      form: {
        email: '',
        name: '',
        gender: null
      },
      show: true

    }
  },
  methods:{

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

    validPassword: function() {
      return this.user.password.length >= 6;

    },onSubmit(event) {
      event.preventDefault()
      if (this.valid() === true) {
       api.register(this.user)
       .then((response) =>{
         console.log(response)
         if(response.data === "ALREADYEXISTS"){
           alert("User with that username already exists");
           event.preventDefault()
         }else{
           alert("Successfully registered, you can log in now!");
           this.goHome();
         }
       });

      }else{

        alert(this.errorMessage);
      }

    },
    goHome(){
      this.$router.push('login')
    }
    ,
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.user= {
           username: "",
            password: "",
            firstName: "",
            lastName: "",
            gender: "MALE",
            birthDate:"2000-01-01"}
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }

  },
  mounted() {
  }
}
</script>

<style scoped>
.register-container {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;


}
.register {
  background-color: #3F4B8D;
  display: flex;
  width: fit-content;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  padding: 30px 0 20px 0;
  color: white;
  border-radius: 20px;

}

.form-group {
  margin-top: 15px;

}

.form-control {
  width: 400px;
}

.register-form {
  display: flex;
  flex-direction: column;
  width: fit-content;
  padding: 20px 20px 20px 20px;
  align-items: flex-start;
}

.gender-select {
  width: 100%;

}
.custom-select {
  width: 100%;
  height: 38px;
  border-radius: 5px;
  padding: 0 15px 0 5px;
}
.button-group-submit {
  display: flex;
  width: 100%;
  justify-content: space-around;
  margin-top: 20px;
}

.submit-button {
  width: 70%;
}
</style>
