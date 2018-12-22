import {CHANGE_PROPOSAL_STATUS, GET_BIDS, GET_SINGLE_PROPOSAL} from "../../actions/proposals/types";
import {IBid, IProposal} from "../../models/IComponents";


interface IState {
    proposal: IProposal | null,
    bids: IBid[] | null
}

const initialState: IState = {
    proposal: null,
    bids: null
};

export default function (state: IState = initialState, action: any) {
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
        case CHANGE_PROPOSAL_STATUS:
            return {
                ...state,
                proposal: {
                    ...state.proposal,
                    status: action.payload
                }
            };
        default:
            return state;
    }
}