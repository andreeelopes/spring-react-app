import {LOGIN} from "./proposals/types";

export const doLogin = () => (dispatch: any) => {
    dispatch({
        type: LOGIN
    })
};