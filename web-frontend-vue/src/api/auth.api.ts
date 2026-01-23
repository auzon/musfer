import { clearTokens, getRefreshToken, setAccessToken, setRefreshToken } from "../auth/token.store";
import { AuthUserResponse, LoginRequest, LogoutRequest, RegisterRequest, TokenResponse } from "./auth.types";
import httpClient from "./http.client";
import { clearAuthState, setAuthState } from "./auth.state";

export async function login(payload: LoginRequest) {
    const response = await httpClient.post<TokenResponse>("/auth/login", payload);
    setAccessToken(response.data.accessToken);
    setRefreshToken(response.data.refreshToken);
    setAuthState(true, payload.username);
    return response.data;
}

export async function signup(payload: RegisterRequest) {
    const response = await httpClient.post<AuthUserResponse>("/auth/register", payload)
    return response.data;
}

export async function logout() {
    const refreshToken = getRefreshToken();
    if (!refreshToken) return;
    const payload: LogoutRequest = {
        refreshToken: refreshToken
    };
    const response = await httpClient.post<AuthUserResponse>("/auth/revoke", payload)
    clearTokens();
    clearAuthState();
}