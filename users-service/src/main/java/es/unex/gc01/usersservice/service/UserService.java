package es.unex.gc01.usersservice.service;


import es.unex.gc01.usersservice.dto.*;

import java.util.List;

public interface UserService {
    UserProfileDTO registerUser(UserRegisterDTO userDTO);
    UserProfileDTO authenticateUser(UserLoginDTO loginDTO);
    UserProfileDTO getUserProfileByUsername(String username);
    UserProfileDTO getUserProfileById(Long id);
    UserProfileDTO updateUser(String username, UpdateUserDTO updateUserDTO);
    SuccessfulResponseDTO deleteUser(String username);
    UserProfileDTO getUserForMicroservice(String username);
    UserProfileDTO getUserLoginForMicroservice(String email, String password);



    // ↓↓↓ NUEVOS MÉTODOS PARA ADMIN ↓↓↓
    List<UserAdminDTO> getAllUsers();
    SuccessfulResponseDTO changeUserRole(String username, String newRole);

}