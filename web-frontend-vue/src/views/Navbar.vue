<template>
    <nav class="navbar bg-body-tertiary fixed-top">
        <div class="container-fluid">
            <router-link class="navbar-brand fw-semibold" to="/">
                Musfer (Closed Beta)
            </router-link>

            <ul class="navbar-nav flex-row align-items-center gap-2 ms-auto">
                <template v-if="!authState.isAuthenticated">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/signup">Sign Up</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/login">Login</router-link>
                    </li>
                </template>

                <template v-else>
                    <li class="nav-item text-secondary me-2">
                        {{ authState.username }}
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-outline-secondary btn-sm" @click="handleLogoutClick">
                            Logout
                        </button>
                    </li>
                </template>
            </ul>
        </div>
    </nav>
</template>

<script setup lang="ts">
import { authState } from '../api/auth.state'
import { logout } from '../api/auth.api'

function handleLogoutClick() {
    logout()
}
</script>
