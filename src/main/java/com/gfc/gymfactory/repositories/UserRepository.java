package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.gfc.gymfactory.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {

    default User findByEmailOrThrow(String email, String message){
        return findByEmail(email).orElseThrow(() -> new ApiException(message, HttpStatus.NOT_FOUND));
    }

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
