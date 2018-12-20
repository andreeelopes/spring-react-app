import {
    SHOW_BID_FORM,
    SHOW_COMMENT_FORM,
    SHOW_REVIEW_FORM,
    SHOW_SECTION_FORM,
    SHOW_TEAMMEMBER_FORM, TYPE_OF_MEMBER
} from "../../actions/proposals/types";

const initialState = {
    commentModal: false,
    bidModal:false,
    reviewModal: false,
    teamMemberModal: false,
    sectionModal: false,
    membertype:'Type of member'
};

export default function (state = initialState, action: any) {
    switch (action.type) {
        case SHOW_COMMENT_FORM:
            return {
                ...state,
                commentModal: action.payload
            };
        case SHOW_BID_FORM:
            return{
                ...state,
                bidModal: action.payload
            };
        case SHOW_REVIEW_FORM:
            return{
                ...state,
                reviewModal: action.payload
            };
        case SHOW_TEAMMEMBER_FORM:
            return{
                ...state,
                teamMemberModal: action.payload
            };
        case SHOW_SECTION_FORM:
            return{
                ...state,
                sectionModal: action.payload
            };
        case TYPE_OF_MEMBER:
            return{
                ...state,
                membertype: action.payload
            }
        default:
            return state;
    }
}