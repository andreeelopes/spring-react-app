import {GET_PARTNERS} from "./types";
import {httpClient} from "../index";

const partnerUrl = '/proposals/{pid}/partnermembers/'

export const fetchPartners = (id: any) => {
    return (dispatch: any, getState: any) => {
        return httpClient.get(partnerUrl.replace("{pid}", id.toString()))
            .then((response: any) => {
                dispatch({
                    type: GET_PARTNERS,
                    payload: response.data
                })
            }).catch((error: any) => {
                console.log(error)
            })
    }
};