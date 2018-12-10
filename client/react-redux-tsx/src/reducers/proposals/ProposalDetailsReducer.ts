import {
    GET_PROPOSAL_PARTNERS,
    GET_PROPOSAL_STAFF,
    GET_PROPOSAL_SECTIONS,
    GET_PROPOSAL_COMMENTS,
    GET_PROPOSAL_BIDS,
    GET_PROPOSAL_REVIEWS, ADD_PROPOSAL_COMMENT
} from "../../actions/proposals/types";

const initialState = {
    partners: [],
    staff: [],
    sections: [],
    comments: [],
    bids: [],
    reviews: []
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case GET_PROPOSAL_PARTNERS:
            return {
                ...state,
                partners: action.payload
            };
        case GET_PROPOSAL_STAFF:
            return {
                ...state,
                staff: action.payload
            };
        case GET_PROPOSAL_SECTIONS:
            return {
                ...state,
                sections: action.payload
            };
        case GET_PROPOSAL_COMMENTS:
            return {
                ...state,
                comments: action.payload
            };
        case GET_PROPOSAL_BIDS:
            return {
                ...state,
                bids: action.payload
            };
        case GET_PROPOSAL_REVIEWS:
            return {
                ...state,
                reviews: action.payload
            };
        case ADD_PROPOSAL_COMMENT:
            const array= state.comments;
            return{
                ...state,
                comments:  [...array, action.payload]
            }

        default:
            return state;
    }
}

