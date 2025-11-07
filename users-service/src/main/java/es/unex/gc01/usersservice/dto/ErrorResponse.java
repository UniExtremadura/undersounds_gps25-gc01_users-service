package es.unex.gc01.usersservice.dto;


public class ErrorResponse {
    private String error;
    private String message;
    private int statusCode;
    private String timestamp;

    public ErrorResponse() {}

    public ErrorResponse(String error, String message, int statusCode, String timestamp) {
        this.error = error;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    // Getters y Setters
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}