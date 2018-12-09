import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showBidModal} from "../../../actions/proposals/proposalPageModalsAction";
import {addBid} from "../../../actions/bids/addBidAction";
import {getUser} from "../../../actions/getSessionUser";

export class AddBidForm extends React.Component<any> {
    public handleOpen = () => {
        this.props.showBidModal(true);
    };
    public handleClose = () => {
        this.props.showBidModal(false);
    };
    public submit = () =>{
        addBid(getUser().id,this.props.id);
    };
    public render() {
        return (<div>
                <Modal show={this.props.bidModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Bid proposal</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        You sure u want to bid?
                    </Modal.Body>

                    <Modal.Footer>
                        <Button onClick={this.handleClose}>No</Button>
                        <Button onClick={this.submit}>Yes</Button>
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
