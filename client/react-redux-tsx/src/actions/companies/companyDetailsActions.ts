import {GET_COMPANY, GET_EMPLOYEES_COMPANY} from "./types";
import {fetch} from "../proposals/ProposalDetailsActions";

const companiesUrl = '/companies';

export const fetchCompany = (cid: number) => {
    return fetch(`${companiesUrl}/${cid}`, GET_COMPANY, false)
};

export const fetchEmployeesOfCompany = (cid: number) => {
    return fetch(`${companiesUrl}/${cid}/employees`, GET_EMPLOYEES_COMPANY)
};
