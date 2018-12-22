import {
    GET_PROPOSAL_PARTNERS,
    GET_PROPOSAL_STAFF,
    GET_PROPOSAL_SECTIONS,
    GET_PROPOSAL_COMMENTS,
    GET_PROPOSAL_BIDS,
    GET_PROPOSAL_REVIEWS, CHANGE_PROPOSAL_STATUS,
} from "./types";
import {httpClient} from "../index";
import {IProposal} from "../../models/IComponents";

const proposalURL = 'proposals/{pid}/';
const partnerURL = proposalURL + 'partnermembers/';
const staffURL = proposalURL + 'staff/';
const sectionsURL = proposalURL + 'sections/';
const commentsURL = proposalURL + 'comments/';
const bidsURL = proposalURL + 'bids/';
const reviewsURL = proposalURL + 'reviews/';

export const fetch = (url: string, reduxAction: string, isArray: boolean = true) => {
    return (dispatch: any, getState: any) => {
        return httpClient.get(url)
            .then((response: any) => {
                dispatch({
                    type: reduxAction,
                    payload: isArray ? response.data.content : response.data
                })
            }).catch((error: any) => {
                console.log(error)
            })
    }
};

export const fetchPartners = (id: number) => {
    return fetch(partnerURL.replace("{pid}", id.toString()), GET_PROPOSAL_PARTNERS)
};

export const fetchStaff = (id: number) => {
    return fetch(staffURL.replace("{pid}", id.toString()), GET_PROPOSAL_STAFF)
};

export const fetchSections = (id: number) => {
    return fetch(sectionsURL.replace("{pid}", id.toString()), GET_PROPOSAL_SECTIONS)
};

export const fetchComments = (id: number) => {
    return fetch(commentsURL.replace("{pid}", id.toString()), GET_PROPOSAL_COMMENTS)
};

export const fetchBids = (id: number) => {
    return fetch(bidsURL.replace("{pid}", id.toString()), GET_PROPOSAL_BIDS)
};

export const fetchReviews = (id: number) => {
    return fetch(reviewsURL.replace("{pid}", id.toString()), GET_PROPOSAL_REVIEWS)
};

export const setStatus = (id: number, proposal: IProposal, newStatus: string) => {
    return (dispatch: any, getState: any) => {
        return httpClient.put(proposalURL.replace("{pid}", id.toString()), {...proposal, status: newStatus})
            .then((response: any) => {
                dispatch({
                    type: CHANGE_PROPOSAL_STATUS,
                    payload: newStatus
                })
            }).catch((error: any) => {
                console.log(error)
            })
    }
};