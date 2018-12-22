import * as React from "react";
import {Button, FormControl, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showCommentModal} from "../../../actions/proposals/proposalPageModalsAction";
import {submitComment} from "../../../actions/comments/commentFormAction";
import {getUser} from "../../../actions/getSessionUser";

export class AddCommentForm extends React.Component<any> {
    private description: any;
    public handleOpen = () => {
        this.props.showCommentModal(true);
    };
    public handleClose = () => {
        this.props.showCommentModal(false);
    };
    public submit = () => {
        const user = getUser();
        if (this.description.value.lenght === 0) {
            return;
        }
        this.props.submitComment(user.id, this.props.id, this.description.value).then(() => this.handleClose());
    };

    public render() {
        return (<div className={"specialOne"}>
                <Modal show={this.props.commentModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Add a comment</Modal.Title>
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
                        <Button className={"red-button"} onClick={this.handleClose}>Cancel</Button>
                        <Button className={"blue-button"} onClick={this.submit}>Add Comment</Button>
                    </Modal.Footer>
                </Modal>
                <Button className={"blue-button"} onClick={this.handleOpen}>Add Comment</Button>
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    commentModal: state.proposalPageModals.commentModal,
    comments: state.proposalDetails.comments,
});

export default connect(mapStateToProps, {showCommentModal, submitComment})(AddCommentForm)
