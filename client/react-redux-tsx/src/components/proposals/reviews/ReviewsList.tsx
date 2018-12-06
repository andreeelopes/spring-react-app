import * as React from "react";
import {Index, IndexRange, InfiniteLoader, List} from "react-virtualized";
import {IEmployee, IProposal, ISection} from "../../../models/IComponents";
import {connect} from "react-redux";
import ReviewLine from "./ReviewLine";
import {getReviews, getSections} from "../../../actions/reviews/reviewListActions";
import Review from "./Review";

export interface IReview {
    id:number,
    author:IEmployee,
    text:string,
    proposal:IProposal,
    score:string
}

class ReviewsList extends React.Component<any> {

private currPage: number;
private table:any;
public constructor(props: {}) {
        super(props);

        this.currPage = -1;
    }
public getReviews = (param: IndexRange) => {
        this.currPage++;

        return this.props.getReviews(this.currPage);

    };

public  componentWillReceiveProps(nextProps:any) {

        if(this.props.sectionsAdded===19) { // updating
            this.table.scrollToPosition(2);
            this.table.scrollToPosition(0);
        }
    }

public isRowLoaded = (index: Index) => {
        return !!this.props.displayedMyReviews[index.index];
    };
public rowRenderer = (props: any) => {
        const list = this.props.displayedMyReviews;
        const review: IReview = list[props.index].review;
        const section: ISection = list[props.index].section;

        return (
            <ReviewLine rowKey={props.key} style={props.style} review={review} section={section}/>
        )
    };

public componentWillMount() {
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.getReviews(param);

    }

public render() {

        return (
            <div>
                <Review review={this.props.review}/>
                <div className="App">
                    <h1>My Reviews</h1>
                </div>
                <InfiniteLoader
                    isRowLoaded={this.isRowLoaded}
                    loadMoreRows={this.getReviews}
                    rowCount={this.props.total}
                    threshold={20}
                >
                    {({onRowsRendered, registerChild}) => (
                        <List className="App-middle"
                              height={250}
                              onRowsRendered={onRowsRendered}
                              ref={(ref)=>{this.table=ref; registerChild(ref)}}
                              rowCount={this.props.displayedMyReviews.length}
                              rowHeight={50}
                              rowRenderer={this.rowRenderer}
                              width={500}
                        />
                    )}
                </InfiniteLoader>
            </div>
        );
    }


}
    const mapStateToProps = (state: any) => ({
    displayedMyReviews: state.reviewList.displayedMyReviews,
    total: state.reviewList.totalSize.total,
    sectionsAdded: state.reviewList.sectionsAdded,
        review:state.reviewModal.review

});

    export default connect(mapStateToProps, {getReviews,
    getSections
})(ReviewsList)
