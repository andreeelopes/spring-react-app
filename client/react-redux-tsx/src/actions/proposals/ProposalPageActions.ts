import axios from "axios";
import {GET_SINGLE_PROPOSAL} from "./types";

export const fetchProposal=(id:number)=> (dispatch:any) =>{

    return axios('http://localhost:8080/proposals/'+id, {
        withCredentials: true,
        method: 'get'
    }).then((json)=>{
        dispatch({
            type: GET_SINGLE_PROPOSAL,
            payload: json.data
        })
    })

};