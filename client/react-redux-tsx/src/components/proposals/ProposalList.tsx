import * as React from "react";
import {Index, IndexRange, InfiniteLoader, List} from 'react-virtualized';
import '../../App.css';
import {IProposal, ISection} from "../../models/IComponents";
import {connect} from "react-redux";
import {ProposalListItem} from "./ProposalListItem";
import {clearList, getProposals, getSections} from "../../actions/proposals/proposalsListActions";
import {Dropdown, DropdownButton, MenuItem} from "react-bootstrap";
import {getUser} from "../../actions/getSessionUser";

const getAllProposalsLink = "http://localhost:8080/employees/{pid}/partnerproposals?page=";
const getStaffProposalsLink = "http://localhost:8080/employees/{pid}/staffproposals?page=";
const getApproverProposalsLink="http://localhost:8080/employees/{pid}/approver?page=";

export class ProposalList extends React.Component<any> {

    private currPage: number;
    private table: any;
    private listType: number;
    private dropdownTitle: string;

    public constructor(props: {}) {
        super(props);
        this.listType = 1;
        this.currPage = -1;
        this.dropdownTitle = "Partner proposals"
    }

    public getProposals = (param: IndexRange) => {
        this.currPage++;
        const user = getUser();
        if (this.listType === 1) {
            return this.props.getProposals(this.currPage, getAllProposalsLink.replace("{pid}", user.id));
        }
        else if (this.listType === 2) {
            return this.props.getProposals(this.currPage, getStaffProposalsLink.replace("{pid}", user.id));
        }
        else if (this.listType === 3) {
            return this.props.getProposals(this.currPage, getApproverProposalsLink.replace("{pid}", user.id));
        }
    };

    public componentWillReceiveProps(nextProps: any) {
        if (this.props.sectionsAdded === 19 || (this.props.total - 1 === this.props.sectionsAdded)) {
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
        this.props.clearList();
        this.getProposals(param);

    }

    public render() {

        return (
            <div>
                <div className="App">
                    <h1>Proposals</h1>
                </div>
                <div className="App">

                    <Dropdown id="dropdown-custom-menu">
                        <DropdownButton className={"blue-button"}
                                        bsStyle={"primary"}
                                        title={this.dropdownTitle}
                                        key={0}
                                        id={`dropdown-basic-${0}`}
                        >

                            <MenuItem eventKey="1" onSelect={this.getAllProposals}>Partner proposals</MenuItem>
                            <MenuItem eventKey="2" onSelect={this.getStaffProposals}>Staff proposals</MenuItem>
                            <MenuItem eventKey="3" onSelect={this.getApproverProposals}>Approver proposals</MenuItem>
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
                              width={300}
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
        this.dropdownTitle = "Partner proposals";
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
    private getApproverProposals= () =>{
        this.listType = 3;
        this.props.clearList();
        this.currPage = -1;
        this.dropdownTitle = "Approver proposals";
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
