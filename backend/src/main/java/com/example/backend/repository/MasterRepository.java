package com.example.backend.repository;

import com.example.backend.model.Master;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MasterRepository extends JpaRepository<Master, Long> {

    @Query("SELECT m FROM Master m WHERE m.service.name = :service")
    List<Master> getMastersByService(@Param("service") String service);

    @Query("SELECT m FROM Master m JOIN m.service s JOIN s.categories c WHERE c.name = :category")
    List<Master> getMastersByCategory(@Param("category") String category);
}
