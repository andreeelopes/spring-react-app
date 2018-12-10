import { SHOW_ADD_BID_BUTTON} from "../proposals/types";

export const showBidButton=(status:boolean)=> (dispatch:any) =>{

        dispatch({
            type: SHOW_ADD_BID_BUTTON,
            payload: status
        })

};

