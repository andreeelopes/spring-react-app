import {LOGIN} from "./proposals/types";
import axios from "axios";

export const doLogin = () => (dispatch: any) => {

    axios.get('http://localhost:8080/', {
        auth: {
            password: "password", username: "employee21"
        }, withCredentials: true
    }).then(null, () => {
        dispatch({
            type: LOGIN
        })
    });

};