import * as React from "react";
import {IEmployee} from "../../models/IComponents";


interface IProps {
    partners: IEmployee[]
}

export class EmployeeList extends React.Component<any> {
    constructor(props: IProps) {
        super(props)
    }

    public render() {
        const {employees} = this.props;
        if (employees.length > 0) {
            return (
                <ul>
                    {
                        employees.map((employee: IEmployee) => (
                            <li key={employee.id}>
                                {employee.name}
                            </li>)
                        )
                    }
                </ul>
            )
        }
        else {
            return null;
        }
    }
}
