<template>
  <Navbar />
  <main class="container py-4">
    <router-view />
  </main>
</template>

<script setup>
import Navbar from './views/Navbar.vue'
</script>
