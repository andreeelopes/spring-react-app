import * as React from 'react';
import {BrowserRouter as Router, Route} from "react-router-dom";
import './App.css';
import Homepage from "./components/Homepage";
import {ProposalPage} from "./components/proposals/ProposalPage";
import CompanyPage from "./components/companies/CompanyPage";
import {Provider} from 'react-redux';


import store from './store'

class App extends React.Component {
    public render() {
        return (
            <Provider store={store}>
                <div>
                    <Router>
                        <div>
                            <Route exact={true} path="/" component={Homepage}/>
                            <Route exact={true} path="/proposals/:id" component={ProposalPage}/>
                            <Route exact={true} path="/companies/:id" component={CompanyPage}/>
                        </div>
                    </Router>
                </div>
            </Provider>
        );
    }
}

export default App;
