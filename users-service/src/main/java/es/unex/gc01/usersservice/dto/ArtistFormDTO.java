package es.unex.gc01.usersservice.dto;


public class ArtistFormDTO {
    private String artistName;
    private String iban;
    private String accountPropietary;
    private String description;

    // Getters y Setters
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    public String getAccountPropietary() { return accountPropietary; }
    public void setAccountPropietary(String accountPropietary) { this.accountPropietary = accountPropietary; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}