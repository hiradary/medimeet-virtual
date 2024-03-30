<template>
    <main>
        <NavBar/>
        <div> 
        Doctor Information div
        </div>
        <div>
          Appointment Information div
        </div>
        <input type="submit" value="Get all the patients">
        <input type="submit" value="Get patient by id">
        <input type="submit" value="Delete a user">
    </main>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default {
  name: 'DoctorInterface',
  components: {
    NavBar
  }
}
</script>

<style>

</style>