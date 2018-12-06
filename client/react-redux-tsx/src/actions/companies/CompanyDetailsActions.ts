import {GET_COMPANY, GET_EMPLOYEES_COMPANY} from "./types";
import {httpClient} from "../index";

const companyUrl = '/companies';

export const fetchCompany = (cid: number) => (dispatch: any) => {
    httpClient.get(`${companyUrl}/${cid}`)
        .then((response: any) =>
            dispatch({
                type: GET_COMPANY,
                payload: response.data
            })).catch((error: any) => {
        console.log(error)
    })
};

export const fetchEmployeesOfCompany = (cid: number) => (dispatch: any) => {
    httpClient.get(`${companyUrl}/${cid}/employees`)
        .then((response: any) =>
            dispatch({
                type: GET_EMPLOYEES_COMPANY,
                payload: response.data.content
            })).catch((error: any) => {
        console.log(error)
    })
};

