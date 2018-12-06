import * as React from "react";
import {Button, Modal} from 'react-bootstrap';
import {connect} from "react-redux";
import {hideModal,showModal} from "../../../actions/reviews/reviewModalAction";
import {Link} from "react-router-dom";

export class Review extends React.Component<any>{

    public render(){
        if(this.props.reviewModal){

        return (<div>
            <Modal show={this.props.reviewModal} onHide={this.handleClose}>
                <Modal.Header>
                    <Modal.Title>
                        <Link
                        to={'/employees/' + this.props.review.author.id}>{this.props.review.author.name}</Link>
                    </Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <h3>Score: {this.props.review.score}</h3>
                    Review:
                    <p>{this.props.review.text}</p>

                </Modal.Body>

                <Modal.Footer>
                    <Button onClick={this.handleClose}>Close</Button>
                </Modal.Footer>
            </Modal>
        </div>);
        }
        else{
            return null;
        }
    }
    private handleClose =()=>{
        this.props.hideModal();
    }
}
const mapStateToProps = (state: any) => ({
    reviewModal: state.reviewModal.state,
    review:state.reviewModal.review
});
export default connect(mapStateToProps, {showModal,hideModal})(Review);