import {
    CHANGE_PROPOSAL_APPROVER,
    CHANGE_PROPOSAL_DESCRIPTION,
    CHANGE_PROPOSAL_PARTNERCOMPANY,
    CHANGE_PROPOSAL_TITLE
} from "./types";

export const changeTitleForm= (text:string) => (dispatch:any) => {
    dispatch({
        type:CHANGE_PROPOSAL_TITLE,
        payload: text
    })
};
export const changeDescriptionForm= (text:string) => (dispatch:any) => {
    dispatch({
        type:CHANGE_PROPOSAL_DESCRIPTION,
        payload: text
    })
};
export const changePartnerCompanyForm= (text:string) => (dispatch:any) => {
    dispatch({
        type:CHANGE_PROPOSAL_PARTNERCOMPANY,
        payload: text
    })
};
export const changeApproverForm= (text:string) => (dispatch:any) => {
    dispatch({
        type:CHANGE_PROPOSAL_APPROVER,
        payload: text
    })
};