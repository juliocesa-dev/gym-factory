package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.domain.enums.Role;
import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.exception.ApiException;
import com.gfc.gymfactory.mappers.UserMapper;
import com.gfc.gymfactory.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("null")
@RequiredArgsConstructor
@Service
public class UserService {

    private static final List<Role> STAFF_ROLES = List.of(Role.INSTRUCTOR, Role.RECEPTIONIST);

    private final UserRepository userRepository;
    private final UserMapper usuarioMapper;

    public UserResponse findUserByID(UUID id) {
        User user = userRepository.findByIdOrThrow(id, "Usuário não encontrado");
        return usuarioMapper.toUserResponse(user);
    }

    public PageResponse<UserResponse> findUsers(Pageable pageable) {
        return PageResponse.of(
                userRepository.findAll(pageable).map(usuarioMapper::toUserResponse)
        );
    }

    public PageResponse<UserResponse> findEmployeesByActive(boolean active, Pageable pageable) {
        return PageResponse.of(
                userRepository.findAllByRoleInAndActive(STAFF_ROLES, active, pageable)
                        .map(usuarioMapper::toUserResponse)
        );
    }

    public void enableEmployee(UUID id) {
        User user = userRepository.findByIdOrThrow(id, "Funcionário não encontrado");
        if (!STAFF_ROLES.contains(user.getRole())) {
            throw new ApiException("Usuário não é um funcionário", HttpStatus.BAD_REQUEST);
        }
        user.setActive(true);
        userRepository.save(user);
    }

    public void disableEmployee(UUID id) {
        User user = userRepository.findByIdOrThrow(id, "Funcionário não encontrado");
        if (!STAFF_ROLES.contains(user.getRole())) {
            throw new ApiException("Usuário não é um funcionário", HttpStatus.BAD_REQUEST);
        }
        user.setActive(false);
        userRepository.save(user);
    }
}
