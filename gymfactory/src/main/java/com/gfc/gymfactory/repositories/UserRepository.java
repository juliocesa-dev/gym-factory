package com.gfc.gymfactory.repositories;

import org.springframework.stereotype.Repository;
import com.gfc.gymfactory.domain.entities.User;

import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {

}
