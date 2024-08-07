package com.GASB.file.repository.org;

import com.GASB.file.model.entity.AdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<AdminUsers, Integer> {
    Optional<AdminUsers> findByEmail(String email);
    Optional<AdminUsers> findByEmailAndPassword(String email, String password);
    Optional<AdminUsers> findByEmailAndOrgId(String email, int orgId);

    boolean existsByEmail(String email);
}
