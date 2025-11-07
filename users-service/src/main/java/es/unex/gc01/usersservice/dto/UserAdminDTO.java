package es.unex.gc01.usersservice.dto;

public class UserAdminDTO {



        private String username;
        private String surname;
        private String name;
        private String email;
        private String role;

        // getters y setters
    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getName() { return name; }
    public void setName(String username) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }
   public String getRole() { return role; }
   public void setRole(String role) { this.role = role; }


}
