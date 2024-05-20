package ldubgd.coursework.TestFTE.repository;

import ldubgd.coursework.TestFTE.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "select u from Users u where u.telegramId = :telegramId")
    Optional<Users> findByTelegramId(String telegramId);
}
