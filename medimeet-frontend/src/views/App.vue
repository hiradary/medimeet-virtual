<template>
  <div id="app">
    <h1>MediMeet</h1>
    <router-view name="nav"></router-view>
    <!-- <DoctorInterface/> -->
    <!-- <PatientInterface/>  -->
    <!-- <LoginPage/> -->
    <!-- <UserSummary/> -->
    <UpdateUser/>

  </div>
</template>

<script>
// import DoctorInterface from './components/DoctorInterface.vue'
// import LoginPage from './LoginPage.vue';
// import PatientInterface from './PatientInterface.vue'
// import UserSummary from '../components/UserSummary.vue';
import UpdateUser from '../views/UpdateUser.vue'


export default {
  name: 'App',
  components: {
    // DoctorInterface,
    // PatientInterface,
    // UserSummary
    // LoginPage
    UpdateUser
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>