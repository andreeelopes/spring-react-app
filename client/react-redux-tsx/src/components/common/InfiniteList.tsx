import {Index, IndexRange, InfiniteLoader, List} from "react-virtualized";
import * as React from "react";

export class InfiniteList extends React.Component<any> {

    private currPage: number;
    private table:any;
    public constructor(props: {}) {
        super(props);

        this.currPage = -1;
    }
    public loadMoreRows = (param: IndexRange) => {
        this.currPage++;

        return this.props.loadMoreRows(this.currPage);

    };

    public  componentWillReceiveProps(nextProps:any) {
        if(this.props.numberOfRowsReady===19) { // updating
            this.table.scrollToPosition(2);
            this.table.scrollToPosition(0);
        }
    }

    public isRowLoaded = (index: Index) => {
        return !!this.props.displayItems[index.index];
    };

    public componentWillMount() {
        const param: IndexRange = {startIndex: 0, stopIndex: 19};
        this.loadMoreRows(param);

    }

    public render() {

        return (
            <div>

                <InfiniteLoader
                    isRowLoaded={this.isRowLoaded}
                    loadMoreRows={this.loadMoreRows}
                    rowCount={this.props.total}
                    threshold={20}
                >
                    {({onRowsRendered, registerChild}) => (
                        <List className="App-middle"
                              height={250}
                              onRowsRendered={onRowsRendered}
                              ref={(ref)=>{this.table=ref; registerChild(ref)}}
                              rowCount={this.props.displayItems.length}
                              rowHeight={50}
                              rowRenderer={this.rowRenderer}
                              width={500}
                        />
                    )}
                </InfiniteLoader>
            </div>
        );
    }


    private rowRenderer =(props:any)=> {
        return this.props.rowRenderer(props);
    }
}
