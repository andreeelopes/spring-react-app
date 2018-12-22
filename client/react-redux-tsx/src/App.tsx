import * as React from 'react';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import './App.css';
import Homepage from "./components/Homepage";
import ProposalPage from "./components/proposals/ProposalPage";
import CompanyPage from "./components/companies/CompanyPage";
import {Provider} from 'react-redux';


import store from './store'
import EmployeePage from "./components/employees/EmployeePage";
import Login from "./components/Login";
import NavBar from "./components/common/NavBar";

class App extends React.Component {

    public render() {
        return (
            <Provider store={store}>
                <div>
                    <BrowserRouter>
                        <Switch>
                            <Route exact={true} path="/" render={()=> {console.log("redireting"); return <Redirect to="/login"/>}}/>
                            <Route path="/login" component={Login}/>
                            <Route component={defaultContainer}/>
                        </Switch>
                    </BrowserRouter>
                </div>
            </Provider>
        );
    }
}

const defaultContainer = () =>(
    <div>
        <NavBar/>
        <Route path="/homepage" component={Homepage}/>
        <Route path="/proposals/:id" component={ProposalPage}/>
        <Route path="/companies/:id" component={CompanyPage}/>
        <Route path="/employees/:id" component={EmployeePage}/>
    </div>
)
export default App;
