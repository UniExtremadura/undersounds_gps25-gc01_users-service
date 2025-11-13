package es.unex.gc01.usersservice.service.impl;



import es.unex.gc01.usersservice.dto.*;
import es.unex.gc01.usersservice.model.User;
import es.unex.gc01.usersservice.repository.UserRepository;
import es.unex.gc01.usersservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


import es.unex.gc01.usersservice.dto.*;
import es.unex.gc01.usersservice.model.User;
import es.unex.gc01.usersservice.model.enums.UserRole;
import es.unex.gc01.usersservice.repository.UserRepository;
import es.unex.gc01.usersservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfileDTO registerUser(UserRegisterDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());  // ← CORREGIDO
        user.setSurname(userDTO.getSurname());    // ← CORREGIDO
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setBio(userDTO.getBio());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserRole(UserRole.USER);  // ← Rol por defecto
    user.setFavoriteGenres(userDTO.getFavoriteGenres());
        User savedUser = userRepository.save(user);
        return mapToUserProfileDTO(savedUser);
    }

    @Override
    public UserProfileDTO authenticateUser(UserLoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return mapToUserProfileDTO(user);
    }

    @Override
    public UserProfileDTO getUserProfileByUsername(String username) {
        User user = userRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToUserProfileDTO(user);
    }

    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToUserProfileDTO(user);
    }

    @Override
    public UserProfileDTO updateUser(String username, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setName(updateUserDTO.getUsername());  // ← CORREGIDO
        user.setSurname(updateUserDTO.getSurname());    // ← CORREGIDO
        user.setEmail(updateUserDTO.getEmail());
        user.setBio(updateUserDTO.getBio());
        user.setPersonalLink(updateUserDTO.getPersonalLink());

        if (updateUserDTO.getBirthday() != null) {
            user.setBirthday(LocalDate.parse(updateUserDTO.getBirthday()));
        }

        user.setPhone(updateUserDTO.getPhone());

        User updatedUser = userRepository.save(user);
        return mapToUserProfileDTO(updatedUser);
    }

    @Override
    public SuccessfulResponseDTO deleteUser(String username) {
        User user = userRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setDeleted(true);
        userRepository.save(user);

        return new SuccessfulResponseDTO("eliminado");
    }

    @Override
    public UserProfileDTO getUserForMicroservice(String username) {
        return getUserProfileByUsername(username);
    }

    @Override
    public UserProfileDTO getUserLoginForMicroservice(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return mapToUserProfileDTO(user);
    }

    @Override
    public List<UserAdminDTO> getAllUsers() {
        List<User> users = userRepository.findAllByDeletedFalse();
        return users.stream()
                .map(this::mapToUserAdminDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SuccessfulResponseDTO changeUserRole(String username, String newRole) {
        User user = userRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        try {
            UserRole role = UserRole.valueOf(newRole.toUpperCase());
            user.setUserRole(role);
            userRepository.save(user);
            return new SuccessfulResponseDTO("Rol actualizado correctamente");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol no válido: " + newRole);
        }
    }

    private UserAdminDTO mapToUserAdminDTO(User user) {
        UserAdminDTO dto = new UserAdminDTO();
        dto.setName(user.getUsername());
        dto.setSurname(user.getSurname());

        dto.setEmail(user.getEmail());
        dto.setRole(user.getUserRole().name());
        return dto;
    }

    private UserProfileDTO mapToUserProfileDTO(User user) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());  // ← Mapear firstName a name
        dto.setSurname(user.getSurname()); // ← Mapear lastName a surname
        dto.setEmail(user.getEmail());
        dto.setPersonalLink(user.getPersonalLink());
        dto.setRol(user.getUserRole());   // ← Rol como enum

        if (user.getBirthday() != null) {
            dto.setBirthday(user.getBirthday().format(DateTimeFormatter.ISO_DATE));
        }

        return dto;
    }

    public User getUserByUsername(String username) {
        // Usar el metodo que carga los géneros
        return userRepository.findByUsernameWithGenres(username)
                .orElseThrow();
    }


}