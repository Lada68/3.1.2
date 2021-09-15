package com.example.sboot.repository;

import com.example.sboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);
    Object findByUsername(String s);

    @Override
    void deleteById(Integer integer);
}
