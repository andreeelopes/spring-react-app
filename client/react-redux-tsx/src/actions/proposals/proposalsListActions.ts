
import axios from "axios";
import {IProposal, ISection} from "../../models/IComponents";
import {ADD_SECTION, CLEAR, GET_PROPOSALS} from "./types";

export const getProposals = (currPage:number,link:string) => (dispatch: any,) => {
    console.log(link);
    return axios(link + currPage, {
        withCredentials: true,
        method: 'get'
    }).then((json: any) => {
        const total = json.data.totalElements;
        const proposalList:any = [];
        json.data.content.map((item:any)=> {
            proposalList.push({proposal:item});
        });
        dispatch({
            type: GET_PROPOSALS,
            total: {total},
            payload: proposalList
        });

        proposalList.map((c:any, i:number) => {

            getSections(c.proposal, json, i,currPage,dispatch)}

        );

    });

};
export const clearList= () => (dispatch:any,) =>{
    dispatch({
        type: CLEAR
    })
};


export const getSections = (c: IProposal, json: any, i: number,page:number,dispatch:any) => {
    return axios('http://localhost:8080/proposals/' + c.id + '/sections/', {
        withCredentials: true,
        method: 'get'
    }).then((sectionjson: any) => {
        let sectionList: ISection[];
        sectionList = sectionjson.data.content;
        sectionList.map((s) => {
            if (s.type = "title") {
                const propLine:any = {section: s, proposal: c};
                dispatch({
                    type: ADD_SECTION,
                    index:i+(page*20),
                    payload: propLine
                });

            }
        });
    });

};
