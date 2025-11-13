package es.unex.gc01.usersservice.dto;


import java.util.List;

public class SentArtistDTO {
    private Long artistId;
    private String username;
    private String artisticName;
    private String description;
    private boolean isTrending;
    private boolean verified;
    private List<SocialMediaLinks> socialMediaLinks;
    private Integer monthlyStreams;
    private List<Genre> genres;

    // Getters y Setters
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getArtisticName() { return artisticName; }
    public void setArtisticName(String artisticName) { this.artisticName = artisticName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isTrending() { return isTrending; }
    public void setTrending(boolean trending) { isTrending = trending; }
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
    public List<SocialMediaLinks> getSocialMediaLinks() { return socialMediaLinks; }
    public void setSocialMediaLinks(List<SocialMediaLinks> socialMediaLinks) { this.socialMediaLinks = socialMediaLinks; }
    public Integer getMonthlyStreams() { return monthlyStreams; }
    public void setMonthlyStreams(Integer monthlyStreams) { this.monthlyStreams = monthlyStreams; }
    public List<Genre> getGenres() { return genres; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }
}