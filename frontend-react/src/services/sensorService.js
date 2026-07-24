import { api } from './api';

export const sensorService = {

    getAll: () =>
        api.get('/sensores'),

    getById: (id) =>
        api.get('/sensores/' + id),

    create: (data) =>
        api.post('/sensores', data),

    update: (id, data) =>
        api.put('/sensores/' + id, data),

    remove: (id) =>
        api.delete('/sensores/' + id)

};