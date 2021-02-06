import axios from 'axios'

const AXIOS = axios.create({
    baseURL: `http://localhost:8083/Web2021/rest`,
    timeout: 2000
});

export default {
    login(userRequest) {
        return AXIOS.post('/auth/login', userRequest);
    },
    register(newUserRequest){
        return AXIOS.post('/auth/register', newUserRequest);
    },
    getLoggedIn(loggedInUser, role){
        return AXIOS.get('/auth/loggedIn/'+ loggedInUser + '/' + role);
    },
    updateInfo(changedUser, role) {
        return AXIOS.post('/user/update/' + role, changedUser)
    },
    loadManifestations() {
        return AXIOS.get('/manifestations/all')
    },
    createManifestation(manifestation) {
        return AXIOS.post('/manifestations/add', manifestation)
    },
    getReservedTickets(){
        return AXIOS.get('/tickets/reserved')
    },
    getAllTickets(){
        return AXIOS.get('/tickets/all')
    },
    updateManifestation(manifestation){
        return AXIOS.post('/manifestations/update', manifestation)
    },
    getTicketsUser(username){
        return AXIOS.get('/tickets/all/' + username)
    },
    submitComment(comment) {
        return AXIOS.post('/comment', comment)
    },
    getAllComments(manifestationName){
        return AXIOS.get('/comment/getall/' + manifestationName)
    },
    getApprovedComments(manifestationName){
        return  AXIOS.get('/comment/get/' + manifestationName)
    },
    approveComment(commentId){
        return AXIOS.post('/comment/approve/'+commentId)
    },
    getAllUsers() {
        return AXIOS.get('/user/allusers')
    }, loadManifestationsActive() {
        return AXIOS.get('/manifestations/allactive')

    },approveManifestation(manifestationName){
        return AXIOS.post('/manifestations/approve/' + manifestationName);
    },
    addSeller(newUser){
        return AXIOS.post('/user/addseller', newUser)
    },
    manifestationComplexSearch(complex){
        return AXIOS.get('/manifestations/complex/'+complex.name + '/'+complex.city+'/'+complex.priceFrom+'/' +complex.priceTo
            + '/' + complex.dateFrom + '/' + complex.dateTo)
    },
    ticketComplexSearch(complexTicket){
        return AXIOS.get('/tickets/complex/'+complexTicket.priceFrom+'/' +complexTicket.priceTo
            + '/' + complexTicket.dateFrom + '/' + complexTicket.dateTo)
    }

}
