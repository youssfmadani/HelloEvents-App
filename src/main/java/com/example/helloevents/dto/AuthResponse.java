package com.example.helloevents.dto;

public class AuthResponse {
    private String jwtToken;

    public AuthResponse() {}

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // Getter & Setter
    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }
}
