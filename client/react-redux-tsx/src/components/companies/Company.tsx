import * as React from "react";
import {connect} from "react-redux";

class Company extends React.Component<any> {

    public constructor(props: any) {
        super(props);
    }

    public render() {
        console.log(this.props);
        return (
            <div>
                {/*{this.props}*/}
            </div>
        );
    }

}


const mapStateToProps = () => ({});

export default connect(mapStateToProps)(Company)