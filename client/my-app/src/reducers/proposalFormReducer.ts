import {
    CHANGE_PROPOSAL_APPROVER,
    CHANGE_PROPOSAL_DESCRIPTION,
    CHANGE_PROPOSAL_PARTNERCOMPANY,
    CHANGE_PROPOSAL_TITLE
} from "../actions/types";


const initialState = {
    title: '',
    description: '',
    partnerCompany: '',
    approver:''
};

export default function(state= initialState,action:any){
    switch (action.type) {
        case CHANGE_PROPOSAL_TITLE:
            return{
                ...state,
                title:action.payload
            };
        case CHANGE_PROPOSAL_DESCRIPTION:
            return{
                ...state,
                description:action.payload
            };
        case CHANGE_PROPOSAL_PARTNERCOMPANY:
            return{
                ...state,
                partnerCompany:action.payload
            };
        case CHANGE_PROPOSAL_APPROVER:
            return{
                ...state,
                approver:action.payload
            };
        default: return state;
    }
}