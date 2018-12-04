import axios from 'axios';
import * as React from "react";
import {Index, IndexRange, InfiniteLoader, List} from 'react-virtualized';
import '../../App.css';
import {IEmployee, IProposal, ISection} from "../../models/IComponents";


interface Istate {
    displayMyReviews: string[]
}

interface IBidPK {
    bidder: IEmployee;
    proposal: IProposal;
}

interface IBid {
    pk: IBidPK
    status: string;
}

export class MyBidList extends React.Component<{}, Istate> {
    private total: number;
    private totalPage: number;
    private ReviewLine: string[];
    private currPage: number;

    public constructor(props: {}) {
        super(props);
        this.state = {displayMyReviews: []};
        this.ReviewLine = [];
        this.total = 0;
        this.currPage = -1;
    }

    public getReviews = (param: IndexRange) => {


        this.currPage++;

        return axios('http://localhost:8080/employees/6/bids?page=' + this.currPage, {
            auth: {
                password: "password",
                username: "employee21"
            },
            method: 'get'
        }).then((json: any) => {
            if (this.totalPage === this.currPage) {
                return;
            }
            let bidList: IBid[] = [];

            bidList = json.data.content;
            bidList.map((c, i) => (this.getSections(c, json, i)));
            this.total = json.data.totalElements;
            this.totalPage = json.data.totalPages;

        });
    };

    public getSections = (c: IBid, json: any, i: number) => {
        const endSize: number = this.ReviewLine.length + json.data.numberOfElements;
        return axios('http://localhost:8080/proposals/' + c.pk.proposal.id + '/sections/', {
            auth: {
                password: "password",
                username: "employee21"
            },
            method: 'get'
        }).then((sectionjson: any) => {
            let sectionList: ISection[];
            sectionList = sectionjson.data.content;
            sectionList.map((s, ind) => {
                if (s.type = "title") {
                    const bidLine: string = s.text + " " + c.status;
                    this.ReviewLine.push(bidLine);
                    if (endSize <= this.ReviewLine.length) {
                        this.setState({displayMyReviews: this.ReviewLine});
                    }
                }
            });


        });

    };


    public isRowLoaded = (index: Index) => {
        return !!this.state.displayMyReviews[index.index];
    };
    public rowRenderer = (props: any) => {

        const list = this.state.displayMyReviews;
        return (
            <div
                key={props.key}
                style={props.style}
            >
                {list[props.index]}
            </div>
        )
    };

    public componentWillMount() {
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.getReviews(param);
    }

    public render() {

        return (
            <div>
                <div className="App">
                    <h1>MyBids</h1>
                </div>
                <InfiniteLoader
                    isRowLoaded={this.isRowLoaded}
                    loadMoreRows={this.getReviews}
                    rowCount={this.total}
                >
                    {({onRowsRendered, registerChild}) => (
                        <List className="App-middle"
                              height={250}
                              onRowsRendered={onRowsRendered}
                              ref={registerChild}
                              rowCount={this.state.displayMyReviews.length}
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
