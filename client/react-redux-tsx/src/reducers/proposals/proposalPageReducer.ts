import {GET_SINGLE_PROPOSAL} from "../../actions/proposals/types";

const initialState = {
    proposal: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_SINGLE_PROPOSAL:
            return {
                ...state,
                proposal: action.payload
            };
        default:
            return state;
    }
}