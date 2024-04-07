// registrationService.js

import http from "../http-common";
class RegistrationService{
    registerUser(userData) {
        return http.post(`/users`, userData);
      }
      registerDoctorDescription(doctorDescData) {
        return http.post(`/doctor-desc`, doctorDescData);
      }
}
export default new RegistrationService();
