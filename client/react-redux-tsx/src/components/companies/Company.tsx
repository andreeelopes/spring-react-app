import * as React from "react";
import {connect} from "react-redux";

export class Company extends React.Component<any> {


    public render() {

        return (
            <div>
                lala
            </div>
        );
    }

}


const mapStateToProps = (state: any) => ({
    user: state.user.user
});

export default connect(mapStateToProps)(Company)