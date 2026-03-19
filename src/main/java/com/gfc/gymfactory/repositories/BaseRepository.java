package com.gfc.gymfactory.repositories;

import com.gfc.gymfactory.exception.ApiException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    default T findByIdOrThrow(ID id, String message){
        return findById(id).orElseThrow(() -> new ApiException(message, HttpStatus.NOT_FOUND));
    }

}
