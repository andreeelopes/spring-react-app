import * as React from "react";
import {connect} from "react-redux";
import {ICompany} from "../../models/IComponents";

class CompanyDetails extends React.Component<any> {

    public constructor(props: ICompany) {
        super(props);
    }

    public render() {

        return (
            <div>
                <h1>{this.props.name}</h1>
                <p>
                    <label>Email: </label> {this.props.email}
                </p>
                <p>
                    <label>Adress: </label> {this.props.address}
                </p>
            </div>
        );
    }

}


const mapStateToProps = () => ({});

export default connect(mapStateToProps)(CompanyDetails)