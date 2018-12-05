import {combineReducers} from 'redux';
import proposalModalReducer from './proposalModalReducer';
import proposalFormReducer from "./proposalFormReducer";
import proposalListReducer from "./proposalListReducer";
import ProposalDetailsReducer from "./ProposalDetailsReducer";
import bidListReducer from "./bidListReducer";
import userReducer from "./userReducer";
import loginReducer from "./loginReducer";


export default combineReducers({
    proposalModal: proposalModalReducer,
    proposalForm: proposalFormReducer,
    proposalList: proposalListReducer,
    proposalDetails: ProposalDetailsReducer,
    login:loginReducer,
    bidList:bidListReducer,
    user: userReducer
})