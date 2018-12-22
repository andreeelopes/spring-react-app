import Axios from "axios";

export const addBid = (user: number, proposal: number) => {
    console.log({pk: {bidder: {id: user}, proposal: {id: proposal}}});
    return Axios.post("http://localhost:8080/proposals/" + proposal + "/bids", {
            pk: {
                bidder: {id: user},
                proposal: {id: proposal}
            }
        },
        {withCredentials: true});
};