import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showBidModal} from "../../../actions/proposals/proposalPageModalsAction";
import {addBid} from "../../../actions/bids/addBidAction";
import {getUser} from "../../../actions/getSessionUser";
import {showBidButton} from "../../../actions/bids/addBidButtonAction";
import {ProposalStatus} from "../../../models/IComponents";

export class AddBidForm extends React.Component<any> {

    public constructor(props: any) {
        super(props);
        this.props.showBidButton(true)
    }
    public handleOpen = () => {
        this.props.showBidModal(true);
    };
    public handleClose = () => {
        this.props.showBidModal(false);
    };
    public submit = () => {
        console.log(getUser().id, this.props.proposal.id);
        addBid(getUser().id, this.props.proposal.id).then(() => {
            this.props.showBidButton(false);
            this.props.showBidModal(false);
        });
    };


    public componentWillReceiveProps(nextprops: any) {
        if (this.props.addBidButtonStatus) {
            const user = getUser();

            if (nextprops.proposal.status !== ProposalStatus.placed) {
                this.props.showBidButton(false);
            }
            else if (nextprops.bids != null) {
                console.log("what3")
                const found: any = nextprops.bids.find((element: any) => (element.pk.bidder.username === user.username && element.pk.proposal.id === nextprops.proposal.id));
                if (found !== undefined) {
                    this.props.showBidButton(false)
                }
            }
            else if (nextprops.proposal != null && nextprops.proposal.approver.id === user.id) {
                console.log("what2")
                this.props.showBidButton(false)
            }

        }
    }

    public render() {
        return (<div className={"prop-control-button"}>
                <Modal show={this.props.bidModal} onHide={this.handleClose}>
                    <Modal.Header>
                        <Modal.Title>Bid proposal</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        Please confirm action.
                    </Modal.Body>

                    <Modal.Footer>
                        <Button className="red-button" onClick={this.handleClose}>Cancel</Button>
                        <Button className="blue-button" onClick={this.submit}>Confirm</Button>
                    </Modal.Footer>
                </Modal>
                {(this.props.addBidButtonStatus) ?
                    <Button className="blue-button" bsStyle="success" onClick={this.handleOpen}>Add
                        Bid</Button> : null}
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    bidModal: state.proposalPageModals.bidModal,
    addBidButtonStatus: state.addBidButton.showButton,

});

export default connect(mapStateToProps, {showBidModal, showBidButton})(AddBidForm);
