import {SHOW_COMMENT_FORM,SHOW_BID_FORM,SHOW_REVIEW_FORM,SHOW_TEAMMEMBER_FORM,SHOW_SECTION_FORM} from "./types";


const modalStatus = ( reduxAction: string,status:boolean) => {
    return (dispatch: any) => {
        return dispatch({
            type: reduxAction,
            payload: status
        })
    }
};

export const showCommentModal= (status:boolean) =>{
    return modalStatus(SHOW_COMMENT_FORM,status);
};
export const showBidModal= (status:boolean) =>{
    return modalStatus(SHOW_BID_FORM,status);
};
export const showReviewModal= (status:boolean) =>{
    return modalStatus(SHOW_REVIEW_FORM,status);
};
export const showTeamMemberModal= (status:boolean) =>{
    return modalStatus(SHOW_TEAMMEMBER_FORM,status);
};
export const showSectionModal= (status:boolean) =>{
    return modalStatus(SHOW_SECTION_FORM,status);
};