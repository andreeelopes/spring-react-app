import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showReviewModal} from "../../../actions/proposals/proposalPageModalsAction";

export class AddReviewForm extends React.Component<any> {
    public handleOpen = () => {
        this.props.showReviewModal(true);
    };
    public handleClose = () => {
        this.props.showReviewModal(false);
    };
    public render() {
        return (<div>
                <Modal show={this.props.reviewModal} onHide={this.handleClose}>
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
                <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Review</Button>
            </div>
        );
    }

}
const mapStateToProps = (state: any) => ({
    reviewModal: state.proposalPageModals.reviewModal,
});

export default connect(mapStateToProps, {showReviewModal})(AddReviewForm);
