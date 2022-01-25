import React, {useState} from 'react';
import './styles.css';
import { useHistory } from "react-router-dom";

import api from '../../Service/api';

import icone from '../../assets/icone.svg'
import wlgrupo from '../../assets/WLgrupo.png';

export default function Login(){

const[username,setUsername] = useState('');
const[cpfpassword,setPassword] = useState('');

const history = useHistory();   

async function login(e) {
    e.preventDefault();

    const data = {
         username,
         password
}
    try {
        const response = await api.post('/auth/signin',data);
        localStorage.setItem('username', username);
        localStorage.setItem('acesseToken', response.data.token);

        history.push('/breakfast')
    } catch (erro) {
        alert('falha no login! tente novamente');
    }
};



    return(
        
        <div className="login-container">
            <section className="form">
                
                <img src={icone} alt="Erudio logo"/>
                <form onSubmit={login}>

                    <h1>Entrar no WLBreakfast</h1>
                    <input
                        placeholder= "Username"
                        valuer={username}
                        onChange={e =>setUsername(e.target.value)}   
                    />
                    <input
                        id="password" type="text" 
                        valuer={password}
                        onChang={e =>setPassword(e.target.value)}    
                    />

                    <button className='buttonlogin' type ="submit">Login</button>
                </form>

            </section>
            <img className='wl' src={wlgrupo} alt="Login"/>

        </div>

    )

    }
 
