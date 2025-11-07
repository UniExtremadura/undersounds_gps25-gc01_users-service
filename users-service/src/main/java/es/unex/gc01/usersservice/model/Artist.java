package es.unex.gc01.usersservice.model;


import es.unex.gc01.usersservice.model.enums.GenreType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String artisticName;

    @Column(length = 1000)
    private String description;

    private String iban;
    private String accountPropietary;
    private boolean isTrending = false;
    private boolean verified = false;
    private boolean deleted = false;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<GenreType> genres = new HashSet<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getArtisticName() { return artisticName; }
    public void setArtisticName(String artisticName) { this.artisticName = artisticName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    public String getAccountPropietary() { return accountPropietary; }
    public void setAccountPropietary(String accountPropietary) { this.accountPropietary = accountPropietary; }
    public boolean isTrending() { return isTrending; }
    public void setTrending(boolean trending) { isTrending = trending; }
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
    public Set<GenreType> getGenres() { return genres; }
    public void setGenres(Set<GenreType> genres) { this.genres = genres; }
}