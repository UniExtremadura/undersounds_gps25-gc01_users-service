package es.unex.gc01.usersservice.model;

import es.unex.gc01.usersservice.model.enums.GenreType;
import es.unex.gc01.usersservice.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String personalLink;
    private LocalDate birthday;

    // CORREGIDO: Inicializar con string vacío
    @Column(columnDefinition = "varchar(1000) default ''")
    private String bio = "";

    // CORREGIDO: Inicializar con string vacío
    @Column(columnDefinition = "varchar(20) default ''")
    private String phone = "";

    private boolean deleted = false;

    // CORREGIDO: Cambiar el nombre del campo para que coincida con el frontend
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole userRole = UserRole.USER;  // ← CAMBIADO A userRole

    // CORREGIDO: Asegurar que se carguen los géneros (FetchType.EAGER)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_genres", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private List<GenreType> favoriteGenres = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPersonalLink() { return personalLink; }
    public void setPersonalLink(String personalLink) { this.personalLink = personalLink; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    // CORREGIDO: Cambiado a userRole
    public UserRole getUserRole() { return userRole; }
    public void setUserRole(UserRole userRole) { this.userRole = userRole; }

    public List<GenreType> getFavoriteGenres() { return favoriteGenres; }
    public void setFavoriteGenres(List<GenreType> favoriteGenres) { this.favoriteGenres = favoriteGenres; }

    // Metodo helper para manejar null en favoriteGenres
    public List<GenreType> getSafeFavoriteGenres() {
        return favoriteGenres != null ? favoriteGenres : new ArrayList<>();
    }
}