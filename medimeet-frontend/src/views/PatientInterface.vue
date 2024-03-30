<template>
  <NavBar />
  <div class="container">
    <div class="row">
      <div class="col-md-12">

        <h1 class="text-center">Patient List</h1>
        <a class="btn btn-primary btn-block" href="/registration">Add Patient</a>
        <br>

        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Email</th>
              <th scope="col">ID</th>
              <th scope="col">Age</th>
              <th scope="col">Gender</th>
              <th scope="col">Phone</th>
              <th scope="col">Status</th>
              <th scope="col">Prescription</th>
              <th scope="col">Bio</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="patient in patients" :key="patient.id">
              <td>{{ patient.name }}</td>
              <td>{{ patient.email }}</td>
              <td>{{ patient.id }}</td>
              <td>{{ patient.age }}</td>
              <td>{{ patient.gender }}</td>
              <td>
                <a class="btn btn-primary" href="/edit/{{ patient.id }}">Edit</a>
                <button class="btn btn-danger" @click="deletePatient(patient.id)">Delete</button>
              </td>
            </tr>
          </tbody>

        </table>

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