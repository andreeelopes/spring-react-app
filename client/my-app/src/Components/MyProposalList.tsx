import axios from 'axios';
import * as React from "react";
import {Link} from "react-router-dom";
import {Index, IndexRange, InfiniteLoader, List} from 'react-virtualized';
import '../App.css';
import {IProposal, ISection} from "../utils/Components";





interface Istate {
    displayMyProposals:IProposalLine[]
}
interface IProposalLine {
    proposal:IProposal;
    section:ISection;

}
export class MyProposalList extends React.Component<{},Istate> {
    private total:number;
    private totalPage:number;
    private proposalLine:IProposalLine[];
    private currPage:number;

    public constructor(props: {}){
        super(props);
        this.state = { displayMyProposals: []};
        this.proposalLine=[];
        this.total=0;
        this.currPage=-1;
    }

    public getProposals = (param:IndexRange ) => {


        console.log(param.startIndex+" "+param.stopIndex);
        this.currPage++;

        return axios('http://localhost:8080/employees/6/partnerproposals?page='+this.currPage, {
            auth: {
                password: "password",
                username: "employee21"
            },
            method: 'get'
        }).then((json: any) => {
            if(this.totalPage===this.currPage) {
                return;
            }
            let proposalList:IProposal[] = [];

            proposalList = json.data.content;
            proposalList.map((c,i) => (this.getSections(c,json,i)));
            this.total=json.data.totalElements;
            this.totalPage=json.data.totalPages;
        });
    };

    public getSections = (c:IProposal,json:any,i:number) => {
        const endSize:number=this.proposalLine.length+json.data.numberOfElements;
        return axios('http://localhost:8080/proposals/'+c.id+'/sections/', {
            auth: {
                password: "password",
                username: "employee21"
            },
            method: 'get'
        }).then((sectionjson:any) => {
            let sectionList: ISection[];
            sectionList=sectionjson.data.content;
            sectionList.map((s) => {
                if(s.type="title") {
                    const propLine: IProposalLine = {section: s, proposal: c};
                    this.proposalLine.push(propLine);
                    if (endSize <= this.proposalLine.length) {
                        this.setState({displayMyProposals: this.proposalLine});
                    }
                }
            });
        });

    };


    public isRowLoaded = (index: Index) => {
        return !!this.state.displayMyProposals[index.index];
    };
    public rowRenderer = (props: any) => {

        const list = this.state.displayMyProposals;
        const proposal:IProposal=list[props.index].proposal;
        const section:ISection=list[props.index].section;
        const propId:number=list[props.index].proposal.id;
        return (
            <div
                key={props.key}
                style={props.style}
            >
                <Link to={'/proposal/'+propId}>{section.text}</Link> {proposal.status+" "+proposal.partnerCompany.name+" "+proposal.companyProposed.name}
            </div>
        )
    };
    public componentWillMount() {
        const param:IndexRange ={startIndex:0,stopIndex:19};
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
                    rowCount={this.total}
                    threshold={20}
                >
                    {({ onRowsRendered, registerChild }) => (
                        <List className="App-middle"
                              height={250}
                              onRowsRendered={onRowsRendered}
                              ref={registerChild}
                              rowCount={this.proposalLine.length}
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
