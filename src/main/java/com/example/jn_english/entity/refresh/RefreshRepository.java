package com.example.jn_english.entity.refresh;

import com.example.jn_english.entity.admin.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshRepository extends CrudRepository<Refresh, Long> {
    Optional<Refresh> findByAdmin(Admin admin);
}
