package es.unex.gc01.usersservice.dto;

import es.unex.gc01.usersservice.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

// DTO para cambiar rol
@Setter
@Getter
public class ChangeRoleDTO {
    private UserRole role;


}
