import http from "../http-common";

class RegistrationService {
  registerUser(userData) {
      return http.post(`/users`, userData);
  }

  registerDoctorDescription(doctorDescData, userId) {
      return http.post(`/doctordesc?userId=${userId}`, doctorDescData);
  }
}

export default new RegistrationService();
