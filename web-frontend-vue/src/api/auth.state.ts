import { reactive } from "vue";

interface AuthState {
    isAuthenticated: boolean;
    username: string | null;
}

export const authState = reactive<AuthState>({
    username: localStorage.getItem("username"),
    isAuthenticated: !!localStorage.getItem("access_token")
});

export function setAuthState(isAuthenticated: boolean, username: string) {
    authState.isAuthenticated = isAuthenticated;
    authState.username = username;
    localStorage.setItem("username", username);
}

export function clearAuthState() {
    authState.isAuthenticated = false;
    authState.username = null;
    localStorage.removeItem("username");
}