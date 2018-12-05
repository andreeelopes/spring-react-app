import * as React from "react";
// import {BidsList} from "./bids/BidsList";
// import {ReviewsList} from "./reviews/ReviewsList";
// import {CommentsList} from "./comments/CommentsList";
// import {SectionsList} from "./sections/SectionsList";
// import {StaffList} from "./StaffList";
import {PartnerList} from "./PartnerList";
import {connect} from "react-redux";
import {fetchPartners} from "../../actions/proposals/ProposalDetailsActions";


export class Proposal extends React.Component<any> {
    //TODO atencao as permissoes porque nem todos podem ver  as listas      -nelson
    public componentDidMount() {
        const params = this.props.match.params;
        if (params) {
            this.props.fetchPartners(params.id);
            console.log(this.props);


            // this.props.fetchStaff(id);
            // this.props.fetchSections(id);
            // this.props.fetchComments(id);
            // this.props.fetchBids(id);
            // this.props.fetchReviews(id);
        }
    }

    public render() {
        return (
            <div>
                <h1>Titulo Proposta</h1>
                <PartnerList partners={this.props.partners}/>
                {/*<StaffList/>*/}
                {/*<SectionsList/>*/}
                {/*<CommentsList/>*/}
                {/*<BidsList/>*/}
                {/*<ReviewsList/>*/}
            </div>);
    }
}

// Make necessary proposals details available in  props
const mapStateToProps = (state: any) =>
    ({partners: state.proposalDetails.partners});

export default connect(mapStateToProps, {fetchPartners})(Proposal);

