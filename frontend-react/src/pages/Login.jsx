import React from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Login() {
  const [email,setEmail]=useState('');
  const [password,setPassword]=useState('');
  const navigate=useNavigate();

  function handleSubmit(e){
    e.preventDefault();
    if(email && password) navigate('/dashboard');
  }

  return <div className="login-page"><form className="card p-4 shadow login-card" onSubmit={handleSubmit}>
    <h2 className="mb-4 text-center">AgroMonitor</h2>
    <input className="form-control mb-3" placeholder="Correo" value={email} onChange={e=>setEmail(e.target.value)}/>
    <input className="form-control mb-3" type="password" placeholder="Contraseña" value={password} onChange={e=>setPassword(e.target.value)}/>
    <button className="btn btn-primary w-100">Iniciar sesión</button>
  </form></div>;
}