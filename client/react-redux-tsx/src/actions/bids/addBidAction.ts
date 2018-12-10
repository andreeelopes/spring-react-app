import Axios from "axios";

export const addBid = (user:number,proposal:number) =>{

    return Axios.post("http://localhost:8080/proposals/"+proposal+"/bids",{pk:{bidder:{id:user.toString()},proposal:{id:proposal}}},
        { withCredentials: true});
};