import { api } from './api';

export const medicionService = {

    getAll: () =>
        api.get('/mediciones'),

    getById: (id) =>
        api.get('/mediciones/' + id),

    create: (data) =>
        api.post('/mediciones', data),

    update: (id, data) =>
        api.put('/mediciones/' + id, data),

    remove: (id) =>
        api.delete('/mediciones/' + id)

};