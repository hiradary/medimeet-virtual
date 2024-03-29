<template>
  <NavBar />
  <br>
  <h2>Patient Interface</h2>

    <!-- <input type="submit" value="My Appointment">
    <input type="submit" value="Feedback">
    <input type="submit" value="My prescription">
    <br>
    <br>
      <input type="submit" value="Update your profile">            
      <input type="submit" value="Delete your account">
      <input type="submit" value="Logout">
    <br>
    <label for="patientName">Patient Name: </label>
      <br>
    <label for="patientEmail">Email: </label>
     <br>
    <label for="patientID">Patient ID: </label>
     <br>
    <label for="patientAge">Age: </label>
     <br>
    <label for="patientGender">Gender: </label>
      <br>
    <label for="patientPhone">Phone: </label>
    <br>
    <label for="patientBio">Bio: </label>
    <br>
    <p>
    </p> -->
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          
        </div>
    </div>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default {
  name: 'PatientInterface',
  components: {
      NavBar,

  }, 
  data(){
    return {
      patients: []
    }
  },
  method : {
    getPatienst(){
      fetch("http://localhost:8080/patients")
      .then(res => res.json())
      .then(data => {
          this.patients = data
          console.log(data) })
      .catch(err => console.log(err.message))
    },
    deletePatient(id){
      fetch(`http://localhost:8080/patients/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
      })
      .then(res => res.json())
      .then(data => console.log(data))
      .catch(err => console.log(err.message)) 
    }
  },
  mounted() {

  }
}
</script>

<style>
#user {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>