<template>
  <div>
    <div class="home-page" v-if="!isDetailedView">
      <div class="button-group-header">
        <b-button v-if="isLoggedIn" @click="logOut"> Log out</b-button>
        <b-button v-if="isLoggedIn" @click="myProfileRoute">My Profile</b-button>
        <b-button v-if="!isLoggedIn" @click="loginRoute"> Login</b-button>
        <b-button v-if="!isLoggedIn" @click="registerRoute"> Register</b-button>
        <b-button v-if="isLoggedIn && notCustomer" @click="dashboardRoute">DASHBOARD</b-button>
      </div>

      <b-container fluid="">
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
                  label="Search"
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
                      class="filter-search"
                      placeholder="Type to Search"
                  ></b-form-input>

                  <b-input-group-append>
                    <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                  </b-input-group-append>
                </b-input-group>
              </b-form-group>
            </b-col>

          </b-row>
          <div class="complex-search">
            <b-input class="complex-search-input" type="text" placeholder="Name" v-model="manifestationSearch.name" ></b-input>
            <b-input class="complex-search-input" type="text" placeholder="City"  v-model="manifestationSearch.city" ></b-input>
            <b-input class="complex-search-input" type="text" placeholder="Price from" v-model="manifestationSearch.priceFrom" ></b-input>
            <b-input class="complex-search-input" type="text" placeholder="Price to" v-model="manifestationSearch.priceTo" ></b-input>

            <div class="complex-search-date">
              <label>Date from</label>
              <b-input type="date" v-model="manifestationSearch.dateFrom"></b-input>
            </div>
            <div class="complex-search-date">
              <label >Date to</label>
              <b-input type="date" v-model="manifestationSearch.dateTo"></b-input>
            </div>

            <b-button class="complex-search-button" @click="complexSearch">Search</b-button>

          </div>
          <!-- Main table element -->
          <b-table
              :items="items"
              :fields="fields"
              :filter="filter"
              :filter-included-fields="filterOn"
              :sort-by.sync="sortBy"
              :sort-desc.sync="sortDesc"
              :sort-direction="sortDirection"
              stacked="md"
              show-empty
              small
              class="table-home-page"
          >
            <template #cell(name)="row">
              {{ row.value }}
            </template>

            <template #cell(picture)="row">
              {{ row.value }}
            </template>
            <template #cell(details)="row">
              <b-button size="sm" @click="info(row.item)" class="mr-1">

                Details
              </b-button>
              <b-button v-if="isSeller" size="sm" @click="editModalInfo(row.item, row.index, $event.target)" class="mr-1">
                Edit
              </b-button>
              <b-button v-if="row.item.status === 'INACTIVE' && isAdmin" size="sm" @click="approveManifestation(row.item)" class="mr-1">
                Approve
              </b-button>
            </template>

            <template #row-details="row">
              <b-card>
                <ul>
                  <li v-for="(value, key) in row.item" :key="key">
                    {{ key }}: {{ value }}
                  </li>
                </ul>
              </b-card>
            </template>
          </b-table>

          <!-- Info modal -->




          <!-- Edit modal -->
            <b-modal class="edit-modal-container" :id="this.editModal.id" :title="this.editModal.title" @ok="updateManifestation" >

              <b-form-group
                  label="Name:"
              >
                <b-input disabled v-model="this.manifestation.name" required></b-input>
              </b-form-group>

              <b-form-group
                  label="Current date:"
              >
                <b-card>{{ this.manifestation.date }}</b-card>
              </b-form-group>
              <b-form-group
                  label="Choose new date:"
              >
                <b-input type="date" v-model="editDate" required></b-input>
              </b-form-group>

              <b-form-group
                  label="Date:"
              >
                <b-form-timepicker v-model="editTime" placeholder="Choose new time" locale="en"></b-form-timepicker>
              </b-form-group>


              <b-form-group
                  label="Price:"
              >
                <b-input v-model="manifestation.price" required></b-input>
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


              <b-form-group label="Type:" >
                <b-form-select
                    id="input-3"
                    v-model="manifestation.type"
                    :options="manifestationTypes"
                    required
                ></b-form-select>
              </b-form-group>
              <b-form-group
                  label="Status:"
              >
                <b-card>{{ this.manifestation.status }}</b-card>
              </b-form-group>

              <b-form-group
                  label="Capacity:"
              >
                <b-input required v-model="this.manifestation.capacity"></b-input>
              </b-form-group>

            </b-modal>
        </b-container>
    </div>
    <div  v-if="isDetailedView">
      <manifestation-detailed-view-component v-on:goHome="showHomeComponent" :manifestation="this.manifestation"></manifestation-detailed-view-component>
    </div>
  </div>
</template>

