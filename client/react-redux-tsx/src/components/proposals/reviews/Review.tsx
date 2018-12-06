import * as React from "react";
import {Button, Modal} from 'react-bootstrap';
import {connect} from "react-redux";
import {hideModal,showModal} from "../../../actions/reviews/reviewModalAction";

export class Review extends React.Component<any>{

    public render(){
        if(this.props.reviewModal){
        console.log(this.props.review);

        return (<div>
            <Modal show={this.props.reviewModal} onHide={this.handleClose}>
                <Modal.Header>
                    <Modal.Title>{this.props.review.author.name}</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    {this.props.review.text}

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