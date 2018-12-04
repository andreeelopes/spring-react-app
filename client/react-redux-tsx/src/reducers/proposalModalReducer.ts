import {HIDE_MODAL, SHOW_MODAL} from "../actions/proposals/types";

const initialState = {
    state: false
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case SHOW_MODAL:
            return {
                ...state,
                state: action.payload
            };
        case HIDE_MODAL:
            return {
                ...state,
                state: action.payload
            };
        default:
            return state;
    }
}