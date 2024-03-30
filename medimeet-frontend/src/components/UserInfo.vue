<template>
    <div class="container">
      <hi class="center-text">User/Patient Information</hi>

      <table class="table table-striped">
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>Name: {{ user.name }}</td>
            <td>Email: {{ user.email }}</td>
            <td>ID: {{ user.id }}</td>
            <td>Age: {{ user.age }}</td>
            <td>Gender: {{ user.gender }}</td>
            <td>Phone: {{ user.phone }}</td>
            <td>Status: {{ user.status }}</td>
            <td>Prescription: {{ user.prescription }}</td>
            <td>Bio: {{ user.bio }}</td> 
          </tr>
        </tbody>

      </table>
    </div>
</template>

<script>
import UserService from '@/services/UserService'
export default {
  name: 'UserInfo',
  data(){
    return { 
      users: [] 
    } 
  },
  methods: {
    getUsers() {
      UserService.getUserList().then(response => {
        this.users = response.data
      })
    }
  }, created() {
    // this.getUsers()
  }
}

</script>

<style>
    
</style>