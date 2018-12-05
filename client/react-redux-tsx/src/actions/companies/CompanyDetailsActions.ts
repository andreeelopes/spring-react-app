import {GET_COMPANY} from "./types";
import {httpClient} from "../index";

const companyUrl = '/companies/{cid}';

export const fetchCompany = (id: number) => (dispatch: any) => {
    httpClient.get(companyUrl.replace("{cid}", id.toString()))
        .then((response: any) =>
            dispatch({
            type: GET_COMPANY,
            payload: response.data
        })).catch((error: any) => {
        console.log(error)
    })
};

