package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.domain.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findTopByEmailOrderByCreatedAtDesc(String email);
}
