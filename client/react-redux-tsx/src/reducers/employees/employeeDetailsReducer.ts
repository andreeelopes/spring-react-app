import {GET_EMPLOYEE} from "../../actions/proposals/types";

const initialState = {
    employee: null,
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_EMPLOYEE:
            return {
                ...state,
                employee: action.payload
            };

        default:
            return state;
    }
}