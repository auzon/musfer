export interface LoginRequest {
    username: string;
    password: string;
}

export interface TokenResponse {
    accessToken: string;
    refreshToken: string;
}

export interface RegisterRequest {
    username: string;
    password: string;
}

export interface AuthUserResponse {
    id: number;
    username: string;
}

export interface LogoutRequest {
    refreshToken: string;
}