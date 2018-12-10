import { SHOW_ADD_BID_BUTTON} from "../proposals/types";
import axios from "axios";

export const showBidButton=(status:boolean)=> (dispatch:any) =>{

        dispatch({
            type: SHOW_ADD_BID_BUTTON,
            payload: status
        })

};

export const fetchBids=(id:number)=>{

    return axios('http://localhost:8080/employees/'+id+'/bids', {
        withCredentials: true,
        method: 'get'
    });
};
