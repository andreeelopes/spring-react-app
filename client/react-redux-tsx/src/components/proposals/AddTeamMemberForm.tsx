import * as React from "react";
import {Button, Dropdown, DropdownButton, FormControl, MenuItem, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showTeamMemberModal} from "../../actions/proposals/proposalPageModalsAction";
import {getUser} from "../../actions/getSessionUser";
import {changeTitle, submitStaffMember, submitTeamMember} from "../../actions/proposals/addTeamMemberAction";

export class AddTeamMemberForm extends React.Component<any> {
    private newTeamMember: any;
    public handleOpen = () => {
        this.props.showTeamMemberModal(true);
    };
    public handleClose = () => {
        this.props.showTeamMemberModal(false);
    };
    public submit = () => {
        const user = getUser();
        if (this.newTeamMember.value.lenght === 0) {
            return;
        }
        if (this.props.title === "Partner Member") {
            this.props.submitPartnerMember(user.id, this.props.id, this.newTeamMember.value).then(() => this.handleClose());
        }
        else if (this.props.title === "Staff Member") {
            this.props.submitStaffMember(user.id, this.props.id, this.newTeamMember.value).then(() => this.handleClose());
        }
    };
    public changeToPartner = () => {
        this.props.changeTitle("Partner Member");
    }
    public changeToStaff = () => {
        this.props.changeTitle("Staff Member");
    }

    public render() {
        return (<div className={"prop-control-button"}>
                <Modal show={this.props.teamMemberModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Add a proposal team member</Modal.Title>
                    </Modal.Header>
                    <Dropdown id="dropdown-custom-menu">
                        <DropdownButton
                            className={"blue-button"}
                            bsStyle={"primary"}
                            title={this.props.title}
                            key={0}
                            id={`dropdown-basic-${0}`}
                        >

                            <MenuItem eventKey="1" onSelect={this.changeToPartner}>Partner member</MenuItem>
                            <MenuItem eventKey="2" onSelect={this.changeToStaff}>Staff member</MenuItem>
                        </DropdownButton>
                    </Dropdown>
                    <Modal.Body>
                        <FormControl
                            type="text"
                            placeholder="Enter description"
                            inputRef={(ref) => {
                                this.newTeamMember = ref
                            }}
                        />
                    </Modal.Body>

                    <Modal.Footer>
                        <Button className="red-button" onClick={this.handleClose}>Close</Button>
                        <Button className="blue-button" onClick={this.submit}>Add member</Button>
                    </Modal.Footer>
                </Modal>
                <Button className="blue-button" bsStyle="success" onClick={this.handleOpen}>Add
                    TeamMember</Button>
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    teamMemberModal: state.proposalPageModals.teamMemberModal,
    title: state.proposalPageModals.membertype
});

export default connect(mapStateToProps, {
    showTeamMemberModal,
    submitTeamMember,
    changeTitle,
    submitStaffMember
})(AddTeamMemberForm);