<template>
<div class="seller-dashboard">
<!--  levi i desni deo samo da znam koji je za sta kasnije nije pisan css za njih, promeniti ime klasa-->
  <div class="levi-deo">

    <h1>Add new MANIFESTATION</h1>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
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
      <b-form-group label="Street name:">
        <b-form-input
            v-model="manifestation.location.address.streetName"
            type="text"
            placeholder="Enter manifestation street name:"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Street number:">
        <b-form-input
            v-model="manifestation.location.address.streetNumber"
            type="text"
            placeholder="Enter manifestation street number:"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Postal code:">
        <b-form-input
            v-model="manifestation.location.address.postalCode"
            type="text"
            placeholder="Enter postal code"
            required
        ></b-form-input>
      </b-form-group>

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


          <label >Choose a date</label>
          <b-form-datepicker  v-model="date" ></b-form-datepicker>
          <label>Choose the time</label>
          <b-form-timepicker v-model="time" placeholder="Choose a time" locale="en"></b-form-timepicker>

      </b-form-group>
        <b-form-group label="Type:" >
          <b-form-select
              id="input-3"
              v-model="manifestation.type"
              :options="manifestationTypes"
              required
          ></b-form-select>
        </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
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
      type:"",
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
    api.createManifestation(this.manifestation).then(response =>{
      console.log(response)
    })
  },
  onReset(event) {
    event.preventDefault()
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
</style>
