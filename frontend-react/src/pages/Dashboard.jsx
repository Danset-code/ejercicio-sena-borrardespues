import React from 'react';
import { useEffect, useState } from 'react';
import { sensorService } from '../services/sensorService';
import { medicionService } from '../services/medicionService';

export default function Dashboard() {

    // ============================================================
    // ESTADOS
    // ============================================================

    const [sensores, setSensores] = useState([]);
    const [mediciones, setMediciones] = useState([]);

    // Formulario para crear sensor
    const [nuevoSensor, setNuevoSensor] = useState({
        nombre: '',
        tipo: '',
        unidad: '',
        activo: true
    });

    // Formulario para agregar medición
    const [nuevaMedicion, setNuevaMedicion] = useState({
        sensorId: '',
        valor: ''
    });

    // Estado para editar sensor
    const [sensorEditando, setSensorEditando] = useState(null);
    const [nuevoNombre, setNuevoNombre] = useState('');

    // Estado de carga
    const [cargando, setCargando] = useState(false);

    // ============================================================
    // CARGAR DATOS
    // ============================================================

    useEffect(() => {
        cargarDatos();
    }, []);

    async function cargarDatos() {

        try {

            setCargando(true);

            const sensoresData = await sensorService.getAll();
            const medicionesData = await medicionService.getAll();

            setSensores(sensoresData);
            setMediciones(medicionesData);

        } catch (error) {

            console.error('Error cargando datos:', error);

            alert(
                'No se pudieron cargar los datos. ' +
                'Verifique que Spring Boot esté ejecutándose.'
            );

        } finally {

            setCargando(false);

        }
    }

    // ============================================================
    // CREAR SENSOR
    // ============================================================

    async function crearSensor(event) {

        event.preventDefault();

        if (!nuevoSensor.nombre ||
            !nuevoSensor.tipo ||
            !nuevoSensor.unidad) {

            alert('Complete todos los campos del sensor.');

            return;
        }

        try {

            await sensorService.create(nuevoSensor);

            alert('Sensor creado correctamente.');

            setNuevoSensor({
                nombre: '',
                tipo: '',
                unidad: '',
                activo: true
            });

            cargarDatos();

        } catch (error) {

            console.error(error);

            alert('Error al crear el sensor.');

        }
    }

    // ============================================================
    // CREAR MEDICIÓN
    // ============================================================

    async function crearMedicion(event) {

        event.preventDefault();

        if (!nuevaMedicion.sensorId ||
            nuevaMedicion.valor === '') {

            alert('Seleccione un sensor e ingrese un valor.');

            return;
        }

        try {

            const sensorSeleccionado = sensores.find(
                sensor =>
                    sensor.id === Number(nuevaMedicion.sensorId)
            );

            const medicion = {

                sensor: sensorSeleccionado,

                valor: Number(nuevaMedicion.valor),

                fecha: new Date().toISOString()

            };

            await medicionService.create(medicion);

            alert('Medición registrada correctamente.');

            setNuevaMedicion({
                sensorId: '',
                valor: ''
            });

            cargarDatos();

        } catch (error) {

            console.error(error);

            alert('Error al registrar la medición.');

        }
    }

    // ============================================================
    // EDITAR NOMBRE DEL SENSOR
    // ============================================================

    function comenzarEdicion(sensor) {

        setSensorEditando(sensor);

        setNuevoNombre(sensor.nombre);

    }

    async function guardarEdicion() {

        if (!nuevoNombre.trim()) {

            alert('El nombre no puede estar vacío.');

            return;
        }

        try {

            const sensorActualizado = {

                ...sensorEditando,

                nombre: nuevoNombre

            };

            await sensorService.update(
                sensorEditando.id,
                sensorActualizado
            );

            alert('Sensor actualizado correctamente.');

            setSensorEditando(null);

            setNuevoNombre('');

            cargarDatos();

        } catch (error) {

            console.error(error);

            alert('Error al actualizar el sensor.');

        }
    }

    // ============================================================
    // ELIMINAR SENSOR
    // ============================================================

    async function eliminarSensor(sensor) {

        const confirmar = window.confirm(

            `¿Está seguro de eliminar el sensor "${sensor.nombre}"?`

        );

        if (!confirmar) {

            return;

        }

        try {

            await sensorService.remove(sensor.id);

            alert('Sensor eliminado correctamente.');

            cargarDatos();

        } catch (error) {

            console.error(error);

            alert(

                'No se pudo eliminar el sensor. ' +
                'Es posible que tenga mediciones asociadas.'

            );

        }
    }

    // ============================================================
    // ELIMINAR MEDICIÓN
    // ============================================================

    async function eliminarMedicion(medicion) {

        const confirmar = window.confirm(

            '¿Desea eliminar esta medición?'

        );

        if (!confirmar) {

            return;

        }

        try {

            await medicionService.remove(medicion.id);

            alert('Medición eliminada correctamente.');

            cargarDatos();

        } catch (error) {

            console.error(error);

            alert('Error al eliminar la medición.');

        }
    }

    // ============================================================
    // ÚLTIMA MEDICIÓN DEL SENSOR
    // ============================================================

    function obtenerUltimaMedicion(sensorId) {

        const medicionesSensor = mediciones

            .filter(
                medicion =>
                    medicion.sensor &&
                    medicion.sensor.id === sensorId
            )

            .sort(
                (a, b) =>
                    new Date(b.fecha) -
                    new Date(a.fecha)
            );

        return medicionesSensor[0];

    }

    // ============================================================
    // INTERFAZ
    // ============================================================

    return (

        <div>

            <h1 className="mb-4">
                Dashboard de Monitoreo Ambiental
            </h1>

            <p className="text-muted">

                Sistema de medición y control ambiental del cultivo.

            </p>

            {/* =====================================================
                TARJETAS DE SENSORES
            ====================================================== */}

            <div className="row g-4 mb-5">

                {sensores.map(sensor => {

                    const ultimaMedicion =
                        obtenerUltimaMedicion(sensor.id);

                    return (

                        <div
                            className="col-md-4"
                            key={sensor.id}
                        >

                            <div className="card shadow-sm h-100">

                                <div className="card-body">

                                    <h5 className="card-title">

                                        {sensor.nombre}

                                    </h5>

                                    <p className="text-muted">

                                        {sensor.tipo}

                                    </p>

                                    <div className="display-6 mb-3">

                                        {ultimaMedicion
                                            ? ultimaMedicion.valor
                                            : '--'}

                                        {' '}

                                        {sensor.unidad}

                                    </div>

                                    <span
                                        className={
                                            sensor.activo
                                                ? 'badge bg-success'
                                                : 'badge bg-secondary'
                                        }
                                    >

                                        {sensor.activo
                                            ? 'Activo'
                                            : 'Inactivo'}

                                    </span>

                                    <div className="mt-3">

                                        <button
                                            className="btn btn-warning btn-sm me-2"
                                            onClick={() =>
                                                comenzarEdicion(sensor)
                                            }
                                        >

                                            Editar nombre

                                        </button>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() =>
                                                eliminarSensor(sensor)
                                            }
                                        >

                                            Eliminar

                                        </button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    );

                })}

            </div>


            {/* =====================================================
                CREAR SENSOR
            ====================================================== */}

            <div className="card shadow-sm mb-4">

                <div className="card-body">

                    <h3>
                        Registrar nuevo sensor
                    </h3>

                    <form
                        onSubmit={crearSensor}
                        className="row g-3"
                    >

                        <div className="col-md-4">

                            <label className="form-label">
                                Nombre
                            </label>

                            <input
                                className="form-control"
                                value={nuevoSensor.nombre}
                                onChange={e =>
                                    setNuevoSensor({
                                        ...nuevoSensor,
                                        nombre: e.target.value
                                    })
                                }
                                placeholder="Ej: Sensor de temperatura"
                            />

                        </div>

                        <div className="col-md-4">

                            <label className="form-label">
                                Tipo
                            </label>

                            <input
                                className="form-control"
                                value={nuevoSensor.tipo}
                                onChange={e =>
                                    setNuevoSensor({
                                        ...nuevoSensor,
                                        tipo: e.target.value
                                    })
                                }
                                placeholder="Ej: Temperatura"
                            />

                        </div>

                        <div className="col-md-4">

                            <label className="form-label">
                                Unidad
                            </label>

                            <input
                                className="form-control"
                                value={nuevoSensor.unidad}
                                onChange={e =>
                                    setNuevoSensor({
                                        ...nuevoSensor,
                                        unidad: e.target.value
                                    })
                                }
                                placeholder="Ej: °C"
                            />

                        </div>

                        <div className="col-12">

                            <button className="btn btn-success">

                                + Registrar sensor

                            </button>

                        </div>

                    </form>

                </div>

            </div>


            {/* =====================================================
                AGREGAR MEDICIÓN
            ====================================================== */}

            <div className="card shadow-sm mb-4">

                <div className="card-body">

                    <h3>
                        Registrar nueva medición
                    </h3>

                    <form
                        onSubmit={crearMedicion}
                        className="row g-3"
                    >

                        <div className="col-md-6">

                            <label className="form-label">
                                Sensor
                            </label>

                            <select
                                className="form-select"
                                value={nuevaMedicion.sensorId}
                                onChange={e =>
                                    setNuevaMedicion({
                                        ...nuevaMedicion,
                                        sensorId: e.target.value
                                    })
                                }
                            >

                                <option value="">
                                    Seleccione un sensor
                                </option>

                                {sensores.map(sensor => (

                                    <option
                                        key={sensor.id}
                                        value={sensor.id}
                                    >

                                        {sensor.nombre}

                                    </option>

                                ))}

                            </select>

                        </div>


                        <div className="col-md-6">

                            <label className="form-label">
                                Valor
                            </label>

                            <input
                                type="number"
                                step="0.01"
                                className="form-control"
                                value={nuevaMedicion.valor}
                                onChange={e =>
                                    setNuevaMedicion({
                                        ...nuevaMedicion,
                                        valor: e.target.value
                                    })
                                }
                                placeholder="Ej: 25.5"
                            />

                        </div>


                        <div className="col-12">

                            <button className="btn btn-primary">

                                + Registrar medición

                            </button>

                        </div>

                    </form>

                </div>

            </div>


            {/* =====================================================
                EDITAR SENSOR
            ====================================================== */}

            {sensorEditando && (

                <div className="card shadow-sm mb-4 border-warning">

                    <div className="card-body">

                        <h3>
                            Editar sensor
                        </h3>

                        <p>

                            Sensor seleccionado:

                            {' '}

                            <strong>

                                {sensorEditando.nombre}

                            </strong>

                        </p>

                        <input
                            className="form-control mb-3"
                            value={nuevoNombre}
                            onChange={e =>
                                setNuevoNombre(e.target.value)
                            }
                        />

                        <button
                            className="btn btn-success me-2"
                            onClick={guardarEdicion}
                        >

                            Guardar cambios

                        </button>

                        <button
                            className="btn btn-secondary"
                            onClick={() =>
                                setSensorEditando(null)
                            }
                        >

                            Cancelar

                        </button>

                    </div>

                </div>

            )}


            {/* =====================================================
                HISTORIAL DE MEDICIONES
            ====================================================== */}

            <div className="card shadow-sm">

                <div className="card-body">

                    <h3>
                        Historial de mediciones
                    </h3>

                    <div className="table-responsive">

                        <table className="table table-striped">

                            <thead>

                                <tr>

                                    <th>
                                        Sensor
                                    </th>

                                    <th>
                                        Valor
                                    </th>

                                    <th>
                                        Unidad
                                    </th>

                                    <th>
                                        Fecha
                                    </th>

                                    <th>
                                        Acción
                                    </th>

                                </tr>

                            </thead>

                            <tbody>

                                {mediciones.map(medicion => (

                                    <tr key={medicion.id}>

                                        <td>

                                            {medicion.sensor?.nombre}

                                        </td>

                                        <td>

                                            {medicion.valor}

                                        </td>

                                        <td>

                                            {medicion.sensor?.unidad}

                                        </td>

                                        <td>

                                            {new Date(
                                                medicion.fecha
                                            ).toLocaleString()}

                                        </td>

                                        <td>

                                            <button
                                                className="btn btn-danger btn-sm"
                                                onClick={() =>
                                                    eliminarMedicion(
                                                        medicion
                                                    )
                                                }
                                            >

                                                Eliminar

                                            </button>

                                        </td>

                                    </tr>

                                ))}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </div>

    );

}