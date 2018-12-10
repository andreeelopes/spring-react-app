import {GET_BIDS_SECTIONS, ADD_TITLE, CLEAR_BIDS} from "../../actions/proposals/types";

const initialState = {
    displayedMyBids: [],
    totalSize: 0,
    sectionsAdded: 0
};
export default function (state = initialState, action: any) {
    let tempState: any = [];
    switch (action.type) {

        case GET_BIDS_SECTIONS:
            tempState = state.displayedMyBids.concat(action.payload);
            return {
                ...state,
                displayedMyBids: tempState,
                totalSize: action.total
            };
        case ADD_TITLE:
            tempState = state.displayedMyBids;
            tempState[action.index] = action.payload;
            return {
                ...state,
                displayedMyBids: tempState,
                sectionsAdded: state.sectionsAdded + 1
            };
        case CLEAR_BIDS:
            return {
                ...state,
                displayedMyBids: [],
                totalSize: 0,
                sectionsAdded: 0

            };
        default:
            return state;
    }
}