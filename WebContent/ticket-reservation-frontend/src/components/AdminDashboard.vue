<template>
<div>

  <div class="button-group-header">
    <b-button class="nav-button" @click="logOut"> Log out</b-button>
    <b-button class="nav-button" @click="myProfileRoute">My Profile</b-button>
    <b-button class="nav-button" @click="homeRoute">Home</b-button>
  </div>
  <div class="admin-dashboard">
  <div >
    <div class="users-table" >
      <b-container fluid="" >
        <!-- User Interface controls -->

        <b-row>
          <b-col lg="6" class="my-1">
            <b-form-group
                label="Sort"
                label-for="sort-by-select"
                label-cols-sm="3"
                label-align-sm="right"
                label-size="sm"
                class="mb-0"
                v-slot="{ ariaDescribedby }"
            >
              <b-input-group size="sm">
                <b-form-select
                    id="sort-by-select"
                    v-model="sortBy"
                    :options="sortOptions"
                    :aria-describedby="ariaDescribedby"
                    class="w-75"
                >
                  <template #first>
                    <option value="">-- none --</option>
                  </template>
                </b-form-select>

                <b-form-select
                    v-model="sortDesc"
                    :disabled="!sortBy"
                    :aria-describedby="ariaDescribedby"
                    size="sm"
                    class="w-25"
                >
                  <option :value="false">Asc</option>
                  <option :value="true">Desc</option>
                </b-form-select>
              </b-input-group>
            </b-form-group>
          </b-col>


          <b-col lg="6" class="my-1">
            <b-form-group
                label="Filter"
                label-for="filter-input"
                label-cols-sm="3"
                label-align-sm="right"
                label-size="sm"
                class="mb-0"
            >
              <b-input-group size="sm">
                <b-form-input
                    id="filter-input"
                    v-model="filter"
                    type="search"
                    placeholder="Type to Search"
                ></b-form-input>

                <b-input-group-append>
                  <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                </b-input-group-append>
              </b-input-group>
            </b-form-group>
          </b-col>
        </b-row>

        <!-- Main table element -->
          <div class="header-table">
          <h3>ALL USERS</h3>
          <b-table
              :items="users"
              :fields="fields"
              :filter="filter"
              :filter-included-fields="filterOn"
              :sort-by.sync="sortBy"
              :sort-desc.sync="sortDesc"
              :sort-direction="sortDirection"
              stacked="md"
              show-empty
              small
              @filtered="onFiltered"
          >
            <template #cell(name)="row">
              {{ row.value.first }} {{ row.value.last }}
            </template>

          </b-table>
        </div>
    </b-container>

    </div>
  </div>
    <div class="add-seller">
  <div class="register">
    <h1>Add seller</h1>
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
            placeholder="Enter  first name"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Your Last Name:" >
        <b-form-input
            v-model="user.lastName"
            type="text"
            placeholder="Enter  last name"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Your Password:">
        <b-form-input
            v-model="user.password"
            type="password"
            placeholder="Enter  password"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Confirm  Password:" >
        <b-form-input
            v-model="passwordAgain"
            type="password"
            placeholder="Enter  password"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Input  birth date:" >
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
 </div>
</div>
</template>

<script>
import api from '../backend-api'
export default {
name: "AdminDashboard",
  data() {
    return {
      fields: [
        { key: 'username', label: 'Username', sortable: true },
        { key: 'firstName', label: 'First Name', sortable: true },
        { key: 'lastName', label: 'Last Name', sortable: true },
        { key: 'gender', label: 'Gender' },
        { key: 'birthDate', label: 'Birth Date', sortable: true },
        { key: 'role', label: 'Role', sortable: true },
        { key: 'customerType', label: 'Customer Type', sortable: true },
        { key: 'firstName', label: 'First Name', sortable: true },
        { key: 'collectedPoints', label: 'Collected Points', sortable: true },
      ],
      sortBy: '',
      sortDesc: false,
      sortDirection: 'asc',
      filter: null,
      filterOn: [],
      users:[],
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
  computed: {
    sortOptions() {
      // Create an options list from our fields
      return this.fields
          .filter(f => f.sortable)
          .map(f => {
            return { text: f.label, value: f.key }
          })
    }
  },
  mounted() {
    // Set the initial number of items
    this.loadUsers();
  },
  methods: {
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length
      this.currentPage = 1
    },
    loadUsers(){
      api.getAllUsers().then(response =>{
        console.log(response.data)
        this.users = response.data;
      })
    },
    logOut(){
      localStorage.clear();
      this.$router.push('/login')
    },

    homeRoute(){
      this.$router.push('/');
    },
    myProfileRoute(){
      this.$router.push('/profile');
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
        this.errorMessage = "Input  password again!"
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
        api.addSeller(this.user)
            .then((response) =>{
              console.log(response)
              if(response.data === "ALREADYEXISTS"){
                alert("User with that username already exists");
                event.preventDefault()
              }else{
                alert("Successfully added seller!");
                this.goHome();
              }
            });

      }else{

        alert(this.errorMessage);
      }

    },
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
  }
}
</script>

<style scoped>
.header-table{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.users-table {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.custom-select {
  width: 100%;
  height: 38px;
  border-radius: 5px;
  padding: 0 15px 0 5px;
}

.button-group-header {
  margin-bottom: 30px;
  height: 50px;
  display: flex;
  align-items: center;

}
.nav-button {
  margin-right: 5px;
  border-radius: 7px;

}
.add-seller {
  width: 30%;
  margin-right: 50px;
  margin-left: 50px;
}
.admin-dashboard {
  display: flex;
  flex-direction: row;
  justify-content: space-around;

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
