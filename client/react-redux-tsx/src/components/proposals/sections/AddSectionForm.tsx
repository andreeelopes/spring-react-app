import * as React from "react";
import {Button, ControlLabel, FormControl, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showSectionModal} from "../../../actions/proposals/proposalPageModalsAction";
import {submitSection} from "../../../actions/sections/sectionFormAction";

export class AddSectionForm extends React.Component<any> {
    private type: HTMLInputElement;
    private content: HTMLInputElement;
    public handleOpen = () => {
        this.props.showSectionModal(true);
    };
    public handleClose = () => {
        console.log(this.type.value);
        console.log(this.content.value);
        this.props.showSectionModal(false);
    };
    public submit = () => {
        if (this.type.value.length === 0 || this.type.value.length === 0) {
            return;
        }
        this.props.submitSection(this.props.id, this.type.value, this.content.value).then(() => this.handleClose());
    };

    public render() {

        return (<div className={"prop-control-button"}>
                <Modal show={this.props.sectionModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Create a new Section</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <ControlLabel>Type</ControlLabel>
                        <FormControl
                            componentClass="input"
                            placeholder="Type of the new section"
                            inputRef={(ref) => {
                                this.type = ref
                            }}
                        />
                        <ControlLabel>Section Description</ControlLabel>
                        <FormControl
                            componentClass="input"
                            placeholder="Content of the new section"
                            inputRef={(ref) => {
                                this.content = ref
                            }}
                        />
                    </Modal.Body>

                    <Modal.Footer>
                        <Button className="red-button" onClick={this.handleClose}>Close</Button>
                        <Button className="blue-button" onClick={this.submit}>Add section</Button>
                    </Modal.Footer>
                </Modal>
                <Button className="blue-button" bsStyle="success" onClick={this.handleOpen}>Add
                    Section</Button>
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    sectionModal: state.proposalPageModals.sectionModal,
});

export default connect(mapStateToProps, {showSectionModal, submitSection})(AddSectionForm);