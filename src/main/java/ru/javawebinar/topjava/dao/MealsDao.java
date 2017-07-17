package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Андрей on 14.07.2017.
 */
public interface MealsDao {
    void add (LocalDateTime dateTime, String desription, Integer calories);
    void delete (int mealId);
    void update (Integer id, LocalDateTime dateTime, String desription, Integer calories);
    List<Meal> getAll ();
    Meal getById (int mealId);
}
