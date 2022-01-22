import React from 'react';
import './styles.css';

import icone from '../../assets/icone.svg'

import wlgrupo from '../../assets/WLgrupo.png'

export default function Login(){
    return(
        
        <div className="login-container">
            <section className="form">
                
                <img src={icone} alt="Erudio logo"/>
                <form>

                    <h1>Entrar no WLBreakfast</h1>
                    <input placeholder= "Username"/>
                    
                    <input id="cpf" type="text" placeholder= "CPF " pattern="[0-9]{11}"/>

                   
                    <button className='buttonlogin' type ="submit">Login</button>
                </form>

            </section>
            <img className='wl' src={wlgrupo} alt="Login"/>

        </div>

    )

    }
 
