import * as React from "react";
import {connect} from "react-redux";
import {
    fetchPartners,
    fetchStaff,
    fetchSections,
    fetchComments,
    fetchBids,
    fetchReviews
} from "../../actions/proposals/ProposalDetailsActions";
import SimpleList from "../common/SimpleList";
import {IComment, IEmployee, IReview, ISection, ProposalStatus} from "../../models/IComponents";
import * as Grid from "react-bootstrap/lib/Grid";
import {Col, Row} from "react-bootstrap";
import StatusChangeButtons from "./StatusChangeButtons";
import {Link} from "react-router-dom";


export class Proposal extends React.Component<any> {
    private proposalID = -1;

    //TODO atencao as permissoes porque nem todos podem ver  as listas      -nelson
    public componentWillMount() {
        const params = this.props.match.params;
        if (params) {
            this.proposalID = params.id;
            this.props.fetchPartners(this.proposalID);
            this.props.fetchStaff(this.proposalID);
            this.props.fetchSections(this.proposalID);
            this.props.fetchComments(this.proposalID);
            this.props.fetchBids(this.proposalID);
            this.props.fetchReviews(this.proposalID);

        }
    }

    public render() {
        if(this.props.proposal){
            debugger;
        }
        return (
            this.props.proposal &&
            <Grid>
                <Row>
                    <Col md={12}>
                        <h1>{this.getTitle(this.props.sections)}</h1>
                    </Col>
                </Row>
                <Row>
                    <Col md={6}>
                        <h5><b>Status:</b> {this.parseStatus(this.props.proposal.status)}</h5>
                    </Col>

                    {!this.isProposalClose(this.props.proposal.status) &&
                    <Col md={6}>
                        <StatusChangeButtons proposal={this.props.proposal}/>
                    </Col>}
                    {this.isProposalClose(this.props.proposal.status) && <Col md={6}/>}
                </Row>
                <Row>
                    <Col md={12}>
                        <h5><b>Approver:</b> {this.props.proposal.approver.name}</h5>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <h5><b>Proposed by:</b> {this.props.proposal.companyProposed.name}</h5>
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <h5><b>Partner:</b> {this.props.proposal.partnerCompany.name}</h5>
                    </Col>
                </Row>
                <Row>
                    <Col md={6}>
                        <SimpleList<IEmployee> title="Partners"
                                               list={this.props.partners}
                                               show={this.employeesShow}
                        /> </Col>
                    <Col md={6}>
                        <SimpleList<IEmployee> title="Staff"
                                               list={this.props.staff}
                                               show={this.employeesShow}

                        />
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <SimpleList<ISection> title="Sections"
                                              list={this.props.sections}
                                              show={this.sectionsShow}
                        />
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <SimpleList<IComment> title="Comments"
                                              list={this.props.comments}
                                              show={this.commentsShow}
                        />
                    </Col>
                </Row>
                {/*<SimpleList<IBid> title="Bids"*/}
                {/*list={this.props.bids}*/}
                {/*show={this.bidsShow}*/}
                {/*/>*/}
                <Row>
                    <Col md={12}>
                        <SimpleList<IReview> title="Reviews"
                                             list={this.props.reviews}
                                             show={this.reviewsShow}
                        />
                    </Col>
                </Row>

            </Grid>
        );
    }

    private employeesShow = (employee: IEmployee) => (
        <div>
            <Link to={`/employees/${employee.id}`}> {employee.name} </Link>
            {employee.email} {employee.job}
        </div>
    );
    private sectionsShow = (section: ISection) => `${section.type}: ${section.text}`;
    private commentsShow = (comment: any) => `${comment.author}: ${comment.comment}`;
    // private bidsShow = (comment: IBid) => `${comment.author}: ${comment.comment}`;
    private reviewsShow = (review: IReview) => `${review.author}: ${review.score}`;
    private parseStatus = (status: string) => {
        switch (status) {
            case ProposalStatus.placed:
                return "Placed";
            case ProposalStatus.approved:
                return "Approved";
            case ProposalStatus.declined:
                return "Declined";
            case ProposalStatus.review_period:
                return "Review period";
            default:
                console.error("Invalid proposal status: " + status);
        }
        return "Invalid status";
    };


    private getTitle = (sections: ISection[]) => {
        if (sections.length !== 0) {
            return sections.filter(section => section.type === 'title')[0].text;
        }
        else {
            return "Proposal";
        }

    }

    private isProposalClose = (status: string) => {
        const closed = status === ProposalStatus.approved || status === ProposalStatus.declined;
        console.log("Closed = " + closed);
        return closed;
    };

}

// Make necessary proposals details available in  props
const mapStateToProps = (state: any) =>
    ({
        partners: state.proposalDetails.partners,
        staff: state.proposalDetails.staff,
        sections: state.proposalDetails.sections,
        comments: state.proposalDetails.comments,
        // bids: state.proposalDetails.bids,
        reviews: state.proposalDetails.reviews
    });

export default connect(mapStateToProps, {
    fetchPartners,
    fetchStaff,
    fetchSections,
    fetchComments,
    fetchBids,
    fetchReviews,
})(Proposal);

