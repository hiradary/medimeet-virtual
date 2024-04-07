<template>
  <div class="bg-img">
    <div class="container d-flex align-items-center justify-content-center h-100">
      <div class="card">
        <div class="card-body">
          <h2 class="text-center">Register</h2>
          <form @submit.prevent="registerUser">
            <div class="mb-3">
              <input type="text" class="form-control" placeholder="Name" v-model="user.name" required>
            </div>
            <div class="mb-3">
              <input type="email" class="form-control" placeholder="Email" v-model="user.email" required>
            </div>
            <div class="mb-3">
              <input type="password" class="form-control" placeholder="Password" v-model="user.password" required>
            </div>
            <div class="mb-3">
              <input type="text" class="form-control" placeholder="Address" v-model="user.address">
            </div>
            <div class="mb-3">
              <input type="text" class="form-control" placeholder="Phone" v-model="user.phone">
            </div>
            <div class="mb-3">
              <select class="form-control" v-model="user.userType">
                <option value="" disabled selected>Select User Type</option>
                <option value="regular">Regular</option>
                <option value="doctor">Doctor</option>
              </select>
            </div>
            <div v-if="user.userType === 'doctor'" class="mb-3">
              <select class="form-control" v-model="doctorDesc.specialization" ref="specializationSelect">
                <option value="" disabled selected>Select Specialization</option>
                <option v-for="(specialization, index) in specializations" :key="index" :value="specialization">{{ specialization }}</option>
              </select>
            </div>
            <div v-if="user.userType === 'doctor'" class="mb-4">
              <label>Availability:</label>
              <div class="availability-options">
                <div class="availability-option">
                  <input type="radio" id="morning" value="Morning (9:00 AM - 12:00 PM)" v-model="doctorDesc.availability">
                  <label for="morning">Morning</label>
                  <span>(9:00 AM - 12:00 PM)</span>
                </div>
                <div class="availability-option">
                  <input type="radio" id="afternoon" value="Afternoon (1:00 PM - 4:00 PM)" v-model="doctorDesc.availability">
                  <label for="afternoon">Afternoon</label>
                  <span>(1:00 PM - 4:00 PM)</span>
                </div>
                <div class="availability-option">
                  <input type="radio" id="evening" value="Evening (5:00 PM - 8:00 PM)" v-model="doctorDesc.availability">
                  <label for="evening">Evening</label>
                  <span>(5:00 PM - 8:00 PM)</span>
                </div>
              </div>
            </div>            
            <div v-if="user.userType === 'doctor'" class="mb-3">
              <input type="text" class="form-control" placeholder="Bio" v-model="doctorDesc.bio">
            </div>
            <div class="text-center">
              <button type="submit" class="btn btn-primary">Register</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import 'select2/dist/css/select2.css';
import 'select2';
import $ from 'jquery';
import RegistrationService from '../services/RegistrationService';

export default {
  data() {
    return {
      user: {
        name: '',
        email: '',
        password: '',
        userType: '',
        address: '',
        phone: ''
      },
      doctorDesc: {
        specialization: '',
        availability: '',
        bio: ''
      },
      specializations: [
        // List of specializations
        "Anatomical Pathology",
        "Anesthesiology",
        "Cardiology",
        "Cardiovascular & Thoracic Surgery",
        "Clinical Immunology/Allergy",
        "Critical Care Medicine",
        "Dermatology",
        "Diagnostic Radiology",
        "Emergency Medicine",
        "Endocrinology and Metabolism",
        "Family Medicine",
        "Gastroenterology",
        "General Internal Medicine",
        "General Surgery",
        "General/Clinical Pathology",
        "Geriatric Medicine",
        "Hematology",
        "Medical Biochemistry",
        "Medical Genetics",
        "Medical Microbiology and Infectious Diseases",
        "Medical Oncology",
        "Nephrology",
        "Neurology",
        "Neurosurgery",
        "Nuclear Medicine",
        "Obstetrics/Gynecology",
        "Occupational Medicine",
        "Ophthalmology",
        "Orthopedic Surgery",
        "Otolaryngology",
        "Pediatrics",
        "Physical Medicine and Rehabilitation (PM & R)",
        "Plastic Surgery",
        "Psychiatry",
        "Public Health and Preventive Medicine (PhPm)",
        "Radiation Oncology",
        "Respirology",
        "Rheumatology",
        "Urology"
      ]
    };
  },
  mounted() {
    $(this.$refs.specializationSelect).select2({
      placeholder: "Select Specialization",
      allowClear: true,
      width: '100%'
    });
  },
  methods: {
    registerUser(event) {
      event.preventDefault(); // Prevent default form submission behavior
      
      // Call the registration service method to register the user
      RegistrationService.registerUser(this.user)
        .then(response => {
          // If registration is successful, handle the response
          console.log(response.data); // Log the response data (optional)
          // Optionally, you can redirect the user to a different route after successful registration
          this.$router.push({ name: "home" });
        })
        .catch(error => {
          // If registration encounters an error, handle the error
          console.error('Error during user registration:', error);
          // Display an error message to the user (optional)
          alert('User registration failed. Please try again.');
        });

      // If the user type is "doctor", register doctor description
      if (this.user.userType === 'doctor') {
        // Call the registration service method to register the doctor description
        RegistrationService.registerDoctorDescription(this.doctorDesc)
          .then(response => {
            // If registration is successful, handle the response
            console.log(response.data); // Log the response data (optional)
            // You can perform additional actions if needed
          })
          .catch(error => {
            // If registration encounters an error, handle the error
            console.error('Error during doctor description registration:', error);
            // Display an error message to the user (optional)
            alert('Doctor description registration failed. Please try again.');
          });
      }
    }
  }
};
</script>

<style>
.bg-img {
  background-image: url('../assets/img/bg-img.jpeg');
  background-size: cover;
  height: 100vh;
}

.card {
  width: 550px;
}

.card-body {
  padding: 20px;
}

.btn-primary {
  width: 100%;
}
.availability-options {
  display: flex;
  flex-wrap: wrap;
}

.availability-option {
  flex-basis: calc(33.33% - 20px); /* Adjust width based on the number of options you have */
  margin-right: 20px;
  margin-bottom: 20px; /* Adjust margin-bottom for spacing */
  display: flex;
  align-items: center;
}

.availability-option label {
  width: 150px; /* Adjust label width as needed */
  margin-right: 10px;
}

.availability-option span {
  margin-left: 10px;
}
</style>
