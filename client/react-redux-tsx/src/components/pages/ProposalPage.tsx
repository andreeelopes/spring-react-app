import * as React from "react";


export class ProposalPage extends React.Component<any> {

    public constructor(props: any) {
        super(props);
    }

    public render() {
        const {match} = this.props
        const id = match.params.id
        console.log(id);
        return (
            <div>
                {id}
            </div>
        );
    }
}