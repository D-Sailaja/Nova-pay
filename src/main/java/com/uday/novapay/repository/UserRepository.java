package com.uday.novapay.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uday.novapay.entity.User;

    public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByEmail(String email);

        boolean existsByEmail(String email);

        boolean existsByPhoneNumber(String phoneNumber);

    }

