import {combineReducers} from 'redux';
import proposalModalReducer from './proposalModalReducer';
import proposalFormReducer from "./proposalFormReducer";

export default combineReducers({
    proposalModal: proposalModalReducer,
    proposalForm: proposalFormReducer
})