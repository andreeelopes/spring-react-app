import {LOGIN} from "../actions/proposals/types";

const initialState = {
    login: false
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case LOGIN:
            return {
                ...state,
                login: true
            };
        default:
            return state;
    }
}