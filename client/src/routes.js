import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

import Login from './pages/Login';
import Breaksat from './pages/Breakfast';
import NewFood from "./pages/NewFood";


export default function Routes (){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}/>
                <Route path="/breakfast" component={Breaksat}/>
                <Route path="/NewFood" component={NewFood}/>                    
            </Switch>
        </BrowserRouter>
    );
}