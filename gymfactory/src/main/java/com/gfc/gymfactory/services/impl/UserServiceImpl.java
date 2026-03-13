package com.gfc.gymfactory.services.impl;

import com.gfc.gymfactory.domain.entities.User;
import com.gfc.gymfactory.repositories.UserRepository;
import com.gfc.gymfactory.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByID(UUID id) {
        return userRepository.findByIdOrThrow(id, "Usuário não encontrado");
    }
}
