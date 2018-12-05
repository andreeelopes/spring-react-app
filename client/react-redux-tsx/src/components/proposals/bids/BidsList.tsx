

import * as React from "react";
import {Index, IndexRange, InfiniteLoader, List} from 'react-virtualized';
import '../../../App.css';
import {connect} from "react-redux";
import {getBids, getSections, IBid} from "../../../actions/bidsListActions";
import {ISection} from "../../../models/IComponents";
import {BidLine} from "./BidLine";





class BidsList extends React.Component<any> {

    private currPage: number;
    private table:any;
    public constructor(props: {}) {
        super(props);

        this.currPage = -1;
    }
    public getBids = (param: IndexRange) => {
        this.currPage++;

        return this.props.getBids(this.currPage);

    };

    public  componentWillReceiveProps(nextProps:any) {

        if(this.props.sectionsAdded===19) { // updating
            this.table.scrollToPosition(2);
            this.table.scrollToPosition(0);
        }
    }

    public isRowLoaded = (index: Index) => {
        return !!this.props.displayedMyBids[index.index];
    };
    public rowRenderer = (props: any) => {
        const list = this.props.displayedMyBids;
        const bid: IBid = list[props.index].bid;
        const section: ISection = list[props.index].section;

        return (
            <BidLine rowKey={props.key} style={props.style} section={section} bid={bid}/>

        )
    };

    public componentWillMount() {
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.getBids(param);

    }

    public render() {

        return (
            <div>
                <div className="App">
                    <h1>Proposals I bidded</h1>
                </div>
                <InfiniteLoader
                    isRowLoaded={this.isRowLoaded}
                    loadMoreRows={this.getBids}
                    rowCount={this.props.total}
                    threshold={20}
                >
                    {({onRowsRendered, registerChild}) => (
                        <List className="App-middle"
                              height={250}
                              onRowsRendered={onRowsRendered}
                              ref={(ref)=>{this.table=ref; registerChild(ref)}}
                              rowCount={this.props.displayedMyBids.length}
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
    displayedMyBids: state.bidList.displayedMyBids,
    total: state.bidList.totalSize.total,
    sectionsAdded: state.bidList.sectionsAdded
});

export default connect(mapStateToProps, {getBids,
    getSections
})(BidsList)
