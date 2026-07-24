import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Monitoring from './pages/Monitoring';
import ControlPanel from './pages/ControlPanel';
import History from './pages/History';
import Alerts from './pages/Alerts';
import Settings from './pages/Settings';

function MainLayout({ children }) {
  return <><Navbar/><main className="container py-4">{children}</main></>;
}

export default function App() {
  return <BrowserRouter><Routes>
    <Route path="/login" element={<Login/>}/>
    <Route path="/dashboard" element={<MainLayout><Dashboard/></MainLayout>}/>
    <Route path="/monitoreo" element={<MainLayout><Monitoring/></MainLayout>}/>
    <Route path="/control" element={<MainLayout><ControlPanel/></MainLayout>}/>
    <Route path="/historial" element={<MainLayout><History/></MainLayout>}/>
    <Route path="/alertas" element={<MainLayout><Alerts/></MainLayout>}/>
    <Route path="/configuracion" element={<MainLayout><Settings/></MainLayout>}/>
    <Route path="/" element={<Navigate to="/dashboard" replace/>}/>
    <Route path="*" element={<Navigate to="/dashboard" replace/>}/>
  </Routes></BrowserRouter>;
}