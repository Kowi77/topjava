package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> findBetween(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

 /*   List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

    С between не справился) Потом еще попробую*/

    @Override
    @Transactional
    Meal save(Meal meal);

    @Transactional
    @Modifying
    int deleteByIdAndUserId(int id, int userId);

    @Override
    Meal findOne(Integer integer);

    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.user WHERE m.id=:id ORDER BY m.dateTime DESC")
    Meal findWithUserById(@Param("id") int id);

}
