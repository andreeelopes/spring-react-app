import * as React from "react";
import {connect} from "react-redux";
import {fetchCompany, fetchEmployeesOfCompany} from "../../actions/companies/companyDetailsActions";
import SimpleList from "../common/SimpleList";
import {IEmployee} from "../../models/IComponents";
import {CompanyDetails} from "./CompanyDetails";
import {Link} from "react-router-dom";
import * as Grid from "react-bootstrap/lib/Grid";
import {Col, Row} from "react-bootstrap";

export class CompanyPage extends React.Component<any> {

    public componentWillMount() {
        const id = this.props.match.params.id;
        this.props.fetchCompany(id);
        this.props.fetchEmployeesOfCompany(id);
    }

    public render() {

        if (this.props.company && this.props.employees) {
            return (

                <Grid>
                    <Row>
                        <Col md={12}>

                            <div>
                                <CompanyDetails {...this.props.company}/>
                                <SimpleList<IEmployee> title="Employees"
                                                       list={this.props.employees}
                                                       show={this.employeesShow}
                                />
                            </div>
                        </Col>
                    </Row>
                </Grid>
            );

        }
        return null;
    }

    private employeesShow = (employee: IEmployee) => (
        <div>
            <Link to={`/employees/${employee.id}`}> {employee.name} </Link>
            {employee.email} {employee.job}
        </div>
    );

}

const mapStateToProps = (state: any) => ({
    company: state.companyDetails.company,
    employees: state.companyDetails.employees
});

export default connect(mapStateToProps, {fetchCompany, fetchEmployeesOfCompany})(CompanyPage)
