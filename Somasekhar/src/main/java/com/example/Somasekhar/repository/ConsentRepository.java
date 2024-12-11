package com.example.Somasekhar.repository;



import com.example.Somasekhar.entity.ConsentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<ConsentStatus, Long> {
}
