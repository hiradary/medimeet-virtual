<template>
    <main>
      <NavBar/>  

      <div class="container">
        <div class="my-5">
          <div class="mx-auto w-25" style="max-width: 100%">

            <h2 class="text-center">Add Patients</h2> 
            <form @submit.prevent="addPatient">

              <div class="row">
                <div class="mb-3 form-group col-md-12">
                  <label for="name" class="form-label">Name</label>
                  
                  <!-- faild -->
                  <!--  <input type="text" class="form-control" id="name" placeholder="Name" v-model="patient.name" required> -->
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
                  <label for="id" class="form-label">ID</label>
                  <!-- <input type="text" class="form-control" id="id" placeholder="Id" v-model="patient.id" required> -->
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
  name: 'RegistrationPage',
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
  methods : {
    addPatient(){
      fetch("http://localhost:8080/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.patient)
      })
      .then(res => res.json())
      .then(data => {
          console.log(data)
          this.$router.push("/")
      })
      .catch(err => console.log(err.message))
    },

  },
  mounted() {

  },  
  created() {
    
  }
}
</script>

<style>
    
</style>