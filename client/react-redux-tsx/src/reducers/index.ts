import {combineReducers} from 'redux';
import proposalModalReducer from './proposalModalReducer';
import proposalFormReducer from "./proposalFormReducer";
import proposalListReducer from "./proposalListReducer";
import ProposalDetailsReducer from "./ProposalDetailsReducer";
import bidListReducer from "./bidListReducer";
import loginReducer from "./loginReducer";
import companyDetailsReducer from "./companies/companyDetailsReducer";
import reviewListReducer from "./reviews/reviewListReducer";
import reviewModalReducer from "./reviews/reviewModalReducer";


export default combineReducers({
    proposalModal: proposalModalReducer,
    proposalForm: proposalFormReducer,
    proposalList: proposalListReducer,
    proposalDetails: ProposalDetailsReducer,
    login: loginReducer,
    bidList: bidListReducer,
    companyDetails: companyDetailsReducer,
    reviewList:reviewListReducer,
    reviewModal: reviewModalReducer
})