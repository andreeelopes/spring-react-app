import {SHOW_MODAL} from "./types";

export const showModal = () => (dispatch: any) => {
    dispatch({
        type: SHOW_MODAL,
        payload: true
    })

};

export const hideModal = () => (dispatch: any) => {

    dispatch({
        type: SHOW_MODAL,
        payload: false
    })

};

