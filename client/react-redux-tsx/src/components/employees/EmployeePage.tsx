import * as React from "react";
import {connect} from "react-redux";

import {fetchEmployee} from "../../actions/employees/employeeDetailsAction";
import {Link} from "react-router-dom";
import {IEmployee} from "../../models/IComponents";
import * as Grid from "react-bootstrap/lib/Grid";
import {Col, Row} from "react-bootstrap";


export class EmployeePage extends React.Component<any> {
    public componentWillMount() {
        this.props.fetchEmployee(this.props.match.params.id);
    }

    public render() {
        const employee: IEmployee = this.props.employee;

        if (employee) {
            return (
                <Grid>
                    <Row>
                        <Col md={12}>
                            <div>
                                <h1> {employee.name} </h1>
                                <p><label>Email: </label> {employee.email}</p>
                                <p><label>Username: </label> {employee.username}</p>
                                <p><label>Job: </label> {employee.job}</p>
                                <p><label>Company: </label>
                                    <Link to={`/companies/${employee.company.id}`}> {employee.company.name} </Link>
                                </p>
                            </div>
                        </Col>
                    </Row>
                </Grid>
            )
        }

        return null;
    }
}

const mapStateToProps = (state: any) => ({
    employee: state.employeeDetails.employee,
});

export default connect(mapStateToProps, {fetchEmployee})(EmployeePage);