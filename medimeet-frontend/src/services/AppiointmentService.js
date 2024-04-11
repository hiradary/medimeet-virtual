// appointment-service.js


import http from "../http-common";
class AppointmentService {
  getAll() {
    return http.get('/appointments');
  }
  getAllUsers() {
    return http.get('/users');
  }
  create(data) {
    return http.post('/appointments', data);
  }
}

export default new AppointmentService();
