import {Button, ControlLabel, FormControl, FormGroup, Modal} from "react-bootstrap";
import * as React from "react";
import {connect} from "react-redux";
import {hideModal, showModal} from "../../actions/proposals/proposalModalActions";
import {submit
} from "../../actions/proposals/proposalFormActions";
import {withRouter} from "react-router";


class AddProposalModal extends React.Component<any> {
    private approver:any;
    private partnerCompany:any;
    private title:any;
    private description:any;

    public SubmitButton = withRouter(({history}) => (
        <Button
            onClick={() => this.submit(history)}
        >
            Submit
        </Button>
    ));
    public handleClose = () => {
        this.props.hideModal();
    };



    public submit = (history: any) => {

        if ( this.description.value.length === 0) {
            return;
        }
        submit(this.approver.value, this.partnerCompany.value, this.title.value, this.description.value, history);


    };
    public componentWillMount()  {
        this.handleClose();
    }
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
                        >
                            <ControlLabel>Title</ControlLabel>
                            <FormControl
                                type="text"
                                inputRef={(ref) => {this.title = ref}}
                                placeholder="Enter title"
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
                                inputRef={(ref) => {this.description = ref}}
                            />
                        </FormGroup>
                    </form>
                    <form>
                        <ControlLabel>Company</ControlLabel>
                        <FormControl
                            type="text"
                            placeholder="Enter Company"
                            inputRef={(ref) => {this.partnerCompany = ref}}
                        />
                    </form>
                    <form>
                        <ControlLabel>Approver</ControlLabel>
                        <FormControl
                            type="text"
                            placeholder="Enter Approver"
                            inputRef={(ref) => {this.approver = ref}}
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

});

export default connect(mapStateToProps, {
    showModal,
    hideModal,
})(AddProposalModal)