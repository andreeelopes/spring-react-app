import * as React from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {showBidModal} from "../../../actions/proposals/proposalPageModalsAction";
import {addBid} from "../../../actions/bids/addBidAction";
import {getUser} from "../../../actions/getSessionUser";
import { showBidButton} from "../../../actions/bids/addBidButtonAction";

export class AddBidForm extends React.Component<any> {
    public handleOpen = () => {
        this.props.showBidModal(true);
    };
    public handleClose = () => {
        this.props.showBidModal(false);
    };
    public submit = () =>{
        addBid(getUser().id,this.props.proposal.id).then(()=>{
            this.props.showBidButton(false);
            this.props.showBidModal(false);
        });
    };

    public componentWillReceiveProps(nextprops:any){
        if(nextprops.proposal!=null && nextprops.proposal.status==="REVIEW_PERIOD" && this.props.addBidButtonStatus && nextprops.bids!=null){
            const user = getUser();
                const found:any=nextprops.bids.find((element:any) =>(element.pk.bidder.username===user.username && element.pk.proposal.id===nextprops.proposal.id));
                if(found!=null ){
                    this.props.showBidButton(false)
                }

            if(nextprops.proposal.approver.id===user.id){
                this.props.showBidButton(false)
            }
        }
    }

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
                {(this.props.addBidButtonStatus)? <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Bid</Button>:null}
            </div>
        );
    }

}
const mapStateToProps = (state: any) => ({
    bidModal: state.proposalPageModals.bidModal,
    addBidButtonStatus: state.addBidButton.showButton,

});

export default connect(mapStateToProps, {showBidModal,showBidButton})(AddBidForm);
