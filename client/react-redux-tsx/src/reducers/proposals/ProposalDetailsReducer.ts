import {
    GET_PROPOSAL_PARTNERS,
    GET_PROPOSAL_STAFF,
    GET_PROPOSAL_SECTIONS,
    GET_PROPOSAL_COMMENTS,
    GET_PROPOSAL_BIDS,
    GET_PROPOSAL_REVIEWS,
    ADD_PROPOSAL_COMMENT,
    ADD_PROPOSAL_REVIEW,
    ADD_PARTNER_MEMBER,
    ADD_STAFF_MEMBER,
    ADD_PROPOSAL_SECTION
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
            const comments= state.comments;
            return{
                ...state,
                comments:  [...comments, action.payload]
            };
        case ADD_PROPOSAL_REVIEW:
            const reviews= state.reviews;
            return{
                ...state,
                reviews:[...reviews,action.payload]
            };
        case ADD_PARTNER_MEMBER:
            const partners= state.partners;
            return{
                ...state,
                partners:[...partners,action.payload]
            };
        case ADD_STAFF_MEMBER:
            const staff=state.staff;
            return{
                ...state,
                staff:[...staff,action.payload]
            }
        case ADD_PROPOSAL_SECTION:
            return{
                ...state,
                sections:[...state.sections,action.payload]
            }
        default:
            return state;
    }
}

