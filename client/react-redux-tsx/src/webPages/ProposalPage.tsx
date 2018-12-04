import * as React from "react";

import {Proposal} from "../Components/Proposal";
import {AddCommentForm} from "../Components/AddCommentForm";
import {AddBidForm} from "../Components/AddBidForm";
import {AddReviewForm} from "../Components/AddReviewForm";
import {AddTeamMemberForm} from "../Components/AddTeamMemberForm";
import {AddSectionForm} from "../Components/AddSectionForm";
import {ChangeStateButton} from "../Components/ChangeStateButton";


export class ProposalPage extends React.Component<any> {

    public constructor(props: any) {
        super(props);
    }

    // TODO
    public render() {
        const {match} = this.props
        const id = match.params.id
        console.log(id);
        return (
            <div>
                <Proposal/>
                <AddCommentForm/>
                <AddBidForm/>
                <AddReviewForm/>

                /* staff only*/
                <AddTeamMemberForm/>
                <AddSectionForm/>

                {/*Approver only*/}
                <ChangeStateButton/>
            </div>
        );
    }
}
// TODO
const mapStateToProps = (state: any) => ({

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

