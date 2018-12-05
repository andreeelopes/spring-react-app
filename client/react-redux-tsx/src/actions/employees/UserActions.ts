import {
    CHANGE_USER
} from "./types";
import {IUser} from "../../models/IComponents";


export const changeUser = (user: IUser) => (dispatch: any) => {
    dispatch({
        type: CHANGE_USER,
        payload: user
    })
};