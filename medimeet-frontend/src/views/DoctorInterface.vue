<template>
  <div>
    <h1>Doctor Interface</h1>

    <div class="container">
      <h2>Appointments</h2>
      <table v-if="appointments.length > 0" style="width: 100%; border-collapse: collapse;">
        <thead>
          <tr style="background-color: #f2f2f2;">
            <th style="padding: 8px; border: 1px solid #dddddd; text-align: left;">Patient</th>
            <th style="padding: 8px; border: 1px solid #dddddd; text-align: left;">Date</th>
            <th style="padding: 8px; border: 1px solid #dddddd; text-align: left;">Time</th>
            <th style="padding: 8px; border: 1px solid #dddddd; text-align: left;">Status</th>
            <th style="padding: 8px; border: 1px solid #dddddd; text-align: left;">Actions</th> <!-- Add Actions column -->

          </tr>
        </thead>
        <tbody>
          <tr v-for="appointment in appointments" :key="appointment.appointmentId" style="border: 1px solid #dddddd;">
            <td style="padding: 8px; border: 1px solid #dddddd;">{{appointment.patient ? appointment.patient.name : ''  }}</td>
            <td style="padding: 8px; border: 1px solid #dddddd;">{{ appointment.appointmentDate }}</td>
            <td style="padding: 8px; border: 1px solid #dddddd;">{{ appointment.appointmentTime }}</td>
            <td style="padding: 8px; border: 1px solid #dddddd;">{{ appointment.status }}</td>
            <td style="padding: 8px; border: 1px solid #dddddd;">
              <button @click="goToAppointmentDetails(appointment.appointmentId)">Details</button>
            </td>
          </tr>
        </tbody>
      </table>
      <p v-else>No appointments available.</p>
      <!-- Create New Appointment Form -->
      <div class="card">
        <!-- Form content -->
        <div class="card-body">
          <h2>New Appointment</h2>
          <form @submit.prevent="submitAppointment">
            <div class="form-group">
              <label for="patient">Patient:</label>
              <select v-model="selectedPatient" id="patient" class="form-control">
                <option v-for="user in users" :key="user.userId" :value="user.userId">{{ user.name }}</option>
              </select>
            </div>
            <div class="form-group">
              <label for="date">Date:</label>
              <input type="date" v-model="date" id="date" class="form-control" required>
            </div>
            <div class="form-group">
              <label for="time">Time:</label>
              <input type="time" v-model="time" id="time" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Create Appointment</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import appointmentService from '../services/AppiointmentService';


export default {
  data() {
    return {
      appointments: [],
      users: [],
      selectedPatient: '',
      date: '',
      time: ''
    };
  },
  mounted() {
    this.fetchAppointments();
    this.fetchUsers();
  },
  methods: {
    goToAppointmentDetails(appointmentId) {
    console.log('Id:', appointmentId);
    this.$router.push({ name: 'AppointmentDetails', params: { id: appointmentId } });
  },
    async fetchAppointments() {
      try {
        const response = await appointmentService.getAll();
        if (Array.isArray(response.data)) {
          this.appointments = response.data;
        } else {
          console.error('Invalid response format for appointments:', response.data);
        }
      } catch (error) {
        console.error('Error fetching appointments:', error);
      }
    },
    async fetchUsers() {
      try {
        const response = await appointmentService.getAllUsers();
        if (Array.isArray(response.data)) {
          this.users = response.data;
        } else {
          console.error('Invalid response format for users:', response.data);
        }
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    async submitAppointment() {
      try {
        // Find the selected patient object based on the selectedPatient ID
        const selectedPatient = this.users.find(user => user.userId === this.selectedPatient);
        
        // Send the whole User object in the appointment creation request
        const response = await appointmentService.create({
          patient: selectedPatient, // Send the entire user object
          appointmentDate: this.date,
          appointmentTime: this.time,
          status: 'Scheduled' // Default status for new appointments
        });

        console.log('New appointment created:', response.data);

        // Clear form fields
        this.selectedPatient = '';
        this.date = '';
        this.time = '';

        // Refresh appointments after creating a new one
        this.fetchAppointments();
      } catch (error) {
        console.error('Error creating appointment:', error);
      }
    }
  }
};
</script>

<style scoped>
.new-appointment {
  margin: 20px auto;
  width: 80%;
  max-width: 600px;
}

.appointment-form {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}

.btn-primary:hover {
  background-color: #0056b3;
}
</style>
