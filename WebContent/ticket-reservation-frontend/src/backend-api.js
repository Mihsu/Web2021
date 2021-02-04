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
    }


}
