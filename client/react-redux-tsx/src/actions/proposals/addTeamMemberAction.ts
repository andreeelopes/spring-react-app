import Axios from "axios";
import {ADD_PARTNER_MEMBER, ADD_STAFF_MEMBER, TYPE_OF_MEMBER} from "./types";

export const submitTeamMember = (userId:number,proposal:number,description:string) => (dispatch:any) =>{
    const jsonToSend= {username:description};
    return Axios.post("http://localhost:8080/proposals/"+proposal+"/partnermembers/",jsonToSend,
        { withCredentials: true}).then((json) => {
            console.log(json.data);
        dispatch({
            type: ADD_PARTNER_MEMBER,
            payload: json.data
        })
    })

};
export const submitStaffMember = (userId:number,proposal:number,description:string) => (dispatch:any) =>{
    const jsonToSend= {username:description};
    return Axios.post("http://localhost:8080/proposals/"+proposal+"/staff/",jsonToSend,
        { withCredentials: true}).then((json) => {
        dispatch({
            type: ADD_STAFF_MEMBER,
            payload: json.data
        })
    })

};
export const changeTitle =(title:string)=>(dispatch:any)=>{
    dispatch({
        type: TYPE_OF_MEMBER,
        payload: title
    })
}