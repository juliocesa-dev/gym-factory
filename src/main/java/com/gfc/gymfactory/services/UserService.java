package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.mappers.UserMapper;
import com.gfc.gymfactory.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@SuppressWarnings("null")
@RequiredArgsConstructor
@Service
public class UserService {

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
}
