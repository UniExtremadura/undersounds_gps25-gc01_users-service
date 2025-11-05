package es.unex.gc01.usersservice.dto;


import es.unex.gc01.usersservice.model.enums.SocialMediaTypes;

public class SocialMediaLinks {
    private Long id;
    private SocialMediaTypes type;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public SocialMediaTypes getType() { return type; }
    public void setType(SocialMediaTypes type) { this.type = type; }
}