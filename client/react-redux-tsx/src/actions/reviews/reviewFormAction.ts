import Axios from "axios";
import {ADD_PROPOSAL_REVIEW} from "../proposals/types";

export const submitReview = (userId: number, proposal: number, description: string) => (dispatch: any) => {
    const json = {author: {id: userId.toString()}, proposal: {id: proposal}, text: description, score: "EXCELENT"};
    return Axios.post("http://localhost:8080/proposals/" + proposal + "/reviews/", json,
        {withCredentials: true}).then(() => {
        dispatch({
            type: ADD_PROPOSAL_REVIEW,
            payload: json
        })
    })
};