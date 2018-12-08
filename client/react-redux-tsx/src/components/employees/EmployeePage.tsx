import * as React from "react";
import {connect} from "react-redux";

import {fetchEmployee} from "../../actions/employees/employeeDetailsAction";



export class EmployeePage extends React.Component<any> {
    public componentWillMount(){
        this.props.fetchEmployee(this.props.match.params.id);

    }
    public render() {
        const employee=this.props.employee;
        if(this.props.employee) {
            return (
                <div>
                    <h1> {employee.name} </h1>
                    <p> Email: {employee.email}</p>
                    <p> Username: {employee.username}</p>
                    <p> Job: {employee.job}</p>
                </div>
            )
        }
        else{
            return null;
        }
    }
}
const mapStateToProps = (state: any) =>
    ({
        employee: state.employeeDetails.employee,
    });
export default connect(mapStateToProps, {
    fetchEmployee
})(EmployeePage);