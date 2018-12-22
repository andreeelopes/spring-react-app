import Axios from "axios";
import {ADD_PROPOSAL_COMMENT} from "../proposals/types";
import {getUser} from "../getSessionUser";

export const submitComment = (userId: number, proposal: string, text: string) => (dispatch: any) => {

    const json = {author: {id: userId.toString()}, proposal: {id: proposal}, comment: text};
    return Axios.post("http://localhost:8080/proposals/" + proposal + "/comments/", json,
        {withCredentials: true}).then(() => {
            const user= getUser();
            json.author=user;
        dispatch({
            type: ADD_PROPOSAL_COMMENT,
            payload: json
        })
    })
};