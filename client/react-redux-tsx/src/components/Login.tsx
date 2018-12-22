import * as React from "react";
import {Button, FormControl} from "react-bootstrap";
import {connect} from "react-redux";
import {doLogin} from "../actions/HomepageLoginAction";
import { withRouter} from 'react-router'
export class Login extends React.Component<any> {
    private username:any;
    private password:any;

    public constructor(props: {}) {
        super(props);
    }

    public SubmitButton = withRouter(({history}) => (
        <Button
            onClick={() => this.submit(history)}
        >
            Submit
        </Button>
    ));
  private submit = (history:any) => {
      this.props.doLogin(this.password.value,this.username.value,history);
  }
    public render() {
        return (
            <div>
                <FormControl
                    componentClass="textarea"
                    placeholder="Enter username"
                    inputRef={(ref) => {
                        this.username = ref
                    }}/>
                <FormControl
                    componentClass="textarea"
                    placeholder="password"
                    inputRef={(ref) => {
                        this.password = ref
                    }}/>
                <this.SubmitButton/>
            </div>
        );
    }
}
export default connect(null, {doLogin})(Login)