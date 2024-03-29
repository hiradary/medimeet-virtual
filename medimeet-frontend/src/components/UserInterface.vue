<template>
    <div id="user">
        <h2>User Interface</h2>
        <form action="">
            <label for="Username">Username: </label>
            <input type="text" name="Username" id="Username">
            <br>
            <label for="password">Password: </label>
            <input type="password" name="password" id="password">
            <br>
            <input type="submit" value="Login">
        </form>
    </div>
</template>

<script>
export default {
  name: 'UserInterface',
  components: {
    
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