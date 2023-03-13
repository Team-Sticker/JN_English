package com.example.jn_english.entity.admin;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByAccountId(String accountId);
}
