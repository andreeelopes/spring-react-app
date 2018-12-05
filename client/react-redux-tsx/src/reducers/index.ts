import {combineReducers} from 'redux';
import proposalModalReducer from './proposalModalReducer';
import proposalFormReducer from "./proposalFormReducer";
import proposalListReducer from "./proposalListReducer";
import ProposalDetailsReducer from "./ProposalDetailsReducer";
import bidListReducer from "./bidListReducer";
import userReducer from "./userReducer";


export default combineReducers({
    proposalModal: proposalModalReducer,
    proposalForm: proposalFormReducer,
    proposalList: proposalListReducer,
    proposalDetails: ProposalDetailsReducer,
    proposalStaffList:proposalListReducer,
    bidList:bidListReducer,
    user: userReducer
})