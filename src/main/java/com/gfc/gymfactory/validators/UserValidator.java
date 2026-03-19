package com.gfc.gymfactory.validators;

import com.gfc.gymfactory.exception.ApiException;
import com.gfc.gymfactory.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final UserRepository userRepository;

    public void throwIfEmailExists(String email){
        if (userRepository.existsByEmail(email)) {
            throw new ApiException("Email já cadastrado", HttpStatus.CONFLICT);
        }
    }
}
