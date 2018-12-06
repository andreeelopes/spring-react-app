import {
    GET_PROPOSAL_PARTNERS,
    GET_PROPOSAL_STAFF,
    GET_PROPOSAL_SECTIONS,
    GET_PROPOSAL_COMMENTS,
    GET_PROPOSAL_BIDS,
    GET_PROPOSAL_REVIEWS
} from "./types";
import {httpClient} from "../index";

const partnerURL = '/proposals/{pid}/partnermembers';
const staffURL = '/proposals/{pid}/staff';
const sectionsURL = '/proposals/{pid}/sections';
const commentsURL = '/proposals/{pid}/comments';
const bidsURL = '/proposals/{pid}/bids';
const reviewsURL = '/proposals/{pid}/reviews';

export const fetch = (url: string, reduxAction: string,) => {
    return (dispatch: any, getState: any) => {
        return httpClient.get(url)
            .then((response: any) => {
                dispatch({
                    type: reduxAction,
                    payload: response.data.content // TODO retirar o content para permitir o pageable   -nelson
                })
            }).catch((error: any) => {
                console.log(error)
            })
    }
};

export const fetchPartners = (id: any) => {
    return fetch(partnerURL.replace("{pid}", id.toString), GET_PROPOSAL_PARTNERS)
};

export const fetchStaff = (id: any) => {
    return fetch(staffURL.replace("{pid}", id.toString), GET_PROPOSAL_STAFF)
};

export const fetchSections = (id: any) => {
    return fetch(sectionsURL.replace("{pid}", id.toString), GET_PROPOSAL_SECTIONS)
};

export const fetchComments = (id: any) => {
    return fetch(commentsURL.replace("{pid}", id.toString), GET_PROPOSAL_COMMENTS)
};

export const fetchBids = (id: any) => {
    return fetch(bidsURL.replace("{pid}", id.toString), GET_PROPOSAL_BIDS)
};

export const fetchReviews = (id: any) => {
    return fetch(reviewsURL.replace("{pid}", id.toString), GET_PROPOSAL_REVIEWS)
};