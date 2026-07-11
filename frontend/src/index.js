/**
 * Punto de entrada de la aplicación React.
 * Renderiza el componente App dentro del div#root del HTML.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

// Crear el punto de montaje y renderizar la aplicación
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);