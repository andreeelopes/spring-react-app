
import axios from "axios";
import {IEmployee, IProposal, ISection} from "../../models/IComponents";
import {ADD_TITLE, GET_BIDS} from "../proposals/types";
import {getUser} from "../getSessionUser";
interface IBidPK {
    bidder: IEmployee;
    proposal: IProposal;
}

export interface IBid {
    pk: IBidPK
    status: string;
}

export const getBids = (currPage:number) => (dispatch: any,) => {
    const user = getUser();
    return axios('http://localhost:8080/employees/'+user.id+'/bids?page=' + currPage, {
        withCredentials: true,
        method: 'get'
    }).then((json: any) => {
        const totalPage:number = json.data.totalPages;
        const total = json.data.totalElements;
        if (totalPage === currPage) {
            return;
        }
        const bidList:any = [];
        json.data.content.map((item:any)=> {
            bidList.push({bid:item});
        });
        dispatch({
            type: GET_BIDS,
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
            if (s.type = "title") {
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
