import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from './pages/Login';
import Breaksat from './pages/Breakfast';

export default function Rotas(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/welcome" exact component={Login}/>
                <Route path="/" component={Breaksat}/>
            </Routes>
        </BrowserRouter>
    )
}