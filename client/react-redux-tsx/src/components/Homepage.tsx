import * as React from "react";
import {Button} from "react-bootstrap";

import ProposalList from "./proposals/ProposalList";
import BidsList from "./proposals/bids/BidsList";
import ReviewsList from "./proposals/reviews/ReviewsList"
import '../App.css';

import {connect} from "react-redux";
import {showModal} from "../actions/proposals/proposalModalActions";
import AddProposalModal from "./proposals/AddProposalModal";



import {doLogin} from "../actions/HomepageLoginAction";

class Homepage extends React.Component<any> {
    public constructor(props: {}) {
        super(props);
    }

    public handleOpen = () => {
        this.props.showModal();
    };

    public render() {
        console.log(this.props.login);
        if (this.props.login) {
            return (
                <div>
                    <ProposalList/>
                    <BidsList/>
                    <ReviewsList/>

                    <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Proposal</Button>
                    <AddProposalModal/>

                </div>
            );
        }
        else {
            return null;
        }
    }
}

const mapStateToProps = (state: any) => ({
    proposalModal: state.proposalModal.state,
    login: state.login.login
});

export default connect(mapStateToProps, {showModal, doLogin})(Homepage)