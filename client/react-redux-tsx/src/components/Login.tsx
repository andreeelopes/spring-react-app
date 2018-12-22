import * as React from "react";
import {Button, Col, FormControl, Row} from "react-bootstrap";
import {connect} from "react-redux";
import {doLogin} from "../actions/HomepageLoginAction";
import {withRouter} from 'react-router'
import * as Grid from "react-bootstrap/lib/Grid";

export class Login extends React.Component<any> {
    private username: any;
    private password: any;

    public constructor(props: {}) {
        super(props);
    }

    public SubmitButton = withRouter(({history}) => (
        <Button className={"blue-button"} style={{marginTop: 10}}
            onClick={() => this.submit(history)}
        >
            Submit
        </Button>
    ));
    private submit = (history: any) => {
        this.props.doLogin(this.password.value, this.username.value, history);
    }

    public render() {
        return (
            <Grid>
                <Row>
                    <Col md={6} mdOffset={3}>
                        <label>Username</label>
                        <FormControl
                            componentClass="input"
                            inputRef={(ref) => {
                                this.username = ref
                            }}/>
                    </Col>
                </Row>
                <Row>
                    <Col md={6} mdOffset={3}>
                        <label>Password</label>
                        <FormControl
                            componentClass="input"
                            type="password"
                            inputRef={(ref) => {
                                this.password = ref
                            }}/>
                    </Col>
                </Row>
                <Row>
                    <Col md={6} mdOffset={3}>
                        <this.SubmitButton/>
                    </Col>
                </Row>
            </Grid>
        );
    }
}

export default connect(null, {doLogin})(Login)