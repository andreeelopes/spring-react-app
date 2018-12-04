import {Button, ControlLabel, FormControl, FormGroup, Modal} from "react-bootstrap";
import * as React from "react";
import {connect} from "react-redux";
import {hideModal, showModal} from "../../actions/proposalModalActions";
import {
    changeApproverForm,
    changeDescriptionForm,
    changePartnerCompanyForm,
    changeTitleForm
} from "../../actions/proposalFormActions";
import {ICompany} from "../../models/IComponents";
import axios from 'axios';

interface Iid {
    id: number
}

interface IAddProposalJson {
    approver: Iid,
    companyProposed: ICompany,
    partnerCompany: Iid
}

class AddProposalModal extends React.Component<any> {
    private approverID: number;
    private companyID: number;
    public handleClose = () => {
        this.props.hideModal();
    };

    public handleTitleChange = (e: any) => {
        this.props.changeTitleForm(e.target.value);
    };

    public handleDescriptionChange = (e: any) => {
        this.props.changeDescriptionForm(e.target.value);
    };
    public handlePartnerCompanyChange = (e: any) => {
        this.props.changePartnerCompanyForm(e.target.value);
    };
    public handleApproverChange = (e: any) => {
        this.props.changeApproverForm(e.target.value);
    };
    public getValidationState = () => {

        const length = this.props.proposalFormTitle.length;
        if (length <= 10 && length >= 5) {
            return 'success';
        }
        else if (length > 0) {
            return 'error';
        }
        return null;
    };
    public approverExist = () => {
        console.log("whattt");
        axios.get('http://localhost:8080/employees?exist=' + this.props.proposalFormApprover, {
            auth: {
                password: "password",
                username: "employee21"
            }
        }).then((response) => {
            if (response.data.totalElements > 0) {
                console.log(response.data);
                this.approverID = response.data.content[0].id;
                this.companyExist();
            }
            return false;
        })
    };
    public companyExist = () => {
        axios.get('http://localhost:8080/companies?search' + this.props.proposalFormPartnerCompany, {
            auth: {
                password: "password",
                username: "employee21"
            }
        }).then((response) => {
            if (response.data.totalElements > 0) {
                this.companyID = response.data.content[0].id;
                this.addProposal();
            }
            return false;
        })
    };
    public addProposal = () => {
        const userData: string | null = sessionStorage.getItem('myData');

        if (userData !== null) {
            const userDataJSON: any = JSON.parse(userData);
            const approverID = {id: this.approverID};
            const companyID = {id: this.companyID};
            const AddProposalJson: IAddProposalJson = {
                approver: approverID,
                companyProposed: userDataJSON.company,
                partnerCompany: companyID
            };

            axios.post('http://localhost:8080/proposals/', AddProposalJson, {
                auth: {
                    password: "password",
                    username: "employee21"
                }
            })
                .then((response) => {
                    const proposalid = {id: response.data};
                    axios.post('http://localhost:8080/proposals/' + proposalid.id + "/sections/",
                        {text: this.props.proposalFormTitle, type: "title", proposal: proposalid}, {
                            auth: {
                                password: "password",
                                username: "employee21"
                            }
                        }).then(() => {
                        axios.post('http://localhost:8080/proposals/' + proposalid.id + "/sections/",
                            {text: this.props.proposalFormDescription, type: "description", proposal: proposalid}, {
                                auth: {
                                    password: "password",
                                    username: "employee21"
                                }
                            })
                    });
                });
        }
    };
    public submit = () => {
        if (this.getValidationState() !== 'success' || this.props.proposalFormDescription > 0) {
            return;
        }
        this.approverExist();


    };

    public render() {
        return (
            <Modal show={this.props.proposalModal} onHide={this.handleClose}>
                <Modal.Header>
                    <Modal.Title>Create a new Proposal</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <form>
                        <FormGroup
                            controlId="formBasicText"
                            validationState={this.getValidationState()}
                        >
                            <ControlLabel>Title</ControlLabel>
                            <FormControl
                                type="text"
                                value={this.props.proposalFormTitle}
                                placeholder="Enter title"
                                onChange={this.handleTitleChange}
                            />
                            <FormControl.Feedback/>
                        </FormGroup>
                    </form>
                    <form>
                        <FormGroup bsSize="large" controlId="formControlsTextarea">
                            <ControlLabel>Desciption</ControlLabel>
                            <FormControl
                                style={{height: 500}}
                                componentClass="textarea"
                                placeholder="Your description"
                                value={this.props.proposalFormDescription}
                                onChange={this.handleDescriptionChange}
                            />
                        </FormGroup>
                    </form>
                    <form>
                        <ControlLabel>Company</ControlLabel>
                        <FormControl
                            type="text"
                            value={this.props.proposalFormPartnerCompany}
                            placeholder="Enter Company"
                            onChange={this.handlePartnerCompanyChange}
                        />
                    </form>
                    <form>
                        <ControlLabel>Approver</ControlLabel>
                        <FormControl
                            type="text"
                            value={this.props.proposalFormApprover}
                            placeholder="Enter Approver"
                            onChange={this.handleApproverChange}
                        />
                    </form>
                </Modal.Body>

                <Modal.Footer>
                    <Button onClick={this.handleClose}>Close</Button>
                    <Button onClick={this.submit} bsStyle="primary">AddProposal</Button>
                </Modal.Footer>
            </Modal>)
    }
}

const mapStateToProps = (state: any) => ({
    proposalModal: state.proposalModal.state,
    proposalFormTitle: state.proposalForm.title,
    proposalFormDescription: state.proposalForm.description,
    proposalFormPartnerCompany: state.proposalForm.partnerCompany,
    proposalFormApprover: state.proposalForm.approver
});

export default connect(mapStateToProps, {
    showModal,
    hideModal,
    changeTitleForm,
    changeDescriptionForm,
    changePartnerCompanyForm,
    changeApproverForm
})(AddProposalModal)