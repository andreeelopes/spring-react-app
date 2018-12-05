import {combineReducers} from 'redux';
import proposalModalReducer from './proposalModalReducer';
import proposalFormReducer from "./proposalFormReducer";
import proposalListReducer from "./proposalListReducer";
import ProposalDetailsReducer from "./ProposalDetailsReducer";

export default combineReducers({
    proposalModal: proposalModalReducer,
    proposalForm: proposalFormReducer,
    proposalList: proposalListReducer,
    proposalDetails: ProposalDetailsReducer
})