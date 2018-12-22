import * as React from "react";
import {Button, FormControl, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showReviewModal} from "../../../actions/proposals/proposalPageModalsAction";
import {getUser} from "../../../actions/getSessionUser";
import {submitReview} from "../../../actions/reviews/reviewFormAction";

export class AddReviewForm extends React.Component<any> {
    private description: any;
    public handleOpen = () => {
        this.props.showReviewModal(true);
    };
    public handleClose = () => {
        this.props.showReviewModal(false);
    };
    public submit = () => {
        const user = getUser();
        if (this.description.value.length === 0) {
            return;
        }
        this.props.submitReview(user.id, this.props.id, this.description.value).then(() => this.handleClose());
    };

    public render() {
        return (<div className={"specialOne"}>
                <Modal show={this.props.reviewModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Add Review</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <FormControl
                            componentClass="textarea"
                            placeholder="Enter description"
                            inputRef={(ref) => {
                                this.description = ref
                            }}
                        />
                    </Modal.Body>

                    <Modal.Footer>
                        <Button className="red-button" onClick={this.handleClose}>Close</Button>
                        <Button className="blue-button" onClick={this.submit}>Add review</Button>
                    </Modal.Footer>
                </Modal>
                <Button className="blue-button" bsStyle="success" onClick={this.handleOpen}>Add Review</Button>
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    reviewModal: state.proposalPageModals.reviewModal,
});

export default connect(mapStateToProps, {showReviewModal, submitReview})(AddReviewForm);
