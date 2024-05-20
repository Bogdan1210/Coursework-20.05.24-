package ldubgd.coursework.TestFTE.repository;

import ldubgd.coursework.TestFTE.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Query(value = "select u from Menu u where u.name = :name")
    Menu findByNameMenu(String name);
}
