import * as React from "react";

import Proposal from "./Proposal";
import AddCommentForm from "./comments/AddCommentForm";
import AddBidForm from "./bids/AddBidForm";
import AddReviewForm from "./reviews/AddReviewForm";
import AddTeamMemberForm from "./AddTeamMemberForm";
import AddSectionForm from "./sections/AddSectionForm";
import {ChangeStateButton} from "./ChangeStateButton";
import {connect} from "react-redux";
import {fetchProposal} from "../../actions/proposals/ProposalPageActions";


export class ProposalPage extends React.Component<any> {
    private proposalId=this.props.match.params.id;
    public constructor(props: any) {
        super(props);
    }
    public componentWillMount(){
        this.props.fetchProposal(this.proposalId);
    }
    // TODO
    public render() {
        return (
            <div>
                <Proposal {...this.props} />
                <AddCommentForm id={this.proposalId}/>
                <AddBidForm proposal={this.props.proposal}/>
                <AddReviewForm/>

                /* staff only*/
                <AddTeamMemberForm/>
                <AddSectionForm/>

                {/*Approver only*/}
                <ChangeStateButton/>
            </div>
        );
    }

    // private onFormSubmit = (newResource) => { //TODO -nelson
    //     switch (newResource.typeof) {
    //         case "Comment":
    //         case "Bid":
    //         case "TeamMember":
    //         case "SectionForm":
    //
    //     }
    //     this.props.saveCommentOnServer(newResource);
    // }
}

// TODO
const mapStateToProps = (state: any) => ({
    proposal:state.proposalPage.proposal
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
    fetchProposal
})(ProposalPage)

