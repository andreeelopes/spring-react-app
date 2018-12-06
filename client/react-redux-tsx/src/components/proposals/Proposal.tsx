import * as React from "react";
// import {BidsList} from "./bids/BidsList";
// import {ReviewsList} from "./reviews/ReviewsList";
// import {CommentsList} from "./comments/CommentsList";
// import {SectionsList} from "./sections/SectionsList";
// import {StaffList} from "./StaffList";
import {connect} from "react-redux";
import {fetchPartners, fetchStaff} from "../../actions/proposals/ProposalDetailsActions";
import SimpleList from "../common/SimpleList";
import {IEmployee, ISection} from "../../models/IComponents";


export class Proposal extends React.Component<any> {
    //TODO atencao as permissoes porque nem todos podem ver  as listas      -nelson
    public componentDidMount() {
        const params = this.props.match.params;
        if (params) {
            this.props.fetchPartners(params.id);
            this.props.fetchStaff(params.id);
            // this.props.fetchSections(params.id);
            // this.props.fetchComments(params.id);
            // this.props.fetchBids(params.id);
            // this.props.fetchReviews(params.id);
        }
    }

    public render() {
        return (
            <div>
                <h1>Titulo Proposta</h1>
                <SimpleList<IEmployee> title="Partners"
                                       list={this.props.partners}
                                       show={this.employeesshow}
                />
                <SimpleList<IEmployee> title="Staff"
                                       list={this.props.staff}
                                       show={this.employeesshow}
                />
                <SimpleList<ISection> title="Sections"
                                      list={this.props.sections}
                                      show={this.sectionsShow}
                />
                {/*<CommentsList/>*/}
                {/*<BidsList/>*/}
                {/*<ReviewsList/>*/}
            </div>);
    }

    private employeesshow = (employee: IEmployee) => `${employee.name} (${employee.email})`;
    private sectionsShow = (section: ISection) => `${section.type}: ${section.text}`;

}

// Make necessary proposals details available in  props
const mapStateToProps = (state: any) =>
    ({partners: state.proposalDetails.partners});

export default connect(mapStateToProps, {
    fetchPartners,
    fetchStaff
})(Proposal);

