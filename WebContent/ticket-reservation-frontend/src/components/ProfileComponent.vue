<template>
  <div>

    <b-button class="nav-button" @click="logOut"> Log out</b-button>
    <b-button class="nav-button" @click="homeRoute">Home</b-button>

<div class="my-profile-container">
  <div class="my-profile-info-container">
    <div class="my-profile-info">
      <b-input-group>
        <template #prepend>
          <b-input-group-text  class="my-profile-info-labels">Username:</b-input-group-text>

        </template>
        <b-form-input disabled v-model="user.username"></b-form-input>
      </b-input-group>

      <b-input-group>
        <template #prepend>
          <b-input-group-text class="my-profile-info-labels">First Name:</b-input-group-text>

        </template>
        <b-form-input v-model="user.firstName"></b-form-input>
      </b-input-group>

      <b-input-group>
        <template #prepend>
          <b-input-group-text class="my-profile-info-labels">Last Name:</b-input-group-text>

        </template>
        <b-form-input v-model="user.lastName"></b-form-input>
      </b-input-group>

      <b-input-group>
        <template #prepend>
          <b-input-group-text disabled="" class="my-profile-info-labels">Birth date:</b-input-group-text>

        </template>
        <b-form-input disabled v-model="user.birthDate"></b-form-input>
      </b-input-group>

      <b-input-group>
        <template #prepend>
          <b-input-group-text class="my-profile-info-labels" >Gender:</b-input-group-text>

        </template>
        <b-form-input disabled v-model="user.gender"></b-form-input>
      </b-input-group>

      <div v-if="isCustomer" >
        <b-input-group>
          <template #prepend>
            <b-input-group-text class="my-profile-info-labels">Collected Points:</b-input-group-text>

          </template>
          <b-form-input disabled v-model="user.collectedPoints"></b-form-input>
        </b-input-group>
      </div>
      <b-input-group>
        <template #prepend>
          <b-input-group-text class="my-profile-info-labels" >MEMBERSHIP:</b-input-group-text>

        </template>
        <b-form-input disabled v-model="membership"></b-form-input>
      </b-input-group>
      <b-button class="change-info-btn" @click="updateInfo" variant="outline-primary">Change Info</b-button>
      </div>
    </div>
  <div class="table-search-sort" v-if="isCustomer">
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
      <div class="complex-search">
        <b-input class="complex-search-input" type="text" v-model="complexTicket.priceFrom" placeholder="Price from"></b-input>
        <b-input class="complex-search-input" type="text" v-model="complexTicket.priceTo" placeholder="Price to"></b-input>

        <div>
          <label>Date from</label>
          <b-input type="date"  v-model="complexTicket.dateFrom" ></b-input>
        </div>
        <div>
          <label >Date to</label>
          <b-input type="date"  v-model="complexTicket.dateTo"  ></b-input>
        </div>

        <b-button @click="submitComplexSearch">SEARCH</b-button>

      </div>
      <b-table
          :items="tickets"
          :fields="fields"
          :filter="filter"
          :filter-included-fields="filterOn"
          :sort-by.sync="sortBy"
          :sort-desc.sync="sortDesc"
          :sort-direction="sortDirection"
          stacked="md"
          show-empty
          small
          class="tickets-table"
      >
        <template #cell(name)="row">
          {{ row.value.first }} {{ row.value.last }}
        </template>

        <template #cell(comment)="row">
          <b-button v-if="row.item.commentable" size="sm" @click="openCommentModal(row.item, row.index, $event.target)" class="mr-1">
            COMMENT
          </b-button>
        </template>
      </b-table>
    </b-container>
  </div>
  <div>
    <b-modal :id="this.commentModal.id" title="Leave a comment" @ok="submitComment">
      <div>
        <b-form-rating v-model="comment.rating"></b-form-rating>
      </div>
      <b-form-textarea v-model="comment.content" placeholder="Leave a comment..."></b-form-textarea>
    </b-modal>
  </div>
</div>
  </div>
</template>

<script>
import api from "../backend-api";
export default {
name: "ProfileComponent",
  components: {},
  data(){
    return {
      comment: {
        rating:1,
        content:"",
        customerUsername:"",
        manifestationName:""
      },
      user: {
        username: "",
        firstName: "",
        lastName: "",
        gender: "",
        birthDate:"",
        collectedPoints:0
      },
      isCommentable:true,
      isCustomer: false,
      membership: "BRONZE",
      tickets:[],
      fields: [
        { key: 'manifestationName', label: 'Manifestation Name', sortable: true },
        { key: 'dateAndTime', label: 'Date', sortable: true },
        { key: 'price', label: 'Price', sortable: true },
        { key: 'ticketType', label: 'Type' },
        { key: 'status', label: 'Status', sortable: true },
        { key: 'comment', label: 'Comment'}
      ],
      sortBy: '',
      sortDesc: false,
      sortDirection: 'asc',
      filter: null,
      filterOn: [],
      commentModal: {
        id: 'comment-modal',
        title: 'Comment on this Manifestation',
        content: ''
      },
      complexTicket:{
        priceFrom:"",
        priceTo:"",
        dateFrom:"",
        dateTo:""
      }


    }
  },
  mounted() {
    this.getLoggedIn();
    this.getTicketsUser();
    this.complexTicket={
      priceFrom:"",
          priceTo:"",
          dateFrom:"2019-01-01",
          dateTo:"2022-01-01"}
  },
  methods: {
    getTicketsUser(){
      if(localStorage.getItem("role") === "customer"){
        api.getTicketsUser(localStorage.getItem("username")).then(response => {
          console.log(response);
          this.tickets = response.data;
        });
      }

    },
    getLoggedIn(){
      let role = localStorage.getItem("role");
      let username = localStorage.getItem("username");

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
    updateInfo(){
      api.updateInfo(this.user, localStorage.getItem("role")).then(response =>{
        if(response.data === "OK"){
          alert("Successfully updated info!")
        }else{
          alert("There was an error.")
        }
      })
    },
    openCommentModal(item, index, button) {
      this.comment.manifestationName = item.manifestationName;
      this.$root.$emit('bv::show::modal', this.commentModal.id, button)
    },
    logOut(){
      localStorage.clear();
      this.$router.push('/login')
    },
    homeRoute() {
      this.$router.push('/');
    },
    submitComment(){
      this.comment.customerUsername = localStorage.getItem('username');
      const comment = JSON.parse(JSON.stringify(this.comment));

      console.log(comment);
        api.submitComment(comment).then(response =>{
          if(response.data === "OK"){
            alert("Successfully submitted comment!");
            this.comment.content = "";


          }

        })
      },
      submitComplexSearch(){
        api.ticketComplexSearch(this.complexTicket).then(response =>{
          this.tickets = response.data;
        })
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
  }

}

</script>

<style scoped>
.complex-search {
  display: flex;
  flex-direction: row;
}
.my-profile-container {
  display: flex;
  width: 100%;
  padding-top: 50px;
}
.my-profile-info-container {
  display: flex;
  width: 500px;
  justify-content: center;
}
.my-profile-info-labels {
  width: 150px;
}
.change-info-btn {
  margin-top: 10px
}

.complex-search-date > .form-control{
  height: 40px;
}
.filter-search {
  height: 40px;
}

.custom-select {
  height: 40px;
  border-radius: 5px;
  border: 1px solid #ced4da;

}
.nav-button {
  margin-right: 5px;
  border-radius: 7px;

}
.complex-search {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  margin-bottom: 15px;

}

.complex-search-input {
  height: 40px;

}

.table-search-sort{
  width: 100%;
}
</style>
