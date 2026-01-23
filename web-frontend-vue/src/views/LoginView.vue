<script setup lang="ts">
import { ref } from 'vue';
import { login } from '../api/auth.api';
import type { LoginRequest } from '../api/auth.types';
import { useRouter } from 'vue-router';

const router = useRouter();

const form = ref<LoginRequest>({
    username: '',
    password: ''
});

const error = ref<string | null>(null);
const loading = ref(false);

async function handleLoginSubmit() {
    if (!form.value.username || !form.value.password) {
        error.value = 'username and password are required';
        return;
    }

    error.value = null;
    loading.value = true;

    try {
        await login(form.value);
        router.push("/");
    } catch (e: any) {
        error.value = e?.response?.data?.message ?? 'Login failed';
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
                    <h1 class="h4 mb-3 text-center">Login</h1>

                    <form @submit.prevent="handleLoginSubmit">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input class="form-control" v-model="form.username" required />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" v-model="form.password" required />
                        </div>

                        <button class="btn btn-primary w-100" :disabled="loading">
                            {{ loading ? 'Logging in…' : 'Login' }}
                        </button>
                    </form>

                    <div v-if="error" class="alert alert-danger mt-3">
                        {{ error }}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
