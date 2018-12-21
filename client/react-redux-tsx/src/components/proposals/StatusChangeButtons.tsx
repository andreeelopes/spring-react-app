import * as React from "react";
import {connect} from "react-redux";
import {
    setStatus
} from "../../actions/proposals/ProposalDetailsActions";
import {Button} from "react-bootstrap";
import {ProposalStatus} from "../../models/IComponents";


export class StatusChangeButtons extends React.Component<any> {

    public render() {
        if (this.props.proposal.status === ProposalStatus.placed) {
            return (
                <Button
                    onClick={() => this.props.setStatus(this.props.proposal.id, this.props.proposal, ProposalStatus.review_period)}>
                    Begin Review Period
                </Button>
            );
        }
        else if (this.props.proposal.status === ProposalStatus.review_period) {
            return (
                <div className={"buttons-status"}>
                    <Button className={"blue-button"}
                        onClick={() => this.props.setStatus(this.props.proposal.id, this.props.proposal, ProposalStatus.approved)}>
                        Approve
                    </Button>
                    < Button className={"blue-button"}
                        onClick={() => this.props.setStatus(this.props.proposal.id, this.props.proposal, ProposalStatus.declined)}>
                        Decline
                    </Button>
                </div>
            );
        }
        return null;
    }


}


// Make necessary proposals details available in  props
const mapStateToProps = (state: any) =>
    ({});

export default connect(mapStateToProps, {
    setStatus
})(StatusChangeButtons);
