import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import { showCommentModal} from "../../../actions/proposals/proposalPageModalsAction";

export class AddCommentForm extends React.Component<any> {
    public handleOpen = () => {
        this.props.showCommentModal(true);
    };
    public handleClose = () => {
        this.props.showCommentModal(false);
    };
    public render() {
        return (<div>
                <Modal show={this.props.commentModal} onHide={this.handleClose}>
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
                <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Comment</Button>
            </div>
        );
    }

}
const mapStateToProps = (state: any) => ({
    commentModal: state.proposalPageModals.commentModal,
});

export default connect(mapStateToProps, {showCommentModal})(AddCommentForm)
