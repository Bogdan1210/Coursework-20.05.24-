package ldubgd.coursework.TestFTE.repository;

import ldubgd.coursework.TestFTE.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MediaRepository extends JpaRepository<Media,Long> {
    @Query(value = "select u from Media u where u.menu = :menu")
    Media findByMenu(String menu);
}
