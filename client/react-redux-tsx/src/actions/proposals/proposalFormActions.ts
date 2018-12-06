import {
    CHANGE_PROPOSAL_APPROVER,
    CHANGE_PROPOSAL_DESCRIPTION,
    CHANGE_PROPOSAL_PARTNERCOMPANY,
    CHANGE_PROPOSAL_TITLE
} from "./types";
import axios from "axios";
import {ICompany} from "../../models/IComponents";
interface Iid {
    id: number
}

interface IAddProposalJson {
    approver: Iid,
    companyProposed: ICompany,
    partnerCompany: Iid
}
export const changeTitleForm = (text: string) => (dispatch: any) => {
    dispatch({
        type: CHANGE_PROPOSAL_TITLE,
        payload: text
    })
};
export const changeDescriptionForm = (text: string) => (dispatch: any) => {
    dispatch({
        type: CHANGE_PROPOSAL_DESCRIPTION,
        payload: text
    })
};
export const changePartnerCompanyForm = (text: string) => (dispatch: any) => {
    dispatch({
        type: CHANGE_PROPOSAL_PARTNERCOMPANY,
        payload: text
    })
};
export const changeApproverForm = (text: string) => (dispatch: any) => {
    dispatch({
        type: CHANGE_PROPOSAL_APPROVER,
        payload: text
    })
};

export const submit = (approver: string, partnerCompany: string,title:string,description:string,history:any) => {
    axios.get('http://localhost:8080/employees?exist=' + approver, {
        auth: {
            password: "password",
            username: "employee21"
        }
    }).then((response) => {
        if (response.data.totalElements > 0) {
            console.log(response.data);
            const approverID = response.data.content[0].id;
            companyExist(approverID, partnerCompany,title,description,history);
        }
        return false;
    })
};
export const companyExist = (approverID: number, partnerCompany: string,title:string,description:string,history:any) => {
    axios.get('http://localhost:8080/companies?search' + partnerCompany, {
        withCredentials: true
    }).then((response) => {
        if (response.data.totalElements > 0) {
            const companyID = response.data.content[0].id;
            addProposal(companyID,approverID,title,description,history);
        }
        return false;
    })
};

export const addProposal = (companyID:number,approverID:number,title:string,description:string,history:any) => {
    const userData: string | null = sessionStorage.getItem('myData');

    if (userData !== null) {
        const userDataJSON: any = JSON.parse(userData);
        const approverIDObject = {id: approverID};
        const companyIDObject = {id: companyID};
        const AddProposalJson: IAddProposalJson = {
            approver: approverIDObject,
            companyProposed: userDataJSON.company,
            partnerCompany: companyIDObject
        };

        axios.post('http://localhost:8080/proposals/', AddProposalJson, {
            withCredentials: true
        })
            .then((response) => {
                const proposalid = {id: response.data};
                axios.post('http://localhost:8080/proposals/' + proposalid.id + "/sections/",
                    {text: title, type: "title", proposal: proposalid}, {
                        withCredentials: true
                    }).then(() => {
                    axios.post('http://localhost:8080/proposals/' + proposalid.id + "/sections/",
                        {text: description, type: "description", proposal: proposalid}, {
                            withCredentials: true
                        }).then(()=>{ history.push('/proposals/'+proposalid.id)});
                });
            });
    }
};