

import * as React from "react";
import {Index, IndexRange, InfiniteLoader, List} from 'react-virtualized';
import '../../App.css';
import {IProposal, ISection} from "../../models/IComponents";
import {connect} from "react-redux";
import {getProposals, getSections} from "../../actions/proposalsListActions";
import {ProposalLine} from "./ProposalLine";




class MyProposalList extends React.Component<any> {
    private currPage: number;
    private table:any;
    public constructor(props: {}) {
        super(props);

        this.currPage = -1;
    }
    public getProposals = (param: IndexRange) => {
        this.currPage++;

        return this.props.getProposals(this.currPage);

    };

    public  componentWillReceiveProps(nextProps:any) {

        if(this.props.sectionsAdded===19) { // updating
            this.table.scrollToPosition(2);
            this.table.scrollToPosition(0);
        }
    }

    public isRowLoaded = (index: Index) => {
        return !!this.props.displayMyProposals[index.index];
    };
    public rowRenderer = (props: any) => {

        const list = this.props.displayMyProposals;
        const proposal: IProposal = list[props.index].proposal;
        const section: ISection = list[props.index].section;

        return (
            <ProposalLine rowKey={props.key} style={props.style} proposal={proposal} section={section}/>
        )
    };

    public componentWillMount() {
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.getProposals(param);

    }

    public render() {

        return (
            <div>
                <div className="App">
                    <h1>MyProposals</h1>
                </div>
                <InfiniteLoader
                    isRowLoaded={this.isRowLoaded}
                    loadMoreRows={this.getProposals}
                    rowCount={this.props.total}
                    threshold={20}
                >
                    {({onRowsRendered, registerChild}) => (
                        <List className="App-middle"
                              height={250}
                              onRowsRendered={onRowsRendered}
                              ref={(ref)=>{this.table=ref; registerChild(ref)}}
                              rowCount={this.props.displayMyProposals.length}
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
    displayMyProposals: state.proposalList.displayMyProposals,
    total: state.proposalList.totalSize.total,
    sectionsAdded: state.proposalList.sectionsAdded
});

export default connect(mapStateToProps, {getSections,
    getProposals
})(MyProposalList)
