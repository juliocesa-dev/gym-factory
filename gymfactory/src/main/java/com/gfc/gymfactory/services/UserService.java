package com.gfc.gymfactory.services;

import com.gfc.gymfactory.dtos.response.UserResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
     UserResponse findUserByID(UUID id);

    PageResponse<UserResponse> findUsers(Pageable pageable);
}
