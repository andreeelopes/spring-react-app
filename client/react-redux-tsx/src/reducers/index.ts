import {combineReducers} from 'redux';
import proposalModalReducer from './proposalModalReducer';
import proposalFormReducer from "./proposalFormReducer";
import proposalListReducer from "./proposalListReducer";
import ProposalDetailsReducer from "./ProposalDetailsReducer";
import bidListReducer from "./bidListReducer";
import loginReducer from "./loginReducer";
import companyDetailsReducer from "./companies/companyDetailsReducer";


export default combineReducers({
    proposalModal: proposalModalReducer,
    proposalForm: proposalFormReducer,
    proposalList: proposalListReducer,
    proposalDetails: ProposalDetailsReducer,
    login: loginReducer,
    bidList: bidListReducer,
    companyDetails: companyDetailsReducer
})