import * as React from 'react';
import {BrowserRouter as Router, Route} from "react-router-dom";
import './App.css';
import Homepage from "./components/Homepage";
import ProposalPage from "./components/proposals/ProposalPage";
import CompanyPage from "./components/companies/CompanyPage";
import {Provider} from 'react-redux';


import store from './store'
import NavBar from "./components/common/NavBar";
import EmployeePage from "./components/employees/EmployeePage";
import Login from "./components/Login";

class App extends React.Component {
    public render() {
        return (
            <Provider store={store}>
                <div>
                    <Router>
                        <div>
                            <NavBar/>
                            <Route exact={true} path="/" component={Login}/>
                            <Route exact={true} path="/homepage" component={Homepage}/>
                            <Route exact={true} path="/proposals/:id" component={ProposalPage}/>
                            <Route exact={true} path="/companies/:id" component={CompanyPage}/>
                            <Route exact={true} path="/employees/:id" component={EmployeePage}/>
                        </div>
                    </Router>
                </div>
            </Provider>
        );
    }
}

export default App;
