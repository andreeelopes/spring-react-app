import * as React from "react";
import {Link} from "react-router-dom";
import {IProposal, ISection} from "../../models/IComponents";

interface IProps {
    rowKey: any,
    style: any,
    proposal: IProposal,
    section: ISection
}

export class ProposalListItem extends React.Component<IProps> {
    public render() {
        return (
            <div
                key={this.props.rowKey}
                style={this.props.style}
            >
                <Link
                    to={'/proposals/' + this.props.proposal.id}>{(this.props.section) ? this.props.section.text : null}</Link> { this.props.proposal.partnerCompany.name + " " + this.props.proposal.companyProposed.name}
            </div>
        );
    }
}