import {GET_EMPLOYEE} from "../proposals/types";
import {fetch} from "../proposals/ProposalDetailsActions";

const employeesURL = '/employees/{pid}/';

export const fetchEmployee = (id: number) => {
    return fetch(employeesURL.replace("{pid}", id.toString()), GET_EMPLOYEE, false);
};