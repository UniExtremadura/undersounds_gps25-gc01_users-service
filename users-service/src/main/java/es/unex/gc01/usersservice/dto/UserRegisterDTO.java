package es.unex.gc01.usersservice.dto;


import es.unex.gc01.usersservice.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRegisterDTO {
    // Getters y Setters
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private List<String> favoriteGenres;

}