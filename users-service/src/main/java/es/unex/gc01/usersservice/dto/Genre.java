package es.unex.gc01.usersservice.dto;



import es.unex.gc01.usersservice.model.enums.GenreType;

public class Genre {
    private Long id;
    private GenreType type;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public GenreType getType() { return type; }
    public void setType(GenreType type) { this.type = type; }
}