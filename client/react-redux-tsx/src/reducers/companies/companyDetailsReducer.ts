import {GET_COMPANY} from "../../actions/companies/types";

const initialState = {
    company: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_COMPANY:
            return {
                ...state,
                company: action.payload
            };
        default:
            return state;
    }
}