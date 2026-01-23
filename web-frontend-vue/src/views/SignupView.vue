<script setup lang="ts">
import { ref } from 'vue';
import { signup } from '../api/auth.api';

const form = ref({
    username: "",
    password: ""
})

const loading = ref(false);
const error = ref<string | null>(null);
const success = ref(false);

async function handleSignupSubmit() {
    success.value = false;
    loading.value = true;
    error.value = null;

    try {
        await signup(form.value);
        success.value = true;
    } catch (e: any) {
        error.value = e?.value?.data?.message ?? "Registration failed.";
    } finally {
        loading.value = false;
    }
}

</script>

<template>
    <div class="row justify-content-center">
        <div class="col-12 col-sm-10 col-md-6 col-lg-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h1 class="h4 mb-3 text-center">Sign Up</h1>

                    <form @submit.prevent="handleSignupSubmit">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input class="form-control" v-model="form.username" required />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" v-model="form.password" required />
                        </div>

                        <button class="btn btn-primary w-100" :disabled="loading">
                            {{ loading ? 'Signing up…' : 'Sign Up' }}
                        </button>
                    </form>

                    <div v-if="error" class="alert alert-danger mt-3">
                        {{ error }}
                    </div>

                    <div v-if="success" class="alert alert-success mt-3">
                        Signup successful. Account must be activated by admin.
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
