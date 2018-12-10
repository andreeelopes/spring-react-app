import axios from "axios";
import {ADD_REVIEW_TITLE, GET_REVIEWS} from "./types";
import {ISection} from "../../models/IComponents";
import {getUser} from "../getSessionUser";
import {CLEAR_REVIEWS} from "../proposals/types";


export const getReviews = (currPage: number) => (dispatch: any,) => {
    const user = getUser();
    return axios('http://localhost:8080/employees/' + user.id + '/reviews?page=' + currPage, {
        withCredentials: true,
        method: 'get'
    }).then((json: any) => {
        const total = json.data.totalElements;
        const bidList: any = [];
        json.data.content.map((item: any) => {
            bidList.push({review: item});
        });
        dispatch({
            type: GET_REVIEWS,
            total: {total},
            payload: bidList
        });
        bidList.map((c: any, i: number) => {

                getSections(c, json, i, currPage, dispatch)
            }
        );

    });

};

export const getSections = (c: any, json: any, i: number, page: number, dispatch: any) => {
    return axios('http://localhost:8080/proposals/' + c.review.proposal.id + '/sections/', {
        withCredentials: true,
        method: 'get'
    }).then((sectionjson: any) => {
        let sectionList: ISection[];
        sectionList = sectionjson.data.content;
        sectionList.map((s) => {
            if (s.type === "title") {
                const propLine: any = {section: s, review: c.review};
                dispatch({
                    type: ADD_REVIEW_TITLE,
                    index: i + (page * 20),
                    payload: propLine
                });

            }
        });
    });

};
export const clearList = () => (dispatch: any,) => {
    dispatch({
        type: CLEAR_REVIEWS
    })
};