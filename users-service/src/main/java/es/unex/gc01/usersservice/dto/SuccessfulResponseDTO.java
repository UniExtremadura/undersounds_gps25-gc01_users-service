package es.unex.gc01.usersservice.dto;

public class SuccessfulResponseDTO {
    private String successful;
    private String message;
    private int statusCode;
    private String timestamp;

    public SuccessfulResponseDTO(String rolActualizadoCorrectamente) {
        this.successful = rolActualizadoCorrectamente;
    }

    // Getters y Setters
    public String getSuccessful() { return successful; }
    public void setSuccessful(String successful) { this.successful = successful; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}