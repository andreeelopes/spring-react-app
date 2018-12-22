import * as React from "react";
import {Link} from "react-router-dom";

import {connect} from "react-redux";
import {showModal} from "../../../actions/reviews/reviewModalAction";

export class ReviewLine extends React.Component<any> {
    public constructor(props: {}) {
        super(props);
    }

    public render() {
        return (
            <div
                key={this.props.rowKey}
                style={this.props.style}
            >
                <Link
                    to={'/proposals/' + this.props.review.proposal.id}>{(this.props.section) ? this.props.section.text : null}</Link>
                <span onClick={() => this.open(this.props.review)}className="review-popup glyphicon glyphicon-fullscreen" aria-hidden="true"/>
            </div>
        );
    }

    private open = (review: any) => {
        this.props.showModal(review);
    }
}

const mapStateToProps = (state: any) => null;
export default connect(mapStateToProps, {showModal})(ReviewLine);