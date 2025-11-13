package es.unex.gc01.usersservice.dto;

import es.unex.gc01.usersservice.model.enums.GenreType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class UserAdminDTO {




        private String username;

        private String surname;
        private String name;
        private String email;
        private String role;
        private String phone;
        private LocalDate birthday;
        private List<GenreType> genre;



}
