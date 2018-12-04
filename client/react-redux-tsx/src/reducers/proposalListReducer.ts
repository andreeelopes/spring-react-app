import {ADD_SECTION, GET_PROPOSALS} from "../actions/proposals/types";

const initialState = {
    displayMyProposals: [],
    totalSize: 0,
    sectionsAdded:0
};
export default function (state = initialState, action: any) {
    let tempState:any= [];
    switch (action.type) {

        case GET_PROPOSALS:
            tempState=state.displayMyProposals.concat(action.payload);
            return {
                ...state,
                displayMyProposals: tempState,
                totalSize:action.total
            };
        case ADD_SECTION:
            tempState=state.displayMyProposals;
            tempState[action.index]=action.payload;
            return{
                ...state,
                displayMyProposals:tempState,
                sectionsAdded: state.sectionsAdded+1
            }
        default:
            return state;
    }
}