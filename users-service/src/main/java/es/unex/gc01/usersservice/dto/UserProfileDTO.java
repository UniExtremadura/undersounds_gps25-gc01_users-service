package es.unex.gc01.usersservice.dto;


import es.unex.gc01.usersservice.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserProfileDTO {
    // Getters y Setters
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String surname;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String personalLink;
    @Setter
    @Getter
    private String birthday;
    private UserRole rol;

    @Setter
    @Getter
    private String bio;
    @Setter
    @Getter
    private String phone;


    @Setter
    @Getter
    private List<String> favoriteGenres;


    public UserRole getUserRole() {
        return rol; }

    public void setUserRole(UserRole userRole) {
        rol = userRole;

    }






}


