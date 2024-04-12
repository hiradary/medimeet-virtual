// prescriptionService.js

import http from "../http-common";

class PrescriptionService {
  getAll() {
    return http.get('/prescriptions');
  }

  getById(id) {
    return http.get(`/prescriptions/${id}`);
  }

  getByAppointmentId(appointmentId) {
    return http.get(`/prescriptions/appointment/${appointmentId}`);
  }

  create(data) {
    return http.post('/prescriptions', data);
  }
//   create(appointmentData, appId) {
//     return http.post(`/prescriptions?appId=${appId}`, appointmentData);
// }

  update(id, data) {
    return http.put(`/prescriptions/${id}`, data);
  }

  delete(id) {
    return http.delete(`/prescriptions/${id}`);
  }
}

export default new PrescriptionService();
