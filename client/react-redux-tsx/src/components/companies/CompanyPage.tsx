import * as React from "react";
import {connect} from "react-redux";
import {Company} from "./Company";


export class CompanyPage extends React.Component<any> {

    public render() {
        // const {match} = this.props
        // const id = match.params.id
        // console.log(id);

        return (
            <div>
                <Company/>
                {/*<EmployeeList/>*/}
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    // user: state.user
});

export default connect(mapStateToProps)(CompanyPage)
