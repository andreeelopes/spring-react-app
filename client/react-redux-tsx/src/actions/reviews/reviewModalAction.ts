import {SHOW_REVIEW_MODAL} from "./types";

export const showModal = (review:any) => (dispatch: any) => {
    dispatch({
        type: SHOW_REVIEW_MODAL,
        state: true,
        payload:review
    })

};

export const hideModal = () => (dispatch: any) => {

    dispatch({
        type: SHOW_REVIEW_MODAL,
        payload: false
    })

};

