package com.example.backend.repository;

import com.example.backend.model.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT s FROM Service s JOIN s.categories c WHERE c.name = :category")
    List<Service> getServicesByCategoryName(@Param("category") String category);
}
