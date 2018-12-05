import axios from 'axios';


export const httpClient = axios.create({
    baseURL: "http://localhost:8080",
    auth: {
        password: "password",
        username: "employee21"
    }
});
