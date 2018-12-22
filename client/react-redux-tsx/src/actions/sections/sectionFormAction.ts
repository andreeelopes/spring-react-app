import Axios from "axios";
import {ADD_PROPOSAL_SECTION} from "../proposals/types";

export const submitSection = (proposalId: number, typeOfSection: string, content: string) => (dispatch: any) => {
    const json = {proposal: {id: proposalId}, type: typeOfSection, text: content};
    return Axios.post("http://localhost:8080/proposals/" + proposalId + "/sections/", json,
        {withCredentials: true}).then(() => {
        dispatch({
            type: ADD_PROPOSAL_SECTION,
            payload: json
        })
    })
};