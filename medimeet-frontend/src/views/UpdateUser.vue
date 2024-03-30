<template>
    <main>
        <NavBar/>
        <div class="container">
        <div class="my-5">
          <div class="mx-auto w-25" style="max-width: 100%">

            <h2 class="text-center">Update Patient</h2> 
            <form @submit.prevent="updatePatient">

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="name" class="form-label">Name</label>
                  <!-- <input type="text" class="form-control" id="name" placeholder="Name" v-model="patient.name" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="email" class="form-label">Email</label>
                  <!-- <input type="email" class="form-control" id="email" placeholder="Email" v-model="patient.email" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="phone" class="form-label">Phone</label>
                  <!-- <input type="text" class="form-control" id="phone" placeholder="Phone" v-model="patient.phone" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="id" class="form-label">ID</label>
                  <!-- <input type="text" class="form-control" id="id" placeholder="Id" v-model="patient.id" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="age" class="form-label">Age</label>
                  <!-- <input type="text" class="form-control" id="age" placeholder="Age" v-model="patient.age" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="gender" class="form-label">Gender</label>
                  <!-- <input type="text" class="form-control" id="gender" placeholder="Gender" v-model="patient.gender" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="bio" class="form-label">Bio</label>
                  <!-- <input type="text" class="form-control" id="bio" placeholder="Bio" v-model="patient.bio" required> -->
                </div>
              </div>

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <input type="submit" value="Update Patient">
                </div>
              </div>
              

            </form>

          </div>
        </div>
      </div> 
    </main>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default {
  name: 'UpdateUser',
  components: {
    NavBar
  },
  data() {
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
        getuser() {
        fetch("http://localhost:8080/patients/" + this.$route.params.id)
        .then(res => res.json())
        .then(data => {
            this.patients = data
            console.log(data) 
          })
        .catch(err => console.log(err.message))
        },
        updateUser() {
        fetch("http://localhost:8080/patients/",  {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(this.patient)
        })
        .then(res => res.json())
        .then(data => {
            console.log(data)
            this.$router.push("/")
        })
        .catch(err => console.log(err.message))

        }
    },
    mounted() {
     
    }

}
</script>

<style>
    
</style>