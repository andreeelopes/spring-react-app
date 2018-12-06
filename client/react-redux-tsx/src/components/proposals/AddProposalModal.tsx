import {Button, ControlLabel, FormControl, FormGroup, Modal} from "react-bootstrap";
import * as React from "react";
import {connect} from "react-redux";
import {hideModal, showModal} from "../../actions/proposals/proposalModalActions";
import {
    changeApproverForm,
    changeDescriptionForm,
    changePartnerCompanyForm,
    changeTitleForm, submit
} from "../../actions/proposals/proposalFormActions";
import {withRouter} from "react-router";





class AddProposalModal extends React.Component<any> {
    public SubmitButton = withRouter(({ history }) => (
        <Button
            onClick={()=>this.submit(history)}
        >
            Submit
        </Button>
    ));
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

    public submit = (history:any) => {
        if (this.getValidationState() !== 'success' || this.props.proposalFormDescription > 0) {
            return;
        }
        submit(this.props.proposalFormApprover,this.props.proposalFormPartnerCompany,this.props.proposalFormTitle,this.props.proposalFormDescription,history);


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
                    <this.SubmitButton/>
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