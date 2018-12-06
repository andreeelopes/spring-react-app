import {GET_COMPANY, GET_EMPLOYEES_COMPANY} from "../../actions/companies/types";

const initialState = {
    company: null,
    employees: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_COMPANY:
            return {
                ...state,
                company: action.payload
            };
        case GET_EMPLOYEES_COMPANY:
            return {
                ...state,
                employees: action.payload
            };
        default:
            return state;
    }
}
