import { useState } from 'react';
import axios from 'axios';
import './App.css'; 

function App() {
  const estadoInicial = {
    name: '',
    lastName: '',
    email: '',
    account: '',
    avg: '',
    country: ''
  };

  const [formData, setFormData] = useState(estadoInicial);
  const [mensaje, setMensaje] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleLimpiar = () => {
    setFormData(estadoInicial);
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); 

    const formularioCompleto = Object.values(formData).every(valor => valor.trim() !== '');
    if (!formularioCompleto) {
      setMensaje('Todos los campos son obligatorios.');
      return;
    }

    setMensaje('⏳ Guardando información...');

    //FormData, la API espera form-data y no un JSON plano
    const datosParaEnviar = new FormData();
    datosParaEnviar.append('name', formData.name);
    datosParaEnviar.append('lastName', formData.lastName);
    datosParaEnviar.append('email', formData.email);
    datosParaEnviar.append('account', formData.account);
    datosParaEnviar.append('avg', formData.avg);
    datosParaEnviar.append('country', formData.country);

    try {
      // Consumimos el servicio usando Axios
      const respuesta = await axios.post('https://masksoft.com.mx/register', datosParaEnviar);

      // Axios guarda la respuesta directa del servidor en 'respuesta.data'
      if (respuesta.data && respuesta.data.status === 'success') {
        setMensaje('¡Datos guardados exitosamente!');
        // Limpiar sólo el formulario, mantener el mensaje visible unos segundos
        setFormData(estadoInicial);
        setTimeout(() => setMensaje(''), 4000);
      } else {
        // Por si la API devuelve un status 200 pero un mensaje lógico de error
        setMensaje(` Hubo un problema: ${respuesta.data.message || 'Intenta de nuevo'}`);
      }
    } catch (error) {
      console.error('Error en la petición:', error);
      
      if (error.response) {
        setMensaje(` Error del servidor: ${error.response.data.message || 'Intenta de nuevo'}`);
      } else {
        setMensaje(' Error de conexión al guardar los datos.');
      }
    }
  };

  return (
    <div className="contenedor-formulario">
      <h2>Registro de Estudiantes</h2>
      
      {mensaje && <p className="mensaje">{mensaje}</p>}

      <form onSubmit={handleSubmit}>
        <div className="campo">
          <label>Nombre(s):</label>
          <input 
            type="text" 
            name="name" 
            value={formData.name} 
            onChange={handleChange} 
            required 
          />
        </div>

        <div className="campo">
          <label>Apellidos:</label>
          <input 
            type="text" 
            name="lastName" 
            value={formData.lastName} 
            onChange={handleChange} 
            required 
          />
        </div>

        <div className="campo">
          <label>Correo electrónico:</label>
          <input 
            type="email" 
            name="email" 
            value={formData.email} 
            onChange={handleChange} 
            required 
          />
        </div>

        <div className="campo">
          <label>Número de cuenta:</label>
          <input 
            type="text" 
            name="account" 
            value={formData.account} 
            onChange={handleChange} 
            required 
          />
        </div>

        <div className="campo">
          <label>Promedio:</label>
          <input 
            type="number" 
            step="0.01" 
            name="avg" 
            value={formData.avg} 
            onChange={handleChange} 
            required 
          />
        </div>

        <div className="campo">
          <label>País:</label>
          <input 
            type="text" 
            name="country" 
            value={formData.country} 
            onChange={handleChange} 
            required 
          />
        </div>

        <div className="botones">
          <button type="button" onClick={handleLimpiar} className="btn-limpiar">
            Limpiar
          </button>
          <button type="submit" className="btn-guardar">
            Guardar
          </button>
        </div>
      </form>
    </div>
  );
}

export default App;