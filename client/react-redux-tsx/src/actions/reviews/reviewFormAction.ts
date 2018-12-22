import Axios from "axios";
import {ADD_PROPOSAL_REVIEW} from "../proposals/types";
import {getUser} from "../getSessionUser";

export const submitReview = (userId: number, proposal: number, description: string) => (dispatch: any) => {
    const json = {author: {id: userId.toString()}, proposal: {id: proposal}, text: description, score: "EXCELENT"};  //TODO score tem de ser diferente de null talvez meter um score not rated
    return Axios.post("http://localhost:8080/proposals/" + proposal + "/reviews/", json,
        {withCredentials: true}).then(() => {
            json.author=getUser();
        dispatch({
            type: ADD_PROPOSAL_REVIEW,
            payload: json
        })
    })
};