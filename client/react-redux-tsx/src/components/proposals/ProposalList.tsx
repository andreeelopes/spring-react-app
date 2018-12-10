import * as React from "react";
import {Index, IndexRange, InfiniteLoader, List} from 'react-virtualized';
import '../../App.css';
import {IProposal, ISection} from "../../models/IComponents";
import {connect} from "react-redux";
import {ProposalListItem} from "./ProposalListItem";
import {clearList, getProposals, getSections} from "../../actions/proposals/proposalsListActions";
import {Dropdown, DropdownButton, MenuItem} from "react-bootstrap";

const getAllProposalsLink = "http://localhost:8080/employees/6/partnerproposals?page=";
const getStaffProposalsLink = "http://localhost:8080/employees/6/staffproposals?page=";


export class ProposalList extends React.Component<any> {

    private currPage: number;
    private table: any;
    private listType: number;
    private dropdownTitle: string;

    public constructor(props: {}) {
        super(props);
        this.listType = 1;
        this.currPage = -1;
        this.dropdownTitle = "All proposals"
    }

    public getProposals = (param: IndexRange) => {
        this.currPage++;
        if (this.listType === 1) {
            return this.props.getProposals(this.currPage, getAllProposalsLink);
        }
        else if (this.listType === 2) {
            return this.props.getProposals(this.currPage, getStaffProposalsLink);
        }
    };

    public componentWillReceiveProps(nextProps: any) {
        console.log(this.props.sectionsAdded);
        if (this.props.sectionsAdded === 19 || (this.props.total-1 === this.props.sectionsAdded)) {
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
            <ProposalListItem rowKey={props.key} style={props.style} proposal={proposal} section={section}/>
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
                <div className="App">

                    <Dropdown id="dropdown-custom-menu">
                        <DropdownButton
                            bsStyle={"primary"}
                            title={this.dropdownTitle}
                            key={0}
                            id={`dropdown-basic-${0}`}
                        >

                            <MenuItem eventKey="1" onSelect={this.getAllProposals}>All proposals</MenuItem>
                            <MenuItem eventKey="2" onSelect={this.getStaffProposals}>Proposals where im staff</MenuItem>
                        </DropdownButton>
                    </Dropdown>
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
                              ref={(ref) => {
                                  this.table = ref;
                                  registerChild(ref)
                              }}
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


    private getAllProposals = () => {
        this.listType = 1;
        this.props.clearList();
        this.currPage = -1;
        this.dropdownTitle = "All proposals";
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.getProposals(param);
    }

    private getStaffProposals = () => {
        this.listType = 2;
        this.props.clearList();
        this.currPage = -1;
        this.dropdownTitle = "Staff proposals";
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.getProposals(param);
    }
}

const mapStateToProps = (state: any) => ({
    displayMyProposals: state.proposalList.displayMyProposals,
    total: state.proposalList.totalSize.total,
    sectionsAdded: state.proposalList.sectionsAdded
});

export default connect(mapStateToProps, {
    getSections, clearList,
    getProposals
})(ProposalList)
