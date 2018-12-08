import {GET_EMPLOYEE} from "../proposals/types";
import {httpClient} from "../index";

const personURL = '/employees/{pid}/';

export const fetch = (url: string, reduxAction: string,) => {
    return (dispatch: any) => {
        return httpClient.get(url)
            .then((response: any) => {
                console.log(response);
                dispatch({
                    type: reduxAction,
                    payload: response.data
                })
            }).catch((error: any) => {
                console.log(error)
            })
    }
};
export const fetchEmployee = (id: number) => {
    console.log("fetching employees");
    return fetch(personURL.replace("{pid}", id.toString()), GET_EMPLOYEE);
};