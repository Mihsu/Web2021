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
    }
}
