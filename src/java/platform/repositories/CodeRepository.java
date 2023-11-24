package platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.entities.Code;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findAll();

    //@Query("SELECT c.code, c.date FROM code c ORDER BY date DESC LIMIT 10;")
    List<Code> findFirst10ByOrderByDateDesc();
}