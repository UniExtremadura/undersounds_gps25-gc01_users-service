package es.unex.gc01.usersservice.dto;


import es.unex.gc01.usersservice.model.enums.GenreType;
import es.unex.gc01.usersservice.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


import es.unex.gc01.usersservice.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserProfileDTO {
    @Setter @Getter
    private String username;

    @Setter @Getter
    private String name;

    @Setter @Getter
    private String surname;

    @Setter @Getter
    private String email;

    @Setter @Getter
    private String personalLink;

    @Setter @Getter
    private String birthday;

    // Opción 2: Mantener 'rol' pero forzar la serialización como 'userRole'
    @Setter @Getter
    @JsonProperty("userRole") // ← Esto fuerza a que en JSON se llame 'userRole'
    private UserRole rol;

    @Setter @Getter
    private String bio;

    @Setter @Getter
    private String phone;

    @Setter @Getter
    private List<GenreType> favoriteGenres;
}

