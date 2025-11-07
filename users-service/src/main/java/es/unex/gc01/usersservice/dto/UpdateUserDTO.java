package es.unex.gc01.usersservice.dto;


import es.unex.gc01.usersservice.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UpdateUserDTO {
    private String username;
    private String name; // Añade este campo
    private String email;
    private String bio;
    private String personalLink;
    private String birthday;
    private String phone;
    private String surname;
    private UserRole role;
}