// appointment-service.js


import http from "../http-common";
class AppointmentService {
  getAll() {
    return http.get('/appointments');
  }
  getById(id) {
    return http.get(`/appointments/${id}`);
  }
  getAllUsers() {
    return http.get('/users');
  }
  create(data) {
    return http.post('/appointments', data);
  }
  update(id, data) {
    return http.put(`/appointments/${id}`, data);
  }

}

export default new AppointmentService();
