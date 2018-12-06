import {ADD_REVIEW_TITLE, GET_REVIEWS} from "../../actions/reviews/types";


const initialState = {
    displayedMyReviews: [],
    totalSize: 0,
    sectionsAdded:0
};
export default function (state = initialState, action: any) {
    let tempState:any= [];
    switch (action.type) {

        case GET_REVIEWS:
            tempState=state.displayedMyReviews.concat(action.payload);

            return {
                ...state,
                displayedMyReviews: tempState,
                totalSize:action.total
            };
        case ADD_REVIEW_TITLE:
            tempState=state.displayedMyReviews;
            tempState[action.index]=action.payload;
            console.log(action.payload);
            return{
                ...state,
                displayedMyReviews:tempState,
                sectionsAdded: state.sectionsAdded+1
            }
        default:
            return state;
    }
}