import {Button, Nav, Navbar, NavItem} from 'react-bootstrap';
import * as React from 'react';
import {IUser} from "../../models/IComponents";
import {connect} from "react-redux";
import {doLogin} from "../../actions/HomepageLoginAction";
import {Link, withRouter} from "react-router-dom";
import {getUser} from "../../actions/getSessionUser";

class NavBar extends React.Component<any> {
    private user: IUser = getUser();

    public render() {

        return (
            <Navbar>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Link to="/homepage">ECMA</Link>
                    </Navbar.Brand>
                </Navbar.Header>
                <Nav>
                    <NavItem eventKey={1} href="#">
                        <Link to={"/employees/" + this.user.id}>My profile</Link>
                    </NavItem>
                    <NavItem eventKey={2} href="#">
                        <Link to={"/companies/" + this.user.company.id}>My Company</Link>
                    </NavItem>


                </Nav>
                <Navbar.Form pullRight={true}>
                    <this.LogoutButton/>
                </Navbar.Form>
                <Navbar.Text pullRight={true}> Welcome, {this.user.username} </Navbar.Text>
            </Navbar>
        );
    }

    public LogoutButton = withRouter(({history}) => (
        <Button className={"blue-button"}
                onClick={() => history.push("/login")}
        >
            Logout
        </Button>
    ));
}



export default connect(null, {doLogin})(NavBar)