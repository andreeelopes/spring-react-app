import axios from "axios";
import {GET_BIDS, GET_SINGLE_PROPOSAL} from "./types";

export const fetchProposal = (id: number) => (dispatch: any) => {

    return axios('http://localhost:8080/proposals/' + id, {
        withCredentials: true,
        method: 'get'
    }).then((json) => {
        dispatch({
            type: GET_SINGLE_PROPOSAL,
            payload: json.data
        })
    })

};
export const fetchBids = (id: number) => (dispatch: any) => {

    return axios('http://localhost:8080/employees/' + id + '/bids', {
        withCredentials: true,
        method: 'get'
    }).then((json) => {
        dispatch({
            type: GET_BIDS,
            payload: json.data.content
        })
    })
};
