package com.example.brewery.Models;

import java.time.OffsetDateTime;

public class Token {
    public int IdToken;

    public String TokenValue;

    public OffsetDateTime TokenDateTime;

    public int getIdToken() {
        return IdToken;
    }

    public void setIdToken(int idToken) {
        IdToken = idToken;
    }

    public String getTokenValue() {
        return TokenValue;
    }

    public void setTokenValue(String tokenValue) {
        TokenValue = tokenValue;
    }

    public OffsetDateTime getTokenDateTime() {
        return TokenDateTime;
    }

    public void setTokenDateTime(OffsetDateTime tokenDateTime) {
        TokenDateTime = tokenDateTime;
    }
}
