package es.unex.gc01.usersservice.dto;


import java.util.List;

public class PageSentArtistDTO {
    private List<SentArtistDTO> content;

    // Getters y Setters
    public List<SentArtistDTO> getContent() { return content; }
    public void setContent(List<SentArtistDTO> content) { this.content = content; }
}