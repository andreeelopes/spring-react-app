import * as React from "react";
import {Button} from "react-bootstrap";
import {MyProposalList} from "../proposals/MyProposalList";
import {MyBidList} from "../bids/MyBidList";
import '../../App.css';
import {connect} from "react-redux";
import {showModal} from "../../actions/proposalModalActions";
import AddProposalModal from "../proposals/AddProposalModal";


class Homepage extends React.Component<any> {

    public componentWillMount() {
        const userInfo = {
            "id": 6,
            "username": "employee21",
            "name": "Employee 1 Company 2",
            "email": "employee21@",
            "job": "normal",
            "company": {"id": 2, "name": "company2", "address": "rua idk", "email": "company2@"},
            "admin": false
        };
        sessionStorage.setItem('myData', JSON.stringify(userInfo));
    }


    public handleOpen = () => {
        this.props.showModal();
    };

    public render() {
        return (
            <div>
                <MyProposalList/>
                <MyBidList/>

                <Button className="App-middle" bsStyle="success" onClick={this.handleOpen}>Add Proposal</Button>
                <AddProposalModal/>

            </div>
        );
    }
}

const mapStateToProps = (state: any) => ({
    proposalModal: state.proposalModal.state
});

export default connect(mapStateToProps, {showModal})(Homepage)