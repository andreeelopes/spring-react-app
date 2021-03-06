import {LOGIN} from "./proposals/types";
import axios from "axios";
export const doLogin = (pass:string,user:string,history:any) => (dispatch: any) => {
    axios.get('http://localhost:8080/employees?exist='+user, {
        auth: {
            password:pass, username:user
        },withCredentials:true
    }).then((json) => {
        console.log(json)
        sessionStorage.setItem('myData', JSON.stringify(json.data.content[0]));
        dispatch({
            type: LOGIN
        })
        history.push('/homepage');
    });

};