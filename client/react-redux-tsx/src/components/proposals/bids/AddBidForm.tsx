import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showBidModal} from "../../../actions/proposals/proposalPageModalsAction";

export class AddBidForm extends React.Component<any> {
    public handleOpen = () => {
        this.props.showBidModal(true);
    };
    public handleClose = () => {
        this.props.showBidModal(false);
    };
    public render() {
        return (<div>
                <Modal show={this.props.bidModal} onHide={this.handleClose}>
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
                <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Bid</Button>
            </div>
        );
    }

}
const mapStateToProps = (state: any) => ({
    bidModal: state.proposalPageModals.bidModal,
});

export default connect(mapStateToProps, {showBidModal})(AddBidForm);
