
import axios from "axios";
import {ISection} from "../../models/IComponents";
import {ADD_TITLE, CLEAR_BIDS, GET_BIDS_SECTIONS} from "../proposals/types";
import {getUser} from "../getSessionUser";


export const getBidsAndSections = (currPage:number) => (dispatch: any,) => {
    const user = getUser();
    return axios('http://localhost:8080/employees/'+user.id+'/bids?page=' + currPage, {
        withCredentials: true,
        method: 'get'
    }).then((json: any) => {
        const total = json.data.totalElements;
        const bidList:any = [];
        json.data.content.map((item:any)=> {
            bidList.push({bid:item});
        });
        dispatch({
            type: GET_BIDS_SECTIONS,
            total: {total},
            payload: bidList
        });
        bidList.map((c:any, i:number) => {

            getSections(c, json, i,currPage,dispatch)}

        );

    });

};

export const getSections = (c: any, json: any, i: number,page:number,dispatch:any) => {
    return axios('http://localhost:8080/proposals/' + c.bid.pk.proposal.id + '/sections/', {
        withCredentials: true,
        method: 'get'
    }).then((sectionjson: any) => {
        let sectionList: ISection[];
        sectionList = sectionjson.data.content;
        sectionList.map((s) => {
            if (s.type === "title") {
                const propLine:any = {section: s, bid: c.bid};
                dispatch({
                    type: ADD_TITLE,
                    index:i+(page*20),
                    payload: propLine
                });

            }
        });
    });

};

export const clearList = () => (dispatch: any,) => {
    dispatch({
        type: CLEAR_BIDS
    })
};