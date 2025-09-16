package com.example.cost.repository;

import com.example.cost.entity.CalculForm;
import com.example.cost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculFormRepository extends JpaRepository<CalculForm, Long> {
    List<CalculForm> findByUser(User user);
}
