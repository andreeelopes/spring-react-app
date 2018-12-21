import * as React from "react";
import {IEmployee, IProposal, ISection} from "../../../models/IComponents";
import {connect} from "react-redux";
import ReviewLine from "./ReviewLine";
import {clearList, getReviews, getSections} from "../../../actions/reviews/reviewListActions";
import Review from "./Review";
import {InfiniteList} from "../../common/InfiniteList";

export interface IReview {
    id: number,
    author: IEmployee,
    text: string,
    proposal: IProposal,
    score: string
}

class ReviewsList extends React.Component<any> {


    public constructor(props: {}) {
        super(props);

    }

    public getReviews = (page: number) => {

        return this.props.getReviews(page);

    };

    public rowRenderer = (props: any) => {
        const list = this.props.displayedMyReviews;
        const review: IReview = list[props.index].review;
        const section: ISection = list[props.index].section;

        return (
            <ReviewLine rowKey={props.key} style={props.style} review={review} section={section}/>
        )
    };

    public render() {

        return (
            <div>
                <Review review={this.props.review}/>
                <div className="App">
                    <h1>My Reviews</h1>
                </div>
                <br/>
                <InfiniteList displayItems={this.props.displayedMyReviews} total={this.props.total}
                              numberOfRowsReady={this.props.sectionsAdded}
                              rowRenderer={this.rowRenderer} loadMoreRows={this.getReviews}
                              clear={this.props.clearList}
                />
            </div>
        );
    }


}

const mapStateToProps = (state: any) => ({
    displayedMyReviews: state.reviewList.displayedMyReviews,
    total: state.reviewList.totalSize.total,
    sectionsAdded: state.reviewList.sectionsAdded,
    review: state.reviewModal.review

});

export default connect(mapStateToProps, {
    getReviews,
    getSections,
    clearList
})(ReviewsList)
