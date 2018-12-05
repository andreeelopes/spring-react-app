import * as React from "react";
import {connect} from "react-redux";
// import Company from "./Company";
import {fetchCompany} from "../../actions/companies/CompanyDetailsActions";
import Company from "./Company";

export class CompanyPage extends React.Component<any> {

    public componentWillMount() {
        const id = this.props.match.params.id;
        this.props.fetchCompany(id);
    }

    public render() {

        return (
            <div>
                <Company {...this.props.company}/>
                {/*<EmployeeList/>*/}
            </div>
        );
    }

}

const mapStateToProps = (state: any) => ({
    company: state.companyDetails.company
});

export default connect(mapStateToProps, {fetchCompany})(CompanyPage)
