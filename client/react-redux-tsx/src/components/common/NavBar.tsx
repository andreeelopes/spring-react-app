import {Nav, Navbar, NavItem} from 'react-bootstrap';
import * as React from 'react';
import {IUser} from "../../models/IComponents";
import {connect} from "react-redux";
import {doLogin} from "../../actions/HomepageLoginAction";
import {Link} from "react-router-dom";

class NavBar extends React.Component<any> {
    private user: IUser = {
        "id": 6,
        "username": "employee21",
        "name": "Employee 1 Company 2",
        "email": "employee21@",
        "job": "normal",
        "company": {"id": 2, "name": "company2", "address": "rua idk", "email": "company2@"},
        "admin": false
    };

    public componentWillMount() {
        this.props.doLogin();
        sessionStorage.setItem('myData', JSON.stringify(this.user));
    }

    public render() {
        return (
            <Navbar>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Link to="/">ECMA</Link>
                    </Navbar.Brand>
                </Navbar.Header>
                <Nav>
                    <NavItem eventKey={1} href="/employees/6">
                        <Link to={"/employees/" + this.user.id}>My profile</Link>
                    </NavItem>
                    <NavItem eventKey={2} href="#">
                        <Link to={"/companies/" + this.user.company.id}>My Company</Link>
                    </NavItem>

                </Nav>
            </Navbar>
        );
    }
}

export default connect(null, {doLogin})(NavBar)