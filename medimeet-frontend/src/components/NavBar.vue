<template>
    <main>
      <navbar class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="/">Frontend</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 flex-grow-1">
              <li class="nav-item mx-3"> <a class="nav-link" href="/">Home</a> </li>
              <li class="nav-item mx-3"> <a class="nav-link" href="/about">About Doctor</a> </li>
              <li class="nav-item mx-3"> <a class="nav-link" href="/login">Login</a> </li>
              <li class="nav-item mx-3"> <a class="nav-link" href="/registration">Registration</a> </li>
              <li class="nav-item mx-3"> <a class="nav-link" href="/edit/{{ user.id }}">User Summary</a> </li>
            </ul>
          </div>
        </div>
      </navbar>
    </main>
</template>

<script>
export default {
  name: 'NavBar',
  components: {
    
  }
}
</script>

<style> 

</style>