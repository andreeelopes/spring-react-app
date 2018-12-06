import {SHOW_REVIEW_MODAL} from "../../actions/reviews/types";

const initialState = {
    state: false,
    review: null
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case SHOW_REVIEW_MODAL:
            return {
                ...state,
                state: action.state,
                review: action.payload
            };
        case SHOW_REVIEW_MODAL:
            return {
                ...state,
                state: action.state
            };
        default:
            return state;
    }
}