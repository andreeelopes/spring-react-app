import {GET_BIDS, GET_SINGLE_PROPOSAL} from "../../actions/proposals/types";

const initialState = {
    proposal: null,
    bids: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_SINGLE_PROPOSAL:
            return {
                ...state,
                proposal: action.payload
            };
        case GET_BIDS:
            return {
                ...state,
                bids: action.payload
            };
        default:
            return state;
    }
}