<script>
import api from '../backend-api'
import ManifestationDetailedViewComponent from "@/components/ManifestationDetailedViewComponent";
export default {
  name: "HomeComponent",
  components: {ManifestationDetailedViewComponent},
  data() {
    return {
      items: [],
      fields: [
        { key: 'name', label: 'Manifestation Name', sortable: true, sortDirection: 'desc' },
        { key: 'dateAndTime', label: 'Date', sortable: true, class: 'text-center' },
        { key: 'location.address.city', label: 'Location', sortable: true, class: 'text-center' },
        { key: 'type', label: 'Type', sortable: true, class: 'text-center' },
        { key: 'regularPrice', label: 'Price', sortable: true, class: 'text-center' },
        { key: 'rating', label: 'Rating', sortable: true, class: 'text-center'},
        { key: 'details', label: 'Details' }
      ],
      manifestationTypes:["THEATRE", "FESTIVAL", "CONCERT", "OTHER"],
      isSeller:false,
      manifestation:{
        name:"",
        dateAndTime:"",
        type:"",
        location: {
          address: {
            city: "",
            postalCode: "",
            streetName: "",
            streetNumber: "",
          },
          longitude:"",
          latitude:"",
        },
        price:"",
        rating:"",
        posterImagePath:"logo.png",
        status:"",
        capacity:0
      },
      manifestationEdit:{
        name:"",
        dateAndTime:"",
        type:"",
        location: {
          address: {
            city: "",
            postalCode: "",
            streetName: "",
            streetNumber: "",
          },
          longitude:"",
          latitude:"",
        },
        regularPrice:"",
        capacity:""
      },
      isDetailedView: false,
      sortBy: '',
      sortDesc: false,
      sortDirection: 'asc',
      filter: null,
      filterOn: [],
      infoModal: {
        id: 'info-modal',
        title: '',
        content: ''
      },
      editModal: {
        id: 'edit-modal',
        title: 'Edit Manifestation',
        content: ''
      },
      isLoggedIn: false,
      editDate:'',
      editTime:'',
      notCustomer:true,
      isAdmin : false,
      manifestationSearch: {
        name:'',
        city:'',
        dateFrom:'2000-01-01',
        dateTo:'2022-01-01',
        priceFrom:'',
        priceTo: ''}
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
    this.manifestationSearch= { name:"",
        city:"",
        dateFrom:"2000-01-01",
        dateTo:"2022-01-01",
        priceFrom:"",
        priceTo: ""};

    if(localStorage.getItem('role') === "seller"){
      this.isSeller = true;
    }
    else if(localStorage.getItem('role') === 'customer'){
      this.notCustomer = false;

    }else if(localStorage.getItem('role') === 'admin'){
      this.isAdmin = true;
    }
    this.isLoggedIn = !!localStorage.getItem("username");
    this.loadManifestations();

  },
  methods: {
    info(item) {
      this.manifestation.name = item.name;
      this.manifestation.date = item.dateAndTime;
      this.manifestation.location = item.location;
      this.manifestation.price = item.regularPrice;
      this.manifestation.rating = item.rating;
      this.manifestation.picture = item.posterImagePath;
      this.manifestation.type = item.type;
      this.manifestation.status = item.status;
      this.manifestation.capacity = item.capacity;
      this.isDetailedView = true;
    },
    editModalInfo(item, index, button) {
      this.manifestation.name = item.name;
      this.manifestation.date = item.dateAndTime;
      let date = this.manifestation.dateAndTime.split(' ')[0]
      let time = this.manifestation.dateAndTime.split(' ')[1]
      this.editDate = date;
      this.editTime = time;
      this.manifestation.location = item.location;
      this.manifestation.price = item.regularPrice;
      this.manifestation.rating = item.rating;
      this.manifestation.picture = item.posterImagePath;
      this.manifestation.type = item.type;
      this.manifestation.status = item.status;
      this.manifestation.capacity = item.capacity;
      this.$root.$emit('bv::show::modal', this.editModal.id, button)
    },
    approveManifestation(manifestation){
      api.approveManifestation(manifestation.name);
      alert("Successfully approved manifestation!")
    },

    resetInfoModal() {
        this.manifestation.name = "";
        this.manifestation.date = "";
        this.manifestation.location =  "";
        this.manifestation.price = "";
        this.manifestation.rating =  "";
        this.manifestation.picture = "";
    },
    getImgUrl(){
      return require('../assets/'+this.manifestation.picture)
    },
    loadManifestations(){

      if(localStorage.getItem('role') === 'customer') {
        api.loadManifestationsActive().then(response =>{
          this.items = response.data;
        })
      }else{
        api.loadManifestations().then(response =>{

          this.items = response.data;
        })
      }
    },
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
    dashboardRoute() {
      if (localStorage.getItem('role') === "admin"){
        this.$router.push('/admin-dashboard');
      }else if (localStorage.getItem('role') === "seller"){
        this.$router.push('/seller-dashboard');
      }
    },
    updateManifestation(){
      this.manifestationEdit.name = this.manifestation.name;
      this.manifestationEdit.location = this.manifestation.location;
      this.manifestationEdit.capacity = this.manifestation.capacity;
      this.manifestationEdit.regularPrice = this.manifestation.price;
      if(this.editTime !== '' && this.editDate !== ''){
        this.manifestationEdit.dateAndTime = this.editDate + " " + this.editTime;
      }else{
        this.manifestationEdit.dateAndTime = this.manifestation.date;
      }

      this.manifestationEdit.type = this.manifestation.type;

      api.updateManifestation(this.manifestationEdit).then();
      alert("Successfully updated manifestation!")
    },
    showHomeComponent(){
      this.isDetailedView = false;
    },
    complexSearch(){

      api.manifestationComplexSearch(this.manifestationSearch).then(response =>{
        this.items = response.data;
      })
    }


  }
}
</script>


<style scoped>
.custom-select {
  height: 40px;
  border-radius: 5px;
  border: 1px solid #ced4da;

}

.complex-search {
  display: flex;
  flex-direction: row;
  align-items: flex-end;

}
.complex-search-date > .form-control{
  height: 40px;
}
.complex-search-input {
  height: 40px;
}

.filter-search {
  height: 40px;
}

.button-group-header {
  margin-bottom: 30px;
  height: 50px;
  display: flex;
  align-items: center;

}
.btn-secondary {
  margin-left: 5px;
}

.table-home-page {
  margin-top: 30px;
}

.complex-search-button {
  height: 40px;
}

.edit-modal-container {
  width: 600px;
}

.nav-button {
  margin-right: 5px;
  border-radius: 7px;

}


</style>
