import {CHANGE_USER} from "../actions/employees/types";


const initialState = {
    user: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {

        case CHANGE_USER:
            return {
                ...state,
                user: action.payload
            };

        default:
            return state;
    }
}