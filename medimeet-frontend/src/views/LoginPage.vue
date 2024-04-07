<template>
  <div class="login-container">
    <div class="login-card">
      <h2>Login</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="username" required>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <button type="submit" class="btn">Login</button>
      </form>
      <div class="error-message" v-if="errorMessage">{{ errorMessage }}</div>
     <br/>
       <!-- Registration link -->
       <router-link to="/registration" class="register-link">Don't have an account? Register here</router-link>
   
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    login() {
      // Here you can implement your login logic
      if (this.username === 'admin' && this.password === 'password') {
        // Successful login
        this.errorMessage = '';
        alert('Login successful!');
      } else {
        // Invalid credentials
        this.errorMessage = 'Invalid username or password';
      }
    }
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: flex-start; /* Align the card to the top */
  height: 100vh;
  padding-top: 50px; /* Add space at the top */
  background-image: url('~@/assets/img/bg-img.jpeg'); /* Set the background image path */
  background-size: cover;
  background-position: center;
}

.login-card {
  width: 400px; /* Increase the width of the card */
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.8); /* Add opacity to make the card semi-transparent */
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.btn {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.btn:hover {
  background-color: #0056b3;
}

.error-message {
  color: #dc3545;
  margin-top: 5px;
}

/* Registration link styles */
.register-link {
  display: block;
  margin-top: 10px;
  text-align: center;
  color: #007bff;
  text-decoration: none;
}

/* Optional: Add hover effect to the registration link */
.register-link:hover {
  text-decoration: underline;
}
</style>
