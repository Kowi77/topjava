package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Андрей on 14.07.2017.
 */
public interface MealsDao {
    void add (Meal meal);
    void delete (int mealId);
    void update (Meal meal);
    List<Meal> getAll ();
    Meal getById (int mealId);
}
