import * as React from "react";
import {Button, FormControl, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showSectionModal} from "../../../actions/proposals/proposalPageModalsAction";

export class AddSectionForm extends React.Component<any> {
    private  title:any;
    public handleOpen = () => {
        this.props.showSectionModal(true);
    };
    public handleClose = () => {
        console.log(this.title.value);
        this.props.showSectionModal(false);
    };
    public render() {

        return (<div>
                <Modal show={this.props.sectionModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Create a new Proposal</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <FormControl
                            componentClass="input"
                            placeholder="Enter recipe title"
                            inputRef={(ref) => {this.title = ref}}
                            />
                    </Modal.Body>

                    <Modal.Footer>
                        <Button onClick={this.handleClose}>Close</Button>
                    </Modal.Footer>
                </Modal>
                <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Section</Button>
            </div>
        );
    }

}
const mapStateToProps = (state: any) => ({
    sectionModal: state.proposalPageModals.sectionModal,
});

export default connect(mapStateToProps, {showSectionModal})(AddSectionForm);