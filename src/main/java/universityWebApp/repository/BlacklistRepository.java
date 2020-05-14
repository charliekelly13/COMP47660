package universityWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityWebApp.model.Blacklist;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, String> {
    @Query("select a.attempts from Blacklist a where a.ip = ?1")
    Integer findAttemptsByIp(String ip);

}