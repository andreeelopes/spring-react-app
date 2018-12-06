import {Link} from "react-router-dom";
import * as React from "react";
import {IBid} from "../../../actions/bids/bidsListActions";
import {ISection} from "../../../models/IComponents";


interface IProps {
    rowKey:any,
    style:any,
    bid:IBid
    section:ISection
}
export class BidLine extends React.Component<IProps>{
    public render(){
        return(
            <div
                key={this.props.rowKey}
        style={this.props.style}
    >
        <Link
            to={'/proposals/' + this.props.bid.pk.proposal.id}>{(this.props.section)? this.props.section.text:null}</Link> {this.props.bid.status}
        </div>
    );
    }
}