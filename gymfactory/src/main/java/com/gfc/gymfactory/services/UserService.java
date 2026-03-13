package com.gfc.gymfactory.services;

import com.gfc.gymfactory.domain.entities.User;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public interface UserService {
     User findUserByID(UUID id);
}
