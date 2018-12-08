
import axios from "axios";
import {ICompany} from "../../models/IComponents";
import {getUser} from "../getSessionUser"

interface Iid {
    id: number
}

interface IAddProposalJson {
    approver: Iid,
    companyProposed: ICompany,
    partnerCompany: Iid
}


export const submit = (approver: string, partnerCompany: string, title: string, description: string, history: any) => {
    axios.get('http://localhost:8080/employees?exist=' + approver, {
        withCredentials: true
    }).then((response) => {
        if (response.data.totalElements > 0) {
            console.log(response.data);
            const approverID = response.data.content[0].id;
            getCompanyId(approverID, partnerCompany, title, description, history);
        }
    })
};
export const getCompanyId = (approverID: number, partnerCompany: string, title: string, description: string, history: any) => {
    axios.get('http://localhost:8080/companies?search' + partnerCompany, {
        withCredentials: true
    }).then((response) => {
        if (response.data.totalElements > 0) {
            const companyID = response.data.content[0].id;
            addProposal(companyID, approverID, title, description, history);
        }
    })
};

export const addProposal = (companyID: number, approverID: number, title: string, description: string, history: any) => {
    const userDataJSON = getUser();

    if (userDataJSON !== null) {
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
                        }).then(() => {
                        history.push('/proposals/' + proposalid.id)
                    });
                });
            });
    }
};