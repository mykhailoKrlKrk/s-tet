package com.example.backend.repository;

import com.example.backend.model.Master;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MasterRepository extends JpaRepository<Master, Long> {

    @Query("SELECT DISTINCT m FROM Master m JOIN m.services s "
            + "JOIN s.categories c WHERE c.name = :category AND m.isDeleted = false")
    List<Master> getMastersByCategory(@Param("category") String category);
}
