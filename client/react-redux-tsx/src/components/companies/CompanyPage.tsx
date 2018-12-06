import * as React from "react";
import {connect} from "react-redux";
import {fetchCompany, fetchEmployeesOfCompany} from "../../actions/companies/CompanyDetailsActions";
import Company from "./CompanyDetails";
import SimpleList from "../common/SimpleList";
import {IEmployee} from "../../models/IComponents";

export class CompanyPage extends React.Component<any> {

    public componentWillMount() {
        const id = this.props.match.params.id;
        this.props.fetchCompany(id);
        this.props.fetchEmployeesOfCompany(id);
    }

    public render() {

        console.log(this.props);
        if (this.props.employees != null){

            return (
                <div>
                    <Company {...this.props.company}/>
                    <SimpleList<IEmployee> title="Employees"
                                           list={this.props.employees}
                                           show={this.employeesShow}
                    />
                </div>
            );
        }
        return null;
    }

    private employeesShow = (employee: IEmployee) => `${employee.name} (${employee.email})`; // TODO remove from here

}

const mapStateToProps = (state: any) => ({
    company: state.companyDetails.company,
    employees: state.companyDetails.employees
});

export default connect(mapStateToProps, {fetchCompany, fetchEmployeesOfCompany})(CompanyPage)
