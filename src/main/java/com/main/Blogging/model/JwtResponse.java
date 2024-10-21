package com.main.Blogging.model;

public class JwtResponse {

    private String jwtToken;
    private String username;

    // Private constructor to restrict object creation from outside
    private JwtResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
        this.username = builder.username;
    }

    // Getters
    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }

    // Static builder() method to return a new Builder instance
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String jwtToken;
        private String username;

        // Builder methods to set properties
        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        // Build method to create the object
        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
