import {GET_PARTNERS} from "./types";
// import {httpClient} from "../index";

// const partnerUrl = '/proposals/{pid}/partnermembers/'

export const fetchPartners = (id: number) => (dispatch: any) => {
    dispatch({
        type: GET_PARTNERS,
        payload: id
    })
};

/*
* export const changeDescriptionForm = (text: string) => (dispatch: any) => {
    dispatch({
        type: CHANGE_PROPOSAL_DESCRIPTION,
        payload: text
    })



    httpClient.get(partnerUrl.replace("{pid}", String(id)))

};*/
