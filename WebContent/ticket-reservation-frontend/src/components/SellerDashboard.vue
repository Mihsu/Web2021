<template>
<div>
  <b-button class="nav-button" @click="logOut"> Log out</b-button>
  <b-button class="nav-button" @click="homeRoute">Home</b-button>
  <div class="seller-dashboard">
    <div class="create-manifestation-form">

      <h1>Add new MANIFESTATION</h1>
      <b-form @submit="onSubmit" class="add-manifestation" @reset="onReset" v-if="show">
        <b-form-group label="Manifestation Name:">
          <b-form-input
              v-model="manifestation.name"
              type="text"
              placeholder="Enter manifestation name"
              required
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Price :">
          <b-form-input
              v-model="manifestation.regularPrice"
              type="text"
              placeholder="Enter manifestation price"
              required
          ></b-form-input>
        </b-form-group>

        <b-form-group label="City:">
          <b-form-input
              v-model="manifestation.location.address.city"
              type="text"
              placeholder="Enter manifestation city"
              required
          ></b-form-input>
        </b-form-group>
        <div class="address">
          <b-form-group label="Street name:" class="street-name">
            <b-form-input
                v-model="manifestation.location.address.streetName"
                type="text"
                placeholder="Street name"
                required
            ></b-form-input>
          </b-form-group>
          <b-form-group label="Number:" class="street-number">
            <b-form-input
                v-model="manifestation.location.address.streetNumber"
                type="text"
                placeholder="Number"
                required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Postal code:" class="postal-code">
            <b-form-input
                v-model="manifestation.location.address.postalCode"
                type="text"
                placeholder="Enter postal code"
                required
            ></b-form-input>
          </b-form-group>
        </div>

        <div class="longitude-latitude-container">
          <b-form-group label="Latitude:">
            <b-form-input
                v-model="manifestation.location.latitude"
                type="text"
                placeholder="Enter Latitude"
                required
            ></b-form-input>
          </b-form-group>
          <b-form-group label="Longitude:">
          <b-form-input
              v-model="manifestation.location.longitude"
              type="text"
              placeholder="Enter Longitude"
              required
          ></b-form-input>
          </b-form-group>
        </div>


        <b-form-group
            label="Capacity :"
            description="How many people can come to this manifestation?"
        >
          <b-form-input
              id="input-1"
              v-model="manifestation.capacity"
              type="number"
              placeholder="Enter manifestation capacity"
              required
          ></b-form-input>

            <div class="date-time-picker">
            <label >Choose a date</label>
            <b-form-datepicker  v-model="date" ></b-form-datepicker>
            </div>
            <div class="date-time-picker">
            <label>Choose the time</label>
            <b-form-timepicker   v-model="time" placeholder="Choose a time" locale="en"></b-form-timepicker>
            </div>
        </b-form-group>
          <b-form-group label="Type:" >
            <b-form-select
                id="input-3"
                v-model="manifestation.type"
                :options="manifestationTypes"
                required
            ></b-form-select>
          </b-form-group>

        <div class="submit-button-group">
          <b-button class="submit-btn" type="submit" variant="primary">Submit</b-button>
          <b-button class="reset-btn" type="reset" variant="danger">Reset</b-button>
        </div>
      </b-form>
     </div>

    <div class="desni-deo">
      <h1>RESERVED TICKETS</h1>
      <template>
        <div>
          <b-table striped hover :items="tickets" :fields="fields" ></b-table>
        </div>
      </template>
    </div>
  </div>
</div>
</template>

<script>
import api from '../backend-api'
export default {
name: "SellerDashboard",
  data(){
  return {
    form: {
      email: '',
      name: '',
      food: null,
      checked: []
    },
    foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
    show: true,
    manifestationTypes:["THEATRE", "FESTIVAL", "CONCERT", "OTHER"],
    manifestation:{
      name:"",
      type:"OTHER",
      dateAndTime: "",
      regularPrice:"",
      location:{
        address:{
          streetName:"",
          streetNumber: "",
          city:"",
          postalCode:""
        },
        latitude:"",
        longitude:""
      }
    },
    date:Date,
    time:'',
    tickets:[],
    fields: ['id', 'manifestationName', 'dateAndTime', 'price', 'buyerUsername', 'ticketType'],
  }
},
  mounted() {
    api.getReservedTickets().then(response =>{
      console.log(response)
      this.tickets = response.data;
    })
  },
  methods: {
  onSubmit(event) {
    this.manifestation.dateAndTime = this.date + " " + this.time;
    console.log(this.manifestation.dateAndTime);
    event.preventDefault();
    api.createManifestation(this.manifestation);
    alert("Successfully added manifestation!");
    this.onReset1();

  },
    logOut(){
      localStorage.clear();
      this.$router.push('/login')
    },
    homeRoute(){
      this.$router.push('/');
    },
    onReset(event) {
      event.preventDefault()
      this.manifestation = {
        name: "",
        type: "",
        dateAndTime: "",
        regularPrice: "",
        location: {
          address: {
            streetName: "",
            streetNumber: "",
            city: "",
            postalCode: ""
          },
          latitude: "",
          longitude: ""
        }
      }
    },
        onReset1(){
        this.manifestation={
          name:"",
          type:"",
          dateAndTime: "",
          regularPrice: "",
          location:{
            address:{
              streetName:"",
              streetNumber: "",
              city:"",
              postalCode:""
            },
            latitude:"",
            longitude:""
          }
        }
    this.date="";
    this.time="";
    this.show = false
    this.$nextTick(() => {
      this.show = true
    })
  }
}
}
</script>

<style scoped>

.seller-dashboard {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.form-group {
  margin-top: 15px;

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
.nav-button {
  margin-right: 5px;
  border-radius: 7px;

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

.address {
  display: flex;

}

.street-number {
  width: 100px;
}

.longitude-latitude-container {
  display: flex;

}
.longitude-latitude-container .form-group {
  width: 50%;
}

.date-time-picker {
 margin-top: 15px;
}

.submit-button-group {
  width: 90%;
  margin-top: 10px;
  display: flex;
  justify-content: space-around;

}

.submit-btn {
  width: 70%;
}
</style>
