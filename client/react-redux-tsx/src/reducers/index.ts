import {combineReducers} from 'redux';
import proposalModalReducer from './proposals/proposalModalReducer';
import proposalFormReducer from "./proposals/proposalFormReducer";
import proposalListReducer from "./proposals/proposalListReducer";
import ProposalDetailsReducer from "./proposals/ProposalDetailsReducer";
import bidListReducer from "./bids/bidListReducer";
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
    reviewList: reviewListReducer,
    reviewModal: reviewModalReducer
})