package ie.dsch.services.controller;

import org.springframework.stereotype.Component;

@Component
public class ErrorException {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
