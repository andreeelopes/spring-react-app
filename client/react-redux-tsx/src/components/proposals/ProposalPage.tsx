import * as React from "react";

import {Proposal} from "./Proposal";
import {AddCommentForm} from "./comments/AddCommentForm";
import {AddBidForm} from "./bids/AddBidForm";
import {AddReviewForm} from "./reviews/AddReviewForm";
import {AddTeamMemberForm} from "./AddTeamMemberForm";
import {AddSectionForm} from "./sections/AddSectionForm";
import {ChangeStateButton} from "./ChangeStateButton";


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
// @ts-ignore //TODO remover, e so para nao se queixar   -nelson
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

