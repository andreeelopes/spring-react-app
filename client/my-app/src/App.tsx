import * as React from 'react';
import {BrowserRouter as Router, Route} from "react-router-dom";
import './App.css';
import Homepage from "./webPages/Homepage";
import {ProposalPage} from "./webPages/ProposalPage";
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
                            <Route exact={true} path="/proposal/:id" component={ProposalPage}/>
                        </div>
                    </Router>
                </div>
            </Provider>
        );
    }
}

export default App;