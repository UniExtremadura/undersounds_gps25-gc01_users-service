package es.unex.gc01.usersservice.dto;



import java.util.List;

public class UpdateArtistDTO {
    private String artisticName;
    private String description;
    private String iban;
    private String accountProperty;
    private List<String> genres;
    private List<SocialMediaLinks> socialMediaLinks;

    // Getters y Setters
    public String getArtisticName() { return artisticName; }
    public void setArtisticName(String artisticName) { this.artisticName = artisticName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    public String getAccountProperty() { return accountProperty; }
    public void setAccountProperty(String accountProperty) { this.accountProperty = accountProperty; }
    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public List<SocialMediaLinks> getSocialMediaLinks() { return socialMediaLinks; }
    public void setSocialMediaLinks(List<SocialMediaLinks> socialMediaLinks) { this.socialMediaLinks = socialMediaLinks; }
}