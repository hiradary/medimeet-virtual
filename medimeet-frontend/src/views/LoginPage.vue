<template>
  <main>
   <NavBar />
    <div class="my-5">
        <div class="mx-auto w-25" style="max-width: 100%">
          <h2 class="text-center mb-4">Login</h2>
          <form @submit.prevent="addPatients">
            <div class="row">

              <div for="name" class="col-md-12 form-group mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" name="name" class="form-control" id="name" v-model="patients.name" required>
              </div>
              <div for="email" class="col-md-12 form-group mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" name="email" class="form-control" id="email" v-model="patients.email" required>
              </div>
              <div for="phone" class="col-md-12 form-group mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="number" name="phone" class="form-control" id="phone" v-model="patients.phone" required>
              </div>

              <div for="id" class="col-md-12 form-group mb-3">
                <label for="id" class="form-label">ID</label>
                <input type="text" name="id" class="form-control" id="id" v-model="patients.id" required>
              </div>
              <div for="age" class="col-md-12 form-group mb-3">
                <label for="age" class="form-label">Age</label>
                <input type="number" name="age" class="form-control" id="age" v-model="patients.age" required>
              </div>
              
              <label for="gender" class="form-label">Gender</label>
              <select id="gender" class="form-control" v-model="patients.gender" required>
                <option value="">Select gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">None</option>
              </select>
              <br>

              <div for="bio" class="col-md-12 form-group mb-3">
                <label for="bio" class="form-label">Bio</label>
                <textarea name="bio" class="form-control" id="bio" v-model="patients.bio" required></textarea>
              </div>
              <div class="col-12 form-group">
                <input type="submit" value="Login" class="btn btn-primary w-100">
              </div>
            </div>
          </form>
        </div>
    </div>
   </main> 
</template>

<script>
import NavBar from '@/components/NavBar.vue'

export default {
  name: 'LoginPage',
  components: {
    NavBar,
  },
  data(){
    return {
      patients: {
        name: '',
        email: '',
        id: '',
        age: '',
        gender: '',
        phone: '',
        bio: ''
      }
    }
  },
  methods: {
    addPatients(){
      fetch("http://localhost:8080/patients", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.patients)
      })
      .then(res => res.json())
      .then(data => {
          console.log(data)
          this.$router.push("/")
      })
      .catch(err => console.log(err.message))
    },
    deletePatient(id){
      return id;
    },
    updatePatient(id){
      return id;
    }
  }
}

</script>

<style>
    
</style>