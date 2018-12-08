import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import { showTeamMemberModal} from "../../actions/proposals/proposalPageModalsAction";

export class AddTeamMemberForm extends React.Component<any> {
    public handleOpen = () => {
        this.props.showTeamMemberModal(true);
    };
    public handleClose = () => {
        this.props.showTeamMemberModal(false);
    };
    public render() {
        return (<div>
            <Modal show={this.props.teamMemberModal} onHide={this.handleClose}>
        <Modal.Header>
            <Modal.Title>Create a new Proposal</Modal.Title>
        </Modal.Header>

        <Modal.Body>
        ola
        </Modal.Body>

        <Modal.Footer>
        <Button onClick={this.handleClose}>Close</Button>
        </Modal.Footer>
        </Modal>
        <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add TeamMember</Button>
        </div>
    );
    }

}
const mapStateToProps = (state: any) => ({
    teamMemberModal: state.proposalPageModals.teamMemberModal,
});

export default connect(mapStateToProps, {showTeamMemberModal})(AddTeamMemberForm);