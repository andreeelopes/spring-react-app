import * as React from "react";
import {ICompany} from "../../models/IComponents";

export class CompanyDetails extends React.Component<ICompany> {

    public constructor(props: ICompany) {
        super(props);
    }

    public render() {
        const company: ICompany = this.props;

        return (
            <div>
                <h1>{company.name}</h1>
                <p>
                    <label>Email: </label> {company.email}
                </p>
                <p>
                    <label>Adress: </label> {company.address}
                </p>
            </div>
        );
    }
}
