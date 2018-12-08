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
import {IComment, IEmployee, IReview, ISection} from "../../models/IComponents";


export class Proposal extends React.Component<any> {
    //TODO atencao as permissoes porque nem todos podem ver  as listas      -nelson
    public componentDidMount() {
        const params = this.props.match.params;
        if (params) {
            this.props.fetchPartners(params.id);
            this.props.fetchStaff(params.id);
            this.props.fetchSections(params.id);
            this.props.fetchComments(params.id);
            this.props.fetchBids(params.id);
            this.props.fetchReviews(params.id);
        }
    }

    public render() {
        return (
            <div>
                <h1>{this.getTitle(this.props.sections)}</h1>
                <SimpleList<IEmployee> title="Partners"
                                       list={this.props.partners}
                                       show={this.employeesShow}
                />
                <SimpleList<IEmployee> title="Staff"
                                       list={this.props.staff}
                                       show={this.employeesShow}

                />
                <SimpleList<ISection> title="Sections"
                                      list={this.props.sections}
                                      show={this.sectionsShow}
                />
                <SimpleList<IComment> title="Comments"
                                      list={this.props.comments}
                                      show={this.commentsShow}
                />
                {/*<SimpleList<IBid> title="Bids"*/}
                {/*list={this.props.bids}*/}
                {/*show={this.bidsShow}*/}
                {/*/>*/}
                <SimpleList<IReview> title="Reviews"
                                     list={this.props.reviews}
                                     show={this.reviewsShow}
                />
            </div>);
    }

    private employeesShow = (employee: IEmployee) => `${employee.name} (${employee.email})`;
    private sectionsShow = (section: ISection) => `${section.type}: ${section.text}`;
    private commentsShow = (comment: IComment) => `${comment.author}: ${comment.comment}`;
    // private bidsShow = (comment: IBid) => `${comment.author}: ${comment.comment}`;
    private reviewsShow = (review: IReview) => `${review.author}: ${review.score}`;

    private getTitle = (sections: ISection[]) => {
        if (sections) {
            return sections.filter(section => section.type === 'title')[0].text;
        }
        else {
            return "Proposal";
        }

    }

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
    fetchReviews
})(Proposal);

