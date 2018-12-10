import {SHOW_ADD_BID_BUTTON} from "../../actions/proposals/types";

const initialState = {
    showButton: true
};
export default function (state = initialState, action: any) {
    switch (action.type) {

        case SHOW_ADD_BID_BUTTON:
            return {
                ...state,
                showButton: action.payload
            };

        default:
            return state;
    }
}