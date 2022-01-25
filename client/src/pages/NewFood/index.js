import React, {useEffect, useState} from 'react';
import { useHistory, Link } from "react-router-dom";
import {FiArrowLeftCircle} from 'react-icons/fi'
import api from '../../Service/api';

import icone from '../../assets/icone.svg'
import './styles.css';

export default function NewFood(){
    const[id,setId]= useState(null);
    const[nome,setNome]= useState(''); 
    const[cpf,setCpf]= useState(null);
    const[comida,setComida]= useState('');
    const[bebida,setBebida]= useState('');
    const[dataatual ,setDataatual]= useState('');

    const acesseToken = localStorage.getItem('acesseToken');

    const history=useHistory();

    async function loadBreakfast() {
        try {
            const response = await api.get(`api/breakfast/v1/${breakfastId}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            })
            let adjustedDate = response.data.launchDate.split("T", 10)[0];

            setId(response.data.id)
            setNome(response.data.nome)
            setCpf(response.data.cpf)
            setComida(response.data.comida)
            setBebida(response.data.bebida)
            setDataatual(adjustedDate)

    async function saveOrUpdate(e) {
        e.preventDefault();


        const data ={
            nome,
            cpf,
            comida,
            bebida,
            dataatual,
        }

        try {
            await api.post('/api/breakfast/v1',data,header,{
                Headers:{
                    Authorization:`Bearer ${acesseToken}`
                }
             });

             try {
                if (breakfastId === '0') {
                    await api.post('api/breakfast/v1', data, {
                        headers: {
                            Authorization: `Bearer ${accessToken}`
                        }
                    });
                } else {
                    data.id = id;
                    await api.put('api/breakfast/v1', data, {
                        headers: {
                            Authorization: `Bearer ${accessToken}`
                        }
                    });
                }
            
            history.push('/breakfast');
        } catch (err) {
            alert('falha enquanto salva a lista');
        }
    }
    
    return(
        <div className="Food-container">
            <div className="content">
                <section className="form">
                    <img src={icone} alt= "erudio"/>
                    <h1 className='titulo1'>{ breakfastId === '0'?"'add new'":"'update'"}</h1>
                    <p>Coloque as informaçao e clique no { breakfastId === '0'?"'add'":"'update'"}!</p>
                    <Link className="back-link" to="/Breakfast">
                        <FiArrowLeftCircle size={16} color="#251fc5"/>
                        voltar para breakfast
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
                    <input
                        type="double" placeholder="ID" 
                           value={id}
                           onChange={e=>setId(e.target.value)}                            
                        />
                    <input                    
                        placeholder="Nome"
                           value={nome}
                           onChange={e=>setNome(e.target.value)}
                        />
                    <input
                        type="double" placeholder="000.000.000-00" pattern="(\d{3}\.?\d{3}\.?\d{3}-?\d{2})"
                           value={cpf}
                           onChange={e=>setCpf(e.target.value)}                            
                        />
                    <input
                        placeholder="Comida"
                           value={comida}
                           onChange={e=>setComida(e.target.value)}
                        />
                    <input
                        placeholder="Bebida"
                           value={bebida}
                           onChange={e=>setBebida(e.target.value)}
                        />
                    <input
                        type="Data" placeholder="Data"
                           value={dataatual}
                           onChange={e=>setDataatual(e.target.value)}
                        />
                    <button className="button" type="submit">{breakfastId === '0'?'add':'update'}</button>

                </form>

            </div>
        
        
        </div>

    );
}