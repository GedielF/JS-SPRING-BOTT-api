import React,{useState, useEffect} from "react";
import {Link,useHistory } from 'react-router-dom';
import {FiPower, FiEdit, FiTrash2} from 'react-icons/fi'

import icone from '../../assets/icone.svg'

import './styles.css';
import api from "../../Service/api";
 

export default function Breakfast() {

    const[breakfast, setBreakfast]= useState([ ]);

    const username = localStorage.getItem('username');
    const acesseToken = localStorage.getItem('acesseToken');

    const history=useHistory();

    useEffect(()=>{
        if(bookfastId ==='0') return;
        else{
            loadBreakfast();
            try {
                const response =await api.get(`api/breakfast/v1/${breakfastId}`,{
                    headers:{
                        Authorization: `Bearer ${accessToken}`
                    }
                })
                let adjustedDate= response.data.dataatual.split("T",10)[0];

                setId(response.data.id)
                setNome(response.data.nome)
                setCpf(response.data.cpf)
                setComida(response.data.comida)
                setBebida(response.data.bebida)
                setDataatual(adjustedDate)
            } catch (error) {
                alert ('erro na gravaçao da lista')
                history.push('/breakfast');
            }
        }

    }),[breakfastId]

    async function logout(){
        localStorage.clear();
        history.push('/'); 
    }
    async function deleteBreakfast(id){
        try {
            await api.delete(`/api/book/v1/${id}`,{
                headers:{
                    Authorization: `Bearer ${accessToken}`
                }
            })

            setBreakfast(breakfast.filter(breakfast=> breakfast.id !== id))
        } catch (err) {
            alert ('Delete failuid')
        }
    }
    useEffect(()=>{
        api.get('/api/book/v1',{
                Headers:{
                    Authorization:`Bearer ${acesseToken}`
                },
                params:{
                    page:1,
                    limit:4,
                    direction: 'asc'

                }
        }).then(Response.data.embedded.breakfastVoes)
    });


    return (
        <div className="Breakfast-container">
            <header>
                <img src={icone} alt="Erudio"/>
                <span> Welcome, <strong>{username,toUpperCase()}</strong>!</span>
                    <Link className="button" to="/NewFood">Add new alimento</Link>
                    <button onClick={logout} type="button">
                        <FiPower size={20}color="#3967E6"/>  
                    </button>
            </header>

            <h1>Regristrar o alimento</h1>
            <ul> 
                {breakfast.map(breakfast =>(
                <li key={breakfast.id}>           
                    <strong>Nome:</strong>
                    <p>{breakfast.nome}</p>
                    <strong>Cpf</strong>
                    <p>{breakfast.cpf}</p>
                    <strong>Comida</strong>
                    <p>{breakfast.comida}</p>
                    <strong>Bebida</strong>
                    <p>{breakfast.bebida}</p>
                    <strong>{Intl.DateTimeFormat('pt-BR').format(new Date( breakfast.dataatual))}</strong>
                    <p>23/01/22</p>
                    
                    <button type="button">
                        <FiEdit size={20} color="251FC5"/>
                    </button>
                    <button onClick={()=>deleteBreakfast(breakfast.id)} type="button">
                        <FiTrash2 size={20} color="251FC5"/>

                    </button>
                </li>
                ))}
            </ul>
        </div>
    );
}