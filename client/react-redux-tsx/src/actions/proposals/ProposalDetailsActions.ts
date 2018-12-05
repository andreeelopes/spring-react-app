import {GET_PARTNERS} from "./types";
import {httpClient} from "../index";

const partnerUrl = '/proposals/{pid}/partnermembers/'

export const fetchPartners = (id: any) => {
   return (dispatch:any, getState:any) => {
       return  httpClient.get(partnerUrl.replace("{pid}", id.toString()))
           .then( (response : any) =>{
                dispatch({
                    type: GET_PARTNERS,
                    payload: response.data
                })
           }).catch( (error : any) => {
               console.log(error)
           })
    }
};

/*return function(dispatch, getState) {
        if (fullURL !== '') {
            return axios.get(fullURL).then(function (response) {
                dispatch(addressDataSuccess(response))

            }).catch(function (error) {
                  console.log(error)
            })
        }
    }

function fetchData(){
    return(dispatch,getState) =>{ //using redux-thunk here... do check it out
        const url = '//you url'
        fetch(url)
        .then (response ) => {dispatch(receiveData(response.data)} //data being your api response object/array
        .catch( error) => {//throw error}
    }

     console.log("url = "  +partnerUrl.replace("{pid}", id.toString()) + "\nid = " + id);
    dispatch({
        type: GET_PARTNERS,
        payload: httpClient.get(partnerUrl.replace("{pid}", id.toString()))
    })

     axios.get('//url')
  .then(function (response) {
    //dispatch action
  })
  .catch(function (error) {
    // throw error
  });







  }*/