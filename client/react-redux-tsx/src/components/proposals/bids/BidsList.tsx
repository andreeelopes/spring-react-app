import * as React from "react";
import '../../../App.css';
import {connect} from "react-redux";
import {getBidsAndSections, getSections, IBid} from "../../../actions/bids/bidsListActions";
import {ISection} from "../../../models/IComponents";
import {BidLine} from "./BidLine";
import {InfiniteList} from "../../common/InfiniteList";


class BidsList extends React.Component<any> {

    public getBids = (page: number) => {

        return this.props.getBids(page);

    };


    public rowRenderer = (props: any) => {
        const list = this.props.displayedMyBids;
        const bid: IBid = list[props.index].bid;
        const section: ISection = list[props.index].section;

        return (
            <BidLine rowKey={props.key} style={props.style} section={section} bid={bid}/>

        )
    };


    public render() {

        return (
            <div>
                <div className="App">
                    <h1>Proposals I bidded</h1>
                </div>
                <InfiniteList displayItems={this.props.displayedMyBids} total={this.props.total}
                              numberOfRowsReady={this.props.sectionsAdded}
                              rowRenderer={this.rowRenderer} loadMoreRows={this.getBids}/>
            </div>
        );
    }


}

const mapStateToProps = (state: any) => ({
    displayedMyBids: state.bidList.displayedMyBids,
    total: state.bidList.totalSize.total,
    sectionsAdded: state.bidList.sectionsAdded
});

export default connect(mapStateToProps, {
    getBids: getBidsAndSections,
    getSections
})(BidsList)
