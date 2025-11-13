package es.unex.gc01.usersservice.dto;

import es.unex.gc01.usersservice.model.enums.UserRole;

// DTO para cambiar rol
public class ChangeRoleDTO {
    private String role;
    public ChangeRoleDTO(String rol) {
        this.role = rol;
    }
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
