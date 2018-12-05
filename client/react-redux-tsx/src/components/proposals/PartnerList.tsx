import * as React from "react";
import {IEmployee} from "../../models/IComponents";


interface IProps {
    partners: IEmployee[]
}

export class PartnerList extends React.Component<any> {
    constructor(props: IProps) {
        super(props)
    }

    public render() {
        const {partners} = this.props;
        if (partners.length > 0) {
            return (
                <ul>
                    {
                        partners.map((partner: IEmployee) => (
                            <li key={partner.id}>
                                {partner.name}
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
