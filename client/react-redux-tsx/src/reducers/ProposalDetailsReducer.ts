import {GET_PARTNERS} from "../actions/proposals/types";

const initialState = {
    partners: ''
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_PARTNERS:
            console.log(action.payload)
            return {
                ...state,
                partners: action.payload
            };
        default:
            return state;
    }
}