package ldubgd.coursework.TestFTE.repository;

import ldubgd.coursework.TestFTE.entity.InlineKeyboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InlineKeyboardRepository extends JpaRepository<InlineKeyboard, Long> {
    @Query(value = "select u from InlineKeyboard u where u.menu = :menu")
    List<InlineKeyboard> findByMenu(String menu);
}
