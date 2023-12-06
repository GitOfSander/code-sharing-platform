package platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import platform.entities.Code;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findAll();

    List<Code> findFirst10ByOrderByDateDesc();

    @Query("SELECT c FROM Code c WHERE c.timeRestriction = 0 AND c.viewsRestriction = 0 ORDER BY c.date DESC LIMIT 10")
    List<Code> findLast10NotRestricted();

    Optional<Code> findById(UUID id);
}