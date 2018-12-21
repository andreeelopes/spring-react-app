import * as React from "react";
import {connect} from "react-redux";
import {
    setStatus
} from "../../actions/proposals/ProposalDetailsActions";
import {Button, Col} from "react-bootstrap";
import {ProposalStatus} from "../../models/IComponents";


export class StatusChangeButtons extends React.Component<any> {

    public render() {
        return (
            <div>
                {this.props.proposal.status === ProposalStatus.placed &&
                <Col md={6}>
                    <Button
                        onClick={() => this.props.setStatus(this.props.proposal.id, this.props.proposal, ProposalStatus.review_period)}>
                        Begin Review Period
                    </Button>
                </Col>}
                {this.props.proposal.status === ProposalStatus.review_period &&
                <Col md={6}>
                    <Button
                        onClick={() => this.props.setStatus(this.props.proposal.id, this.props.proposal, ProposalStatus.approved)}>
                        Approve
                    </Button>

                    <Button
                        onClick={() => this.props.setStatus(this.props.proposal.id, this.props.proposal, ProposalStatus.declined)}>
                        Decline
                    </Button>
                </Col>}
            </div>
        );
    }


}


// Make necessary proposals details available in  props
const mapStateToProps = (state: any) =>
    ({});

export default connect(mapStateToProps, {
    setStatus
})(StatusChangeButtons);
