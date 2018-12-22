import * as React from "react";

import Proposal from "./Proposal";
import AddCommentForm from "./comments/AddCommentForm";
import AddBidForm from "./bids/AddBidForm";
import AddReviewForm from "./reviews/AddReviewForm";
import AddTeamMemberForm from "./AddTeamMemberForm";
import AddSectionForm from "./sections/AddSectionForm";
import {connect} from "react-redux";
import {fetchBids, fetchProposal} from "../../actions/proposals/ProposalPageActions";
import {getUser} from "../../actions/getSessionUser";


export class ProposalPage extends React.Component<any> {
    private proposalId = this.props.match.params.id;
    private imStaff = false;
    private imApprover = false;
    private canReview = false;
    private user = getUser();

    public constructor(props: any) {
        super(props);
        console.log(this.imApprover);//TODO - remover e so para calar um warning - nelson
    }

    public componentWillMount() {
        this.props.fetchProposal(this.proposalId).then(() => {

                this.props.fetchBids(this.user.id).then(() => {
                    const found = this.props.bids.find((element: any) => (element.pk.proposal.id === parseInt(this.proposalId, 0) && element.status === "ACCEPTED"));  // nao me perguntem pq mas o proposalId nao é int e dizia que era diferente
                    if (found != null && this.props.proposal.status === "REVIEW_PERIOD") {
                        this.canReview = true;
                    }
                });
        });


    }

    public componentWillReceiveProps(nextProps: any) {
        if (nextProps.staff.length > 0) {
            const found: any = nextProps.staff.find((element: any) => (element.username === this.user.username));
            if (found != null) {
                this.imStaff = true;
            }
        }
        if (this.props.proposal != null) {
            this.imApprover = this.user.id === this.props.proposal.approver.id;
        }
    }

    public render() {
        return (
            <div>
                <Proposal {...this.props} />
                <AddCommentForm id={this.proposalId}/>
                <AddBidForm proposal={this.props.proposal} bids={this.props.bids}/>

                {(this.canReview) ? <AddReviewForm id={this.proposalId}/> : null}

                {(this.imStaff) ? <AddTeamMemberForm id={this.proposalId}/> : null}
                {(this.imStaff) ? <AddSectionForm id={this.proposalId}/> : null}

            </div>
        );
    }
}

// TODO
const mapStateToProps = (state: any) => ({
    proposal: state.proposalPage.proposal,
    staff: state.proposalDetails.staff,
    bids: state.proposalPage.bids
});

// TODO
/*
export default connect(mapStateToProps, {
    addComment,
    addBid,
    addReview,
    addTeamMember,
    changeStatus,
    addSection,
    selectSection,
    selectComment,
    selectBidder,
    selectReview,
    selectStaff,
    selectPartner
})(AddProposalModal)
*/
export default connect(mapStateToProps, {
    fetchBids,
    fetchProposal
})(ProposalPage)